-- V1_0__create_user_table.sql
CREATE TABLE app_role (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description VARCHAR(255)
);
CREATE TABLE e_school (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        city VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        oib VARCHAR(255) NOT NULL
);

CREATE TABLE e_grade (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE app_user (
                          id BIGSERIAL PRIMARY KEY,
                          username VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          phone VARCHAR(255),
                          name VARCHAR(255),
                          last_name VARCHAR(255),
                          oib VARCHAR(255) NOT NULL UNIQUE,
                          school_id BIGINT,
                          grade_id BIGINT NULL,
                          CONSTRAINT FK_user_school FOREIGN KEY (school_id) REFERENCES e_school (id) ON DELETE SET NULL,
                          CONSTRAINT FK_user_grade FOREIGN KEY (grade_id) REFERENCES e_grade (id) ON DELETE SET NULL
);

-- Create a new table to store the relationship between parents and children
CREATE TABLE parent_child (
                              id BIGSERIAL PRIMARY KEY,
                              parent_id BIGINT NOT NULL,
                              child_id BIGINT NOT NULL,
                              CONSTRAINT FK_parent_child_parent FOREIGN KEY (parent_id) REFERENCES app_user(id) ON DELETE CASCADE,
                              CONSTRAINT FK_parent_child_child FOREIGN KEY (child_id) REFERENCES app_user(id) ON DELETE CASCADE,
                              CONSTRAINT unique_parent_child UNIQUE (parent_id, child_id) -- Ensures a unique relationship between a parent and child
);


CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id) REFERENCES app_user (id) ON DELETE CASCADE,
                            CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id) REFERENCES app_role (id) ON DELETE CASCADE
);

-- Table to track user activities (logins, lunch attendance, and other actions)
CREATE TABLE user_activity (
                               id BIGSERIAL PRIMARY KEY,                   -- Unique ID for the activity
                               user_id BIGINT NOT NULL,                    -- The user associated with the activity
                               action_type VARCHAR(50) NOT NULL,           -- The type of action (e.g., "login", "lunch", "button_click")
                               action_description VARCHAR(255),            -- Optional description about the action (e.g., "User clicked 'Going to lunch'")
                               action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Timestamp of the action
                               CONSTRAINT FK_user_activity FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);


