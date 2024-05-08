package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

public class CreateSkillController {

    private SkillRepository skillRepository;
    private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;
    private EmployeeRepository employeeRepository;
    //Repository instances are obtained from the Repositories class
    public CreateSkillController() {
        getOrganizationRepository();
        getSkillRepository();
        getAuthenticationRepository();
        getEmployeeRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateSkillController(OrganizationRepository organizationRepository,
                               SkillRepository skillRepository,
                               AuthenticationRepository authenticationRepository, EmployeeRepository employeeRepository) {
        this.organizationRepository = organizationRepository;
        this.skillRepository = skillRepository;
        this.authenticationRepository = authenticationRepository;
        this.employeeRepository = employeeRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }
    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private Employee getEmployeeFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Employee(email.getEmail());
    }

    public CreateSkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getSkills();
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
    public Skill createSkill(String name, String description) {

        Skill newSkill = new Skill(name, description);

        skillRepository.add(newSkill);

        return newSkill;
    }
}