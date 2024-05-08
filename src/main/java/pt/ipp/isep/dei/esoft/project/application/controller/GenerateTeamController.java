package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

public class GenerateTeamController {

    private static EmployeeRepository employeeRepository;
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

    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the Team Repository
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    public ArrayList<Skill> getAllSkills() {
        return skillRepository.getAllSkills();
    }

    public List<Employee> employee() {
        return employeeRepository.getEmployees();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Team generateTeam(int teamMaxSize, int teamMinSize, ArrayList<Skill> skills) {

        Team team = new Team(teamMaxSize, teamMinSize, skills);

        teamRepository.add(team);

        return team;
    }
}
