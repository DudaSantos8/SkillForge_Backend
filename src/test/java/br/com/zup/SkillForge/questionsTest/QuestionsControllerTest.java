package br.com.zup.SkillForge.questionsTest;

import br.com.zup.SkillForge.softSkill.controllers.QuestionsController;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.SkillForge.softSkill.services.QuestionsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class QuestionsControllerTest {

    @InjectMocks
    private QuestionsController questionsController;

    @Mock
    private QuestionsService questionsService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionsController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateQuestion() throws Exception {
        QuestionsRequestDTO requestDTO = new QuestionsRequestDTO();
        requestDTO.setTitle("Sample Question");
        requestDTO.setOptionA("Option A");
        requestDTO.setOptionB("Option B");
        requestDTO.setOptionC("Option C");

        QuestionsResponseDTO responseDTO = new QuestionsResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle(requestDTO.getTitle());
        responseDTO.setOptionA(requestDTO.getOptionA());
        responseDTO.setOptionB(requestDTO.getOptionB());
        responseDTO.setOptionC(requestDTO.getOptionC());

        when(questionsService.createQuestion(any(QuestionsRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/inclusion-questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.title").value(responseDTO.getTitle()))
                .andExpect(jsonPath("$.optionA").value(responseDTO.getOptionA()))
                .andExpect(jsonPath("$.optionB").value(responseDTO.getOptionB()))
                .andExpect(jsonPath("$.optionC").value(responseDTO.getOptionC()));
    }

    @Test
    void testUpdateQuestion() throws Exception {
        Long questionId = 1L;

        QuestionsRequestDTO requestDTO = new QuestionsRequestDTO();
        requestDTO.setTitle("Updated Question");
        requestDTO.setOptionA("Updated Option A");
        requestDTO.setOptionB("Updated Option B");
        requestDTO.setOptionC("Updated Option C");

        QuestionsResponseDTO responseDTO = new QuestionsResponseDTO();
        responseDTO.setId(questionId);
        responseDTO.setTitle(requestDTO.getTitle());
        responseDTO.setOptionA(requestDTO.getOptionA());
        responseDTO.setOptionB(requestDTO.getOptionB());
        responseDTO.setOptionC(requestDTO.getOptionC());

        when(questionsService.updateQuestion(eq(questionId), any(QuestionsRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/inclusion-questions/{id}", questionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.title").value(responseDTO.getTitle()))
                .andExpect(jsonPath("$.optionA").value(responseDTO.getOptionA()))
                .andExpect(jsonPath("$.optionB").value(responseDTO.getOptionB()))
                .andExpect(jsonPath("$.optionC").value(responseDTO.getOptionC()));
    }

    @Test
    void testGetQuestionById() throws Exception {
        Long questionId = 1L;

        QuestionsResponseDTO responseDTO = new QuestionsResponseDTO();
        responseDTO.setId(questionId);
        responseDTO.setTitle("Sample Question");
        responseDTO.setOptionA("Option A");
        responseDTO.setOptionB("Option B");
        responseDTO.setOptionC("Option C");

        when(questionsService.getQuestionById(eq(questionId))).thenReturn(responseDTO);

        mockMvc.perform(get("/inclusion-questions/{id}", questionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.title").value(responseDTO.getTitle()))
                .andExpect(jsonPath("$.optionA").value(responseDTO.getOptionA()))
                .andExpect(jsonPath("$.optionB").value(responseDTO.getOptionB()))
                .andExpect(jsonPath("$.optionC").value(responseDTO.getOptionC()));
    }

    @Test
    void testListQuestions() throws Exception {
        QuestionsResponseDTO question1 = new QuestionsResponseDTO();
        question1.setId(1L);
        question1.setTitle("Sample Question 1");
        question1.setOptionA("Option A");
        question1.setOptionB("Option B");
        question1.setOptionC("Option C");

        QuestionsResponseDTO question2 = new QuestionsResponseDTO();
        question2.setId(2L);
        question2.setTitle("Sample Question 2");
        question2.setOptionA("Option A");
        question2.setOptionB("Option B");
        question2.setOptionC("Option C");

        List<QuestionsResponseDTO> questionsList = List.of(question1, question2);

        when(questionsService.listQuestions()).thenReturn(questionsList);

        mockMvc.perform(get("/inclusion-questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(question1.getId()))
                .andExpect(jsonPath("$[0].title").value(question1.getTitle()))
                .andExpect(jsonPath("$[0].optionA").value(question1.getOptionA()))
                .andExpect(jsonPath("$[0].optionB").value(question1.getOptionB()))
                .andExpect(jsonPath("$[0].optionC").value(question1.getOptionC()))
                .andExpect(jsonPath("$[1].id").value(question2.getId()))
                .andExpect(jsonPath("$[1].title").value(question2.getTitle()))
                .andExpect(jsonPath("$[1].optionA").value(question2.getOptionA()))
                .andExpect(jsonPath("$[1].optionB").value(question2.getOptionB()))
                .andExpect(jsonPath("$[1].optionC").value(question2.getOptionC()));
    }

    @Test
    void testDeleteQuestion() throws Exception {
        Long questionId = 1L;

        doNothing().when(questionsService).deleteQuestion(eq(questionId));

        mockMvc.perform(delete("/inclusion-questions/{id}", questionId))
                .andExpect(status().isNoContent());

        verify(questionsService, times(1)).deleteQuestion(eq(questionId));
    }
}
