package com.jacek.fieldsell.controllers;

import com.jacek.fieldsell.models.Team;
import com.jacek.fieldsell.services.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    public void returnsListOfTeams() throws Exception {
        var team1 = new Team("Team1", List.of("dupa", "dupa2"));
        var team2 = new Team("Team1", List.of("dupa", "dupa2"));

        when(teamService.getAllTeams()).thenReturn(List.of(team1, team2));

        mockMvc.perform(get("/team/")
                        .with(user("user"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(content().json("[{\"id\":1,\"name\":\"Team1\"},{\"id\":2,\"name\":\"Team2\"}]"));

    }

}