package com.br.edercnj.walletuser.web.api.v1;

import com.br.edercnj.walletuser.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.mocks.UserDtoMock;
import com.br.edercnj.walletuser.mocks.UserMock;
import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private  UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws UserAlreadyRegisteredException, UserNotFoundException {
        Mockito.when(userService.createUser(any(User.class))).thenReturn(UserMock.createUser());
        Mockito.when(userService.findUserByUsername(anyString())).thenReturn(UserMock.createUser());
    }

    @Test
    void findUUserByUsername_should_be_return_200() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/users")
                        .param("username", "teste")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void deposit_should_be_return_201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .content(new ObjectMapper().writeValueAsString(UserDtoMock.createUserDto()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}