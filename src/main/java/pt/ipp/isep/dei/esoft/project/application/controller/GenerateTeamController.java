package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

public class GenerateTeamController {

    private static CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;
    private AuthenticationRepository authenticationRepository;
    private TeamRepository teamRepository;

    //Repository instances are obtained from the Repositories class
    public GenerateTeamController() {
        getAuthenticationRepository();
        getSkillRepository();
        getEmployeeRepository();
        getTeamRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public GenerateTeamController(AuthenticationRepository authenticationRepository, SkillRepository skillRepository) {
        this.authenticationRepository = authenticationRepository;
        this.skillRepository = skillRepository;
    }

    private CollaboratorRepository getEmployeeRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    public ArrayList<Skill> getAllSkills() {
        return skillRepository.getAllSkills();
    }

    public List<Collaborator> collaborator() {
        return collaboratorRepository.getCollaborators();
    }

    public List<Team> getAllTeams() {
        return teamRepository.getTeams();
    }

    public int getTeamId(Team team) {
        return team.getId(); // Assuming Team class has a getId() method
    }

    public Team createTeam(int teamMaxSize, int teamMinSize, ArrayList<Skill> skills) {

        ArrayList<Collaborator> team = Team.createSuperTeam(teamMaxSize, teamMinSize, skills, collaboratorRepository.getCollaboratorsWithoutTeam());

        return new Team(team);
    }

    public Team createSecondTeam(int teamMaxSize, int teamMinSize, ArrayList<Skill> skills, Team team) {
        ArrayList<Collaborator> team2 = Team.createSuperTeam2(teamMaxSize, teamMinSize, skills, collaboratorRepository.getCollaboratorsWithoutTeam(), team);

        return new Team(team2);
    }

    public Team addToRepository(Team team) {
        teamRepository.addTeam(team);
        return team;
    }

    public void colaboratorHasTeam(Team team) {
        for (Collaborator collaborator : team.getCollaborators()) {
            collaboratorRepository.hasTeam(collaborator);
        }
    }

}
