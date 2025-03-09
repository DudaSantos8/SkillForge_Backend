package br.com.zup.SkillForge.history.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamerHistoryResponseDTO {

    private Long id;
    private String matchResult;
    private int pointsEarned;
    private String Feedback;

}

