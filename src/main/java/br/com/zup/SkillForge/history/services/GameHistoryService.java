package br.com.zup.SkillForge.history.services;

import br.com.zup.SkillForge.history.dtos.GamerHistoryRequestDTO;
import br.com.zup.SkillForge.history.dtos.GamerHistoryResponseDTO;
import br.com.zup.SkillForge.history.models.GameHistory;
import br.com.zup.SkillForge.history.models.Player;
import br.com.zup.SkillForge.history.repositories.GameHistoryRepository;
import br.com.zup.SkillForge.history.repositories.PlayerRepository;
import br.com.zup.SkillForge.history.services.mappers.GameHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(GameHistoryService.class);

    private final GameHistoryRepository gameHistoryRepository;
    private final PlayerRepository playerRepository;
    private final GameHistoryMapper gameHistoryMapper;

    public GamerHistoryResponseDTO createGameHistory(Long playerId, GamerHistoryRequestDTO requestDTO) {
        logger.info("Creating game history for player with ID: {}", playerId);

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> {
                    logger.error("Player with ID {} not found", playerId);
                    return new IllegalArgumentException("Player not found");
                });

        GameHistory gameHistory = gameHistoryMapper.toEntity(requestDTO);
        gameHistory.setPlayer(player);

        GameHistory savedGameHistory = gameHistoryRepository.save(gameHistory);
        logger.info("Game history created with ID: {}", savedGameHistory.getId());

        return gameHistoryMapper.toDTO(savedGameHistory);
    }

    public GamerHistoryResponseDTO updateGameHistory(Long gameHistoryId, GamerHistoryRequestDTO requestDTO) {
        logger.info("Updating game history with ID: {}", gameHistoryId);

        GameHistory existingGameHistory = gameHistoryRepository.findById(gameHistoryId)
                .orElseThrow(() -> {
                    logger.error("Game history with ID {} not found", gameHistoryId);
                    return new IllegalArgumentException("Game history not found");
                });

        existingGameHistory.setMatchResult(requestDTO.getMatchResult());
        existingGameHistory.setPointsEarned(requestDTO.getPointsEarned());
        existingGameHistory.setFeedback(requestDTO.getFeedback());

        GameHistory updatedGameHistory = gameHistoryRepository.save(existingGameHistory);
        logger.info("Game history updated with ID: {}", updatedGameHistory.getId());

        return gameHistoryMapper.toDTO(updatedGameHistory);
    }

    public List<GamerHistoryResponseDTO> listGameHistories() {
        logger.info("Listing all game histories");

        return gameHistoryRepository.findAll().stream()
                .map(gameHistoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}