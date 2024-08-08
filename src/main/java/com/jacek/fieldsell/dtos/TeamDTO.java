package com.jacek.fieldsell.dtos;

import com.jacek.fieldsell.models.Team;

import java.util.List;

public record TeamDTO(String name, List<String> members) {

    public static TeamDTO fromModel(Team team) {
        return new TeamDTO(team.getName(), team.getMembers());
    }

}
