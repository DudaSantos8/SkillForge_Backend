package br.com.zup.SkillForge.history.dtos;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDTO {
    @Min(value = 0, message = "Ranking must be at least 0.")
    private int ranking;

    @Min(value = 0, message = "Score must be at least 0.")
    private int score;
}

