package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import java.util.List;

public class CreateSkillController {

    private SkillRepository skillRepository;
    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    //Repository instances are obtained from the Repositories class
    public CreateSkillController() {
        getSkillRepository();
        getAuthenticationRepository();
        getCollaboratorRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateSkillController(CollaboratorRepository collaboratorRepository,
                               SkillRepository skillRepository,
                               AuthenticationRepository authenticationRepository) {
        this.skillRepository = skillRepository;
        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public CreateSkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getSkills();
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getAllCollaborators();
    }

    public Skill createSkill(String name, String description) {

        Skill newSkill = new Skill(name, description);

        skillRepository.add(newSkill);

        return newSkill;
    }
}