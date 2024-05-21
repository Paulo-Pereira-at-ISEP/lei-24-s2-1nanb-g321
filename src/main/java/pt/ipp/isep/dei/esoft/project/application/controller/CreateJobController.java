package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

public class CreateJobController {

    private JobRepository jobRepository;
    //private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;

    //Repository instances are obtained from the Repositories class
    public CreateJobController() {
        //getOrganizationRepository();
        getJobRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateJobController(
                           JobRepository jobRepository,
                           AuthenticationRepository authenticationRepository) {
        //this.organizationRepository = organizationRepository;
        this.jobRepository = jobRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }
/*
    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

 */

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private Manager getManagerFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Manager(email.getEmail());
    }

    public CreateJobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.getJobs();
    }

    public Job createJob(String name, String description) {

        Job newJob = new Job(name, description);

        jobRepository.add(newJob);

        return newJob;
    }
}