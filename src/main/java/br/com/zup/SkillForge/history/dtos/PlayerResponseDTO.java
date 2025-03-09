package br.com.zup.SkillForge.history.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDTO {

    private int ranking;
    private int score;

}