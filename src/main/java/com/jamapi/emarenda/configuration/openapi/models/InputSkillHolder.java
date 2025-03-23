package com.jamapi.emarenda.configuration.openapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

/** Used for OpenApi representation only */
@Getter
public class InputSkillHolder {
  @Valid @NotNull private List<InputSkillModel> skills;

  @Getter
  public static class InputSkillModel {
    @Schema(description = "Skill id (predefined skills only)")
    @JsonProperty("predefinedSkillId")
    private Long predefinedSkillId;

    @Schema(description = "Skill name")
    @JsonProperty("skillName")
    private String skillName;
  }
}
