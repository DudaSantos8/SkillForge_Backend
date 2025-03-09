package br.com.zup.SkillForge.history.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamerHistoryRequestDTO {

    @NotEmpty(message = "MatchResult cannot be empty.")
    private String matchResult;

    @NotEmpty(message = "PointsEarned cannot be empty.")
    private int pointsEarned;

    @NotEmpty(message = "Feedback cannot be empty.")
    private String feedback;
}