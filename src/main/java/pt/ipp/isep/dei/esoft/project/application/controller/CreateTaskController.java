package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskController {

    private TaskRepository taskRepository;
    private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;
    private EmployeeRepository employeeRepository;
    //Repository instances are obtained from the Repositories class
    public CreateTaskController() {
        getOrganizationRepository();
        getTaskRepository();
        getAuthenticationRepository();
        getEmployeeRepository();
        getTaskRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateTaskController(OrganizationRepository organizationRepository,
                                 SkillRepository skillRepository,
                                 AuthenticationRepository authenticationRepository, EmployeeRepository employeeRepository) {
        this.organizationRepository = organizationRepository;
        this.taskRepository = taskRepository;
        this.authenticationRepository = authenticationRepository;
        this.employeeRepository = employeeRepository;
    }

    private TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            taskRepository = repositories.getTaskRepository();
        }
        return taskRepository;
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

    public CreateTaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
    public Task createTask(String name, String description) {

        Task newTask = new Task(name, description);

        taskRepository.add(newTask);

        return newTask;
    }
}