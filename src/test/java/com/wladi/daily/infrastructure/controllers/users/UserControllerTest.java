package com.wladi.daily.infrastructure.controllers.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wladi.daily.application.usecases.users.UserInteractor;
import com.wladi.daily.domain.entity.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserInteractor userInteractor;

    @MockBean
    private UserDtoMapper userDtoMapper;

    private User user;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("Wladi Silva", "wladisilva", "wladi@example.com", "password");
        userResponse = new UserResponse("Wladi Silva", "wladisilva", "wladi@example.com");
    }

    @Test
    void testGetUser() throws Exception {
        when(userInteractor.findUser("wladisilva")).thenReturn(user);
        when(userDtoMapper.toResponse(user)).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/wladisilva"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Wladi Silva"))
                .andExpect(jsonPath("$.username").value("wladisilva"))
                .andExpect(jsonPath("$.email").value("wladi@example.com"))
                .andDo(MockMvcResultHandlers.print());

        verify(userInteractor, times(1)).findUser("wladisilva");
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = List.of(user);
        
        when(userInteractor.findAllUsers()).thenReturn(users);
        when(userDtoMapper.toResponse(user)).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Wladi Silva"))
                .andExpect(jsonPath("$[0].username").value("wladisilva"))
                .andExpect(jsonPath("$[0].email").value("wladi@example.com"))
                .andDo(MockMvcResultHandlers.print());

        verify(userInteractor, times(1)).findAllUsers();
    }

    @Test
    void testCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest("Wladi Silva", "wladisilva", "wladi@example.com", "password");
        when(userDtoMapper.toUser(userRequest)).thenReturn(user);
        when(userInteractor.createUser(user)).thenReturn(user);
        when(userDtoMapper.toResponse(user)).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Wladi Silva"))
                .andExpect(jsonPath("$.username").value("wladisilva"))
                .andExpect(jsonPath("$.email").value("wladi@example.com"))
                .andDo(MockMvcResultHandlers.print());

        verify(userInteractor, times(1)).createUser(user);
    }

    @Test
    void testUpdateUser() throws Exception {
        UserRequest userRequest = new UserRequest("Wladi Silva", "wladisilva", "wladi@example.com", "password");
        when(userDtoMapper.toUser("wladisilva", userRequest)).thenReturn(user);
        when(userInteractor.updateUser(user)).thenReturn(user);
        when(userDtoMapper.toResponse(user)).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/users/wladisilva")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Wladi Silva"))
                .andExpect(jsonPath("$.username").value("wladisilva"))
                .andExpect(jsonPath("$.email").value("wladi@example.com"))
                .andDo(MockMvcResultHandlers.print());

        verify(userInteractor, times(1)).updateUser(user);
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/wladisilva"))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        verify(userInteractor, times(1)).deleteUser("wladisilva");
    }
    
}
