package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addUsers();
        addSkills();
    }

    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        Skill skill = new Skill("Teste", "Teste");
        skillRepository.add(skill);
    }


    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here
        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole("HRM", AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole("Collaborator", AuthenticationController.ROLE_Collaborator);
        authenticationRepository.addUserRole("VFM", AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole("QAM", AuthenticationController.ROLE_QAM);
        authenticationRepository.addUserRole("GSM", AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserWithRole("HRM", "HRM@this.app", "admin",
                AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserWithRole("VFM", "VFM@this.app", "admin",
                AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserWithRole("QAM", "QAM@this.app", "admin",
                AuthenticationController.ROLE_QAM);
        authenticationRepository.addUserWithRole("GSM", "GSM@this.app", "admin",
                AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserWithRole("Collaborator", "colab@this.app", "admin",
                AuthenticationController.ROLE_Collaborator);
    }
}