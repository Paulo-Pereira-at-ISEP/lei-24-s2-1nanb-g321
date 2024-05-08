package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateEmployeeController {

    private static EmployeeRepository employeeRepository;
    private JobRepository jobRepository;

    private SkillRepository skillRepository;
    private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;

    //Repository instances are obtained from the Repositories class
    public CreateEmployeeController() {
        getAuthenticationRepository();
        getOrganizationRepository();
        getJobRepository();
        getSkillRepository();
        getEmployeeRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateEmployeeController(OrganizationRepository organizationRepository,
                               JobRepository jobRepository,
                               AuthenticationRepository authenticationRepository, SkillRepository skillRepository) {
        this.organizationRepository = organizationRepository;
        this.jobRepository = jobRepository;
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
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
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
    public List<Employee> employee() {
        return employeeRepository.getEmployees();
    }

    public List<Job> getAllJobs(){
        return jobRepository.getAllJobs();
    }
    public ArrayList<Skill> getAllSkills() {
        return skillRepository.getAllSkills();
    }

    public Employee createEmployee(String name, LocalDate birthdate, LocalDate admissionDate, String adress,
                                   int mobile, String email, String docType, int docNumber, int taxPayerId, Job job, ArrayList<Skill> skill) {

        Employee employee = new Employee(name, birthdate, admissionDate, adress, mobile, email, docType, docNumber, taxPayerId, job, skill);

        employeeRepository.add(employee);

        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }
}
