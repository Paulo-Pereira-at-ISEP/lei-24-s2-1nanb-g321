package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Bootstrap implements Runnable {

    List<Manager> m_Managers = new ArrayList<>();
    ArrayList<Collaborator> m_Collaborators = new ArrayList<>();
    private static String PASSWORD = "admin";

    //Add some task categories to the repository as bootstrap
    public void run() {
        //addOrganization();
        addSkills();
        addJobs();
        addManager();
        addCollaborator();
        addUsers();
        addTasks();
        addGreenSpace();
        addTeam();
    }
/*
    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("Musgo Sublime"); //nome da empresa

        organizationRepository.add(organization);
    }

 */



    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        skillRepository.add(new Skill("Light Vehicle Driver", "Drives light vehicles"));
        skillRepository.add(new Skill("Heavy Vehicle Driver", "Drives heavy vehicles"));
        skillRepository.add(new Skill("Machine Operator", "Operates machines such as backhoes or tractors"));
        skillRepository.add(new Skill("Tree Pruner", "Prunes trees"));
        skillRepository.add(new Skill("Phytopharmaceutical applicator", "Applies agriculture phytopharmaceuticals to the crops"));

    }

    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        jobRepository.add(new Job("Designer", "Creates plans and ideas for products or visuals or experiences"));
        jobRepository.add(new Job("Budget Manager", "Plans and tracks company spending"));
        jobRepository.add(new Job("Gardener", "Maintains the gardens"));
        jobRepository.add(new Job("Electrician", "Installs and maintains and fixes electrical systems in buildings"));
        jobRepository.add(new Job("Bricklayer", "Builds walls and structures by laying bricks with mortar"));

    }

    private void addTasks(){
        TaskRepository taskRepository = Repositories.getInstance().getTaskRepository();

        taskRepository.add(new Task("Cut Grass", "Cuts grass with a machine"));
        taskRepository.add(new Task("Trash Collection", "Picks up litter and debris to keep the green space clean"));
        taskRepository.add(new Task("Weeding", "Remove unwanted plants that can crowd out desired vegetation"));
        taskRepository.add(new Task("Trail Maintenance", "Clears fallen branches, repair paths, or build new ones"));

    }

    private void addGreenSpace(){
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();

        greenSpaceRepository.add(new GreenSpace("Avioso", 20000, "Large-Sized Park", m_Managers.get(2)));
        greenSpaceRepository.add(new GreenSpace("Parque Mágico", 200, "Garden", m_Managers.get(4)));
        greenSpaceRepository.add(new GreenSpace("Parque Assombrado", 5000, "Medium-Sized Park", m_Managers.get(4)));

    }

    private void addManager() {
        ManagerRepository managerRepository = Repositories.getInstance().getManagerRepository();

        managerRepository.add(new Manager("HRM", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "hrm@this.app", "CC", 12345678, 123456789, new Job("Manager HR", "Human Resources"), PASSWORD, AuthenticationController.ROLE_HRM,"HRM"));
        managerRepository.add(new Manager("VFM", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "vfm@this.app", "CC", 12345678, 123456789, new Job("Manager VF", "Vehicle Fleet"),PASSWORD, AuthenticationController.ROLE_VFM, "VFM"));
        managerRepository.add(new Manager("GSM_1", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "gsm@this.app", "CC", 12345678, 123456789, new Job("Manager GS", "Green Spaces"),PASSWORD, AuthenticationController.ROLE_GSM, "GSM"));
        managerRepository.add(new Manager("QAM", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "qam@this.app", "CC", 12345678, 123456789, new Job("Manager QA", "Software Quality Assessment Team"),PASSWORD, AuthenticationController.ROLE_QAM, "QAM"));
        managerRepository.add(new Manager("GSM_2", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "gsm2@this.app", "CC", 12345678, 123456789, new Job("Manager GS", "Green Spaces"),PASSWORD, AuthenticationController.ROLE_GSM, "GSM"));

        m_Managers = managerRepository.getAllManagers();

    }

    private void addCollaborator() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        collaboratorRepository.add(new Collaborator("Alfredo", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "alfredo@this.app", "CC", 12345678, 123456789, new Job("Gardener", "Garden maintenance"), PASSWORD ,AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Light Vehicle Driver","Drives light vehicles"));add(new Skill("Heavy Vehicle Driver","Drives heavy vehicles"));}} ));
        collaboratorRepository.add(new Collaborator("Anacleto", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "anacleto@this.app", "CC", 12345678, 123456789, new Job("Gardener", "Garden maintenance"), PASSWORD ,AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Light Vehicle Driver","Drives light vehicles"));add(new Skill("Machine Operator", "Operates machines such as backhoes or tractors"));}} ));
        collaboratorRepository.add(new Collaborator("Genoveva", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "genoveva@this.app", "CC", 12345678, 123456789, new Job("Gardener", "Garden maintenance"), PASSWORD ,AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Light Vehicle Driver","Drives light vehicles"));}} ));
        collaboratorRepository.add(new Collaborator("Bianca", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "bianca@this.app", "CC", 12345678, 123456789, new Job("Gardener", "Garden maintenance"), PASSWORD ,AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Light Vehicle Driver","Drives light vehicles"));}} ));
        collaboratorRepository.add(new Collaborator("Laurindo", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "laurindo@this.app", "CC", 12345678, 123456789, new Job("Gardener", "Garden maintenance"), PASSWORD ,AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Light Vehicle Driver","Drives light vehicles"));}} ));
        collaboratorRepository.add(new Collaborator("Beatriz", LocalDate.of(1995, 05, 12), LocalDate.of(2015, 01, 07), "Rua da paz", 987654321, "beatriz@this.app", "CC", 12345678, 123456789, new Job("Bricklayer", "Builds walls and structures by laying bricks with mortar"), PASSWORD, AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Machine Operator", "Operates machines such as backhoes or tractors"));add(new Skill("Heavy Vehicle Driver","Drives heavy vehicles"));}} ));
        collaboratorRepository.add(new Collaborator("Helena", LocalDate.of(1995, 07, 24), LocalDate.of(2017, 01, 07), "Rua da paz", 987654321, "helena@this.app", "CC", 12345678, 123456789, new Job("Bricklayer", "Builds walls and structures by laying bricks with mortar"), PASSWORD, AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Electrical Wiring", "Installs and repairs electrical wiring"));add(new Skill("Tree Pruner", "Prunes trees"));}} ));
        collaboratorRepository.add(new Collaborator("José", LocalDate.of(1995, 05, 12), LocalDate.of(2015, 01, 07), "Rua da paz", 987654321, "jose@this.app", "CC", 12345678, 123456789, new Job("Designer", "Creates plans and ideas for products or visuals or experiences"), PASSWORD, AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Phytopharmaceutical applicator", "Applies agriculture phytopharmaceuticals to the crops"));add(new Skill("Tree Pruner", "Prunes trees"));}} ));
        collaboratorRepository.add(new Collaborator("Bruno", LocalDate.of(1995, 05, 12), LocalDate.of(2015, 01, 07), "Rua da paz", 987654321, "bruno@this.app", "CC", 12345678, 123456789, new Job("Budget Manager", "Plans and tracks company spending"), PASSWORD, AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Electrical Wiring", "Installs and repairs electrical wiring"));add(new Skill("Phytopharmaceutical applicator", "Applies agriculture phytopharmaceuticals to the crops"));}} ));
        collaboratorRepository.add(new Collaborator("Joana", LocalDate.of(1995, 05, 12), LocalDate.of(2015, 01, 07), "Rua da paz", 987654321, "joana@this.app", "CC", 12345678, 123456789, new Job("Electrician", "Installs and maintains and fixes electrical systems in buildings"), PASSWORD, AuthenticationController.ROLE_Collaborator, new ArrayList<>(){{add(new Skill("Tree Pruner", "Prunes trees"));add(new Skill("Light Vehicle Driver","Drives light vehicles"));}} ));

        m_Collaborators = (ArrayList<Collaborator>) collaboratorRepository.getAllCollaborators();

    }

    private void addTeam(){
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        teamRepository.addTeam(new Team(m_Collaborators));



    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        //---------------Eliminar posteriormente------------------------
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        //---------------------------------------------------------------

        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_Collaborator, AuthenticationController.ROLE_Collaborator);

        for (Manager manager : m_Managers) {
            if (manager.getRole().equals(AuthenticationController.ROLE_HRM)) {
                authenticationRepository.addUserWithRole(manager.getName(),manager.getEmail(), manager.getPassword(),
                        AuthenticationController.ROLE_HRM);
            }
            else if (manager.getRole().equals(AuthenticationController.ROLE_VFM)) {
                authenticationRepository.addUserWithRole(manager.getName(),manager.getEmail(), manager.getPassword(),
                        AuthenticationController.ROLE_VFM);
            }
            else if (manager.getRole().equals(AuthenticationController.ROLE_QAM)) {
                authenticationRepository.addUserWithRole(manager.getName(),manager.getEmail(), manager.getPassword(),
                        AuthenticationController.ROLE_QAM);
            }
            else if (manager.getRole().equals(AuthenticationController.ROLE_GSM)) {
                authenticationRepository.addUserWithRole(manager.getName(),manager.getEmail(), manager.getPassword(),
                        AuthenticationController.ROLE_GSM);
            }
            else {
                authenticationRepository.addUserWithRole(manager.getName(),manager.getEmail(), manager.getPassword(),
                        AuthenticationController.ROLE_Collaborator);
            }
        }

        for (Collaborator collaborator : m_Collaborators) {
            if (collaborator.getRole().equals(AuthenticationController.ROLE_Collaborator)) {
                authenticationRepository.addUserWithRole(collaborator.getName(),collaborator.getEmail(), collaborator.getPassword(),
                        AuthenticationController.ROLE_Collaborator);
            }
        }
    }
}