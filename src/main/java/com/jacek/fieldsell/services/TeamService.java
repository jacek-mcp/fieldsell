package com.jacek.fieldsell.services;

import com.jacek.fieldsell.models.Team;
import com.jacek.fieldsell.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository repository;

    public Collection<Team> getAllTeams() {
        return repository.findAll();
    }

    public void addTeam(Team team) {
        repository.save(team);
    }

    public void deleteTeam(Team team) {
        repository.delete(team);
    }

    public void updateTeam(Team team) {
        repository.save(team);
    }

    public Optional<Team> getTeamById(Integer id) {
        return repository.findById(id);
    }

    public Optional<Team> getTeamByName(String name) {
        return repository.findByName(name);
    }
}
