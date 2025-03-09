package br.com.zup.SkillForge.gamerHistoryTest;

import br.com.zup.SkillForge.history.controllers.GameHistoryController;
import br.com.zup.SkillForge.history.dtos.GamerHistoryRequestDTO;
import br.com.zup.SkillForge.history.dtos.GamerHistoryResponseDTO;
import br.com.zup.SkillForge.history.services.GameHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
public class GameHistoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GameHistoryService gameHistoryService;

    @InjectMocks
    private GameHistoryController gameHistoryController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createGameHistory_ShouldReturnCreatedStatusAndResponseBody() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(gameHistoryController).build();

        Long playerId = 1L;
        GamerHistoryRequestDTO requestDTO = new GamerHistoryRequestDTO("Victory", 100);
        GamerHistoryResponseDTO responseDTO = new GamerHistoryResponseDTO(1L, "Victory", 100);

        Mockito.when(gameHistoryService.createGameHistory(eq(playerId), any(GamerHistoryRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/game-history/{playerId}", playerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.matchResult").value(responseDTO.getMatchResult()))
                .andExpect(jsonPath("$.pointsEarned").value(responseDTO.getPointsEarned()));
    }

    @Test
    public void createGameHistory_ShouldReturnBadRequestWhenPlayerNotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(gameHistoryController).build();

        Long playerId = 1L;
        GamerHistoryRequestDTO requestDTO = new GamerHistoryRequestDTO("Victory", 100);

        Mockito.when(gameHistoryService.createGameHistory(eq(playerId), any(GamerHistoryRequestDTO.class)))
                .thenThrow(new IllegalArgumentException("Player not found"));

        mockMvc.perform(post("/api/game-history/{playerId}", playerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}