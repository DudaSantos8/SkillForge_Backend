package br.com.zup.SkillForge.history.controllers;

import br.com.zup.SkillForge.history.dtos.GamerHistoryRequestDTO;
import br.com.zup.SkillForge.history.dtos.GamerHistoryResponseDTO;
import br.com.zup.SkillForge.history.services.GameHistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/game-history")
@RequiredArgsConstructor
public class GameHistoryController {

    private final GameHistoryService gameHistoryService;
    private static final Logger logger = LoggerFactory.getLogger(GameHistoryController.class);

    @PostMapping("/{playerId}")
    public ResponseEntity<GamerHistoryResponseDTO> createGameHistory(
            @PathVariable Long playerId,
            @Valid @RequestBody GamerHistoryRequestDTO requestDTO) {
        logger.info("Received request to create game history for player ID: {}", playerId);
        try {
            GamerHistoryResponseDTO responseDTO = gameHistoryService.createGameHistory(playerId, requestDTO);
            logger.info("Game history created successfully for player ID: {}", playerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            logger.error("Error creating game history: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{gameHistoryId}")
    public ResponseEntity<GamerHistoryResponseDTO> updateGameHistory(
            @PathVariable Long gameHistoryId,
            @Valid @RequestBody GamerHistoryRequestDTO requestDTO) {
        logger.info("Received request to update game history with ID: {}", gameHistoryId);
        try {
            GamerHistoryResponseDTO responseDTO = gameHistoryService.updateGameHistory(gameHistoryId, requestDTO);
            logger.info("Game history updated successfully with ID: {}", gameHistoryId);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating game history: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GamerHistoryResponseDTO>> listGameHistories() {
        logger.info("Received request to list all game histories");
        List<GamerHistoryResponseDTO> responseDTOs = gameHistoryService.listGameHistories();
        logger.info("Successfully retrieved {} game histories", responseDTOs.size());
        return ResponseEntity.ok(responseDTOs);
    }
}