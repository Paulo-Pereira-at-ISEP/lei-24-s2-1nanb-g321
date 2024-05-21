package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.List;

public class CreateManagerController {

    private ManagerRepository managerRepository;
    private JobRepository jobRepository;
    private AuthenticationRepository authenticationRepository;

    //Repository instances are obtained from the Repositories class
    public CreateManagerController() {
        getAuthenticationRepository();
        getJobRepository();
        getManagerRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateManagerController(ManagerRepository managerRepository, JobRepository jobRepository, AuthenticationRepository authenticationRepository) {
        this.managerRepository = managerRepository;
        this.jobRepository = jobRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private ManagerRepository getManagerRepository() {
        if (managerRepository == null) {
            Repositories repositories = Repositories.getInstance();
            managerRepository = repositories.getManagerRepository();
        }
        return managerRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public List<Job> getAllJobs(){
        return jobRepository.getAllJobs();
    }

    public List<Manager> getAllManagers() {
        return managerRepository.getManagers();
    }

    public Manager addManager(String name, LocalDate dateOfBirth, LocalDate admissionDate, String address, int mobile, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, String password, String department, String role) {
        Manager manager = new Manager(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, department, role);
        managerRepository.addManager(manager);
        return manager;
    }
}
