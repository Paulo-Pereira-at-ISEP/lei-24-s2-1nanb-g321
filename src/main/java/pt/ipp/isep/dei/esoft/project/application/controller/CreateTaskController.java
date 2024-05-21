package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

public class CreateTaskController {

    private TaskRepository taskRepository;
    private AuthenticationRepository authenticationRepository;
    private ManagerRepository managerRepository;

    //Repository instances are obtained from the Repositories class
    public CreateTaskController() {
        getTaskRepository();
        getAuthenticationRepository();
        getManagerRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateTaskController(SkillRepository skillRepository,
                                 AuthenticationRepository authenticationRepository,
                                ManagerRepository managerRepository
    ) {
        this.taskRepository = taskRepository;
        this.authenticationRepository = authenticationRepository;
        this.managerRepository = managerRepository;
    }

    private TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            taskRepository = repositories.getTaskRepository();
        }
        return taskRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private ManagerRepository getManagerRepository() {
        if (managerRepository == null) {
            Repositories repositories = Repositories.getInstance();
        }
        return managerRepository;
    }

    private Manager getManagerFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Manager(email.getEmail());
    }

    public CreateTaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
    public List<Manager> getAllManagers() {
        return managerRepository.getAllManagers();
    }
    public Task createTask(String name, String description) {

        Task newTask = new Task(name, description);

        taskRepository.add(newTask);

        return newTask;
    }
}