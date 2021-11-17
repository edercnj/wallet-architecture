package com.br.edercnj.wallettimeline.web.api.v1;

import com.br.edercnj.wallettimeline.service.FinancialMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(FinancialMovementController.class)
class FinancialMovementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinancialMovementService financialMovementService;

    @BeforeEach
    void setUp() {
        Mockito.when(financialMovementService.findByUserId(anyString())).thenReturn(Mockito.anyList());
    }

    @Test
    void getFinancialMovementsFromUser_should_be_return_200() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/timelines/users/{id}", "teste")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}