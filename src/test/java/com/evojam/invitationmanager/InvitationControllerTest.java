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
class InvitationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getInvitations_thenStatus200() throws Exception {
        mvc.perform(get("/api/invitation/").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Invitation for technical interview")));
    }

    @Test
    void getInvitationById_thenStatus200() throws Exception {
        mvc.perform(get("/api/invitation/3").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Let's talk about future of our company")));
    }

    @Test
    void createInvitation_thenStatus201() throws Exception {
        mvc.perform(post("/api/invitation/").accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json")
                        .content("{" +
                                "\"inviteeEmail\": \"matkonrad99@gmail.com\", " +
                                "\"description\": \"Zdarzenie\", " +
                                "\"inviterEmail\": \"jankowalh4ck3r@protonmail.com\"" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}