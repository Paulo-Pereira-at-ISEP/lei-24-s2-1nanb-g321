package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
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

    public Team generateTeam(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {

        Team team = new Team(teamMinSize, teamMaxSize, skills);

        team.generateTeam(collaboratorRepository.getCollaborators());

        // Apresentar equipa final
        ArrayList<Collaborator> teamFinal = new ArrayList<Collaborator>();

        for (Collaborator collaborator : team.getCollaborators()) {
            teamFinal.add(collaboratorRepository.getCollaboratorByEmail(collaborator.getEmail()));

        }
        team.setCollaborators(teamFinal);

        teamRepository.addTeam(team);
        return team;
    }

    public Team generateSecondTeam(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {

        Team team = new Team(teamMinSize, teamMaxSize, skills);

        team.generateSecondTeam(collaboratorRepository.getCollaborators());

        // Apresentar equipa final
        ArrayList<Collaborator> teamFinal = new ArrayList<Collaborator>();

        for (Collaborator collaborator : team.getCollaborators()) {
            teamFinal.add(collaboratorRepository.getCollaboratorByEmail(collaborator.getEmail()));

        }
        team.setCollaborators(teamFinal);

        return team;
    }

}
