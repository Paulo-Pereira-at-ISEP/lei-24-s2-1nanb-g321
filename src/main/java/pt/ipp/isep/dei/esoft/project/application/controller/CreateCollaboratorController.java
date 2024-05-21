package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateCollaboratorController {

    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;
    private AuthenticationRepository authenticationRepository;
    private SkillRepository skillRepository;

    //Repository instances are obtained from the Repositories class
    public CreateCollaboratorController() {
        getAuthenticationRepository();
        getJobRepository();
        getCollaboratorRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateCollaboratorController(CollaboratorRepository collaboratorRepository,
                                        JobRepository jobRepository,
                                        SkillRepository skillRepository,
                                        AuthenticationRepository authenticationRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
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

    public List<Skill> getAllSkills(){
        return skillRepository.getAllSkills();
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getCollaborators();
    }

    public Collaborator addCollaborator(String name, LocalDate dateOfBirth, LocalDate admissionDate, String address, int mobile, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, String password, String role, ArrayList<Skill> skills) {
        Collaborator collaborator = new Collaborator(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, skills);
        collaboratorRepository.addCollaborator(collaborator);
        return collaborator;
    }
}
