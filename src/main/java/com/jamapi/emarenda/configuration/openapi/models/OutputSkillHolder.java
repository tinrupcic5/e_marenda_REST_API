package com.jamapi.emarenda.configuration.openapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

/** Used for OpenApi representation only */
@Getter
public class OutputSkillHolder {
  @Valid @NotNull private List<OutputSkillModel> skills;

  @Getter
  public static class OutputSkillModel {
    @Schema(description = "Skill id (predefined skills only)")
    @JsonProperty("predefinedSkillId")
    private Long predefinedSkillId;

    @Schema(description = "Skill name")
    @JsonProperty("skillNameAr")
    private String skillNameAr;

    @Schema(description = "Skill name")
    @JsonProperty("skillNameEn")
    private String skillNameEn;
  }
}
