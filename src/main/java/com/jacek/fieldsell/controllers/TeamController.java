package com.jacek.fieldsell.controllers;

import com.jacek.fieldsell.dtos.TeamDTO;
import com.jacek.fieldsell.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/team")
@CrossOrigin("*")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/")
    public ResponseEntity<Collection<TeamDTO>> helloUserController() {
        final var allTeams = teamService.getAllTeams().stream().map(TeamDTO::fromModel).toList();
        return new ResponseEntity<>(allTeams, HttpStatus.OK);
    }
}
