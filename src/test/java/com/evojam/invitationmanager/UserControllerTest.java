package com.evojam.invitationmanager;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.matchers.JUnitMatchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InvitationManagerApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getUsers_thenStatus200() throws Exception {
        mvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getUserById_thenStatus200() throws Exception {
        mvc.perform(get("/api/user/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Konrad")));
    }

    @Test
    void createUser_thenStatus201() throws Exception {
        mvc.perform(post("/api/user/").accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json")
                        .content("{\"firstName\": \"Janusz\", \"lastName\": \"Testowy\"" +
                                ", \"email\": \"januszTestowy@gmail.com\"}"))
                .andDo(print())
                .andExpect(status().isCreated());

    }

}