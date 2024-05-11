package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap implements Runnable {

    List<Employee> m_Employees;

    //Add some task categories to the repository as bootstrap
    public void run() {
        addOrganization();
        addSkills();
        addJobs();
        addEmployee();
        addUsers();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("Musgo Sublime"); //nome da empresa

        organizationRepository.add(organization);
    }

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

    private void addEmployee() {
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();

        employeeRepository.add(new Employee("HRM", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "hrm@this.app", "CC", 12345678, 123456789, new Job("Manager HR", "Human Resources"),new ArrayList<>(){{add(new Skill("Manager","Manages"));}}, AuthenticationController.ROLE_HRM, Employee.setPasswordDefault()));
        employeeRepository.add(new Employee("VFM", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "vfm@this.app", "CC", 12345678, 123456789, new Job("Manager VF", "Vehicle Fleet"),new ArrayList<>(){{add(new Skill("Manager","Manages"));}}, AuthenticationController.ROLE_VFM, Employee.setPasswordDefault()));
        employeeRepository.add(new Employee("GSM", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "gsm@this.app", "CC", 12345678, 123456789, new Job("Manager GS", "Green Spaces"),new ArrayList<>(){{add(new Skill("Manager","Manages"));}}, AuthenticationController.ROLE_GSM, Employee.setPasswordDefault()));
        employeeRepository.add(new Employee("QAM", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "qam@this.app", "CC", 12345678, 123456789, new Job("Manager QA", "Software Quality Assessment Team"),new ArrayList<>(){{add(new Skill("Manager","Manages"));}}, AuthenticationController.ROLE_QAM, Employee.setPasswordDefault()));

        employeeRepository.add(new Employee("Alfredo", LocalDate.of(2000, 12, 04), LocalDate.of(2008, 10, 02), "Rua da casa", 912345678, "alfredo@this.app", "CC", 12345678, 123456789, new Job("Gardener", "Garden maintenance"), new ArrayList<>(){{add(new Skill("Light Vehicle Driver","Drives light vehicles"));}} ));

        m_Employees = employeeRepository.getAllEmployees();
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

        for (Employee employee : m_Employees) {
            if (employee.getRole().equals(AuthenticationController.ROLE_HRM)) {
                authenticationRepository.addUserWithRole(employee.getName(),employee.getEmail(), employee.getPassword(),
                        AuthenticationController.ROLE_HRM);
            }
            else if (employee.getRole().equals(AuthenticationController.ROLE_VFM)) {
                authenticationRepository.addUserWithRole(employee.getName(),employee.getEmail(), employee.getPassword(),
                        AuthenticationController.ROLE_VFM);
            }
            else if (employee.getRole().equals(AuthenticationController.ROLE_QAM)) {
                authenticationRepository.addUserWithRole(employee.getName(),employee.getEmail(), employee.getPassword(),
                        AuthenticationController.ROLE_QAM);
            }
            else if (employee.getRole().equals(AuthenticationController.ROLE_GSM)) {
                authenticationRepository.addUserWithRole(employee.getName(),employee.getEmail(), employee.getPassword(),
                        AuthenticationController.ROLE_GSM);
            }
            else {
                authenticationRepository.addUserWithRole(employee.getName(),employee.getEmail(), employee.getPassword(),
                        AuthenticationController.ROLE_Collaborator);
            }
        }
    }
}