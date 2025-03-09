package br.com.zup.SkillForge.history.services.mappers;

import br.com.zup.SkillForge.history.dtos.GamerHistoryRequestDTO;
import br.com.zup.SkillForge.history.dtos.GamerHistoryResponseDTO;
import br.com.zup.SkillForge.history.models.GameHistory;
import org.springframework.stereotype.Component;

@Component
public class GameHistoryMapper {

    public GameHistory toEntity(GamerHistoryRequestDTO dto) {
        GameHistory gameHistory = new GameHistory();
        gameHistory.setMatchResult(dto.getMatchResult());
        gameHistory.setPointsEarned(dto.getPointsEarned());
        gameHistory.setFeedback(dto.getFeedback());
        return gameHistory;
    }

    public GamerHistoryResponseDTO toDTO(GameHistory entity) {
        GamerHistoryResponseDTO dto = new GamerHistoryResponseDTO();
        dto.setId(entity.getId());
        dto.setMatchResult(entity.getMatchResult());
        dto.setPointsEarned(entity.getPointsEarned());
        dto.setFeedback(entity.getFeedback());
        return dto;
    }
}