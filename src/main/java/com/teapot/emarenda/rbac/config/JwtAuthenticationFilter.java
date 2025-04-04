package com.teapot.emarenda.rbac.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.header.string}")
    public String HEADER_STRING;

    @Value("${jwt.token.prefix}")
    public String TOKEN_PREFIX;

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
        "/swagger-ui/",
        "/swagger-ui.html",
        "/api-docs/",
        "/v3/api-docs/",
        "/swagger-resources/",
        "/webjars/",
        "/users/authenticate",
        "/users/register"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return EXCLUDED_PATHS.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "").trim();

            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException ex) {
                logger.error("Error occurred while retrieving Username from Token", ex);
                resolver.resolveException(req, res, null, ex);
            } catch (ExpiredJwtException ex) {
                logger.warn("The token has expired", ex);
                resolver.resolveException(req, res, null, ex);
            } catch (SignatureException ex) {
                logger.error("Authentication Failed. Invalid username or password.");
                resolver.resolveException(req, res, null, ex);
            }
        } else {
            // For non-excluded paths, if no token is present, let the request continue
            // The security configuration will handle the authentication requirement
            chain.doFilter(req, res);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(authToken, userDetails))) {
                UsernamePasswordAuthenticationToken authentication =
                        jwtTokenUtil.getAuthenticationToken(authToken, userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("User authenticated: " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}
