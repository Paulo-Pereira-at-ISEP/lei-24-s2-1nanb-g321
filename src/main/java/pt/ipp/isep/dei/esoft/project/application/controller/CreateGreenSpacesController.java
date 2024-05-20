package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

public class CreateGreenSpacesController {


        private GreenSpaceRepository greenSpaceRepository;
        private OrganizationRepository organizationRepository;
        private AuthenticationRepository authenticationRepository;

        //Repository instances are obtained from the Repositories class
        public CreateGreenSpacesController() {
            getOrganizationRepository();
            getGreenSpacesRepository();
            getAuthenticationRepository();
        }

        //Allows receiving the repositories as parameters for testing purposes
        public CreateGreenSpacesController(OrganizationRepository organizationRepository,
                                   GreenSpaceRepository greenSpaceRepository,
                                   AuthenticationRepository authenticationRepository) {
            this.organizationRepository = organizationRepository;
            this.greenSpaceRepository = greenSpaceRepository;
            this.authenticationRepository = authenticationRepository;
        }

        private GreenSpaceRepository getGreenSpaceRepository() {
            if (greenSpaceRepository == null) {
                Repositories repositories = Repositories.getInstance();

                //Get the TaskCategoryRepository
                greenSpaceRepository = repositories.getGreenSpaceRepository();
            }
            return greenSpaceRepository;
        }

        private OrganizationRepository getOrganizationRepository() {
            if (organizationRepository == null) {
                Repositories repositories = Repositories.getInstance();
                organizationRepository = repositories.getOrganizationRepository();
            }
            return organizationRepository;

        }
    private GreenSpaceRepository getGreenSpacesRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
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

        public CreateGreenSpacesController(GreenSpaceRepository greenSpaceRepository) {
            this.greenSpaceRepository = greenSpaceRepository;
        }

        public List<GreenSpace> getAllGreenSpaces() {
            return greenSpaceRepository.getGreenSpaces();
        }

        public GreenSpace createGreenSpace(String name, double area, Employee employee ) {

            GreenSpace newGreenSpace = new GreenSpace(name, area, employee);

            greenSpaceRepository.add(newGreenSpace);

            return newGreenSpace;
        }
    }
