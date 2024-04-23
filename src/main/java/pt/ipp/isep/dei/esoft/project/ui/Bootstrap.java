package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addOrganization();
        addUsers();
        addSkills();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");

        //---------------Eliminar posteriormente------------------------
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        //---------------------------------------------------------------

        organization.addEmployee(new Employee("HRM@this.app"));
        organization.addEmployee(new Employee("VFM@this.app"));
        organization.addEmployee(new Employee("QAM@this.app"));
        organization.addEmployee(new Employee("GSM@this.app"));
        organization.addEmployee(new Employee("colab@this.app"));

        organizationRepository.add(organization);
    }

    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        Skill skill = new Skill("Skill", "Primeira Skill");

        skillRepository.add(skill);
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

        //---------------Eliminar posteriormente------------------------
        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);
        //---------------------------------------------------------------

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