package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

public class GenerateTeamController {

    private static TeamRepository teamRepository;

    public List<Team> team() {
        return teamRepository.getTeam();
    }

    public Team createTeam(int teamSize, listOfSkills) {

        Team team = new Team(teamSize, listOfSkills);

        return team;
    }
}

