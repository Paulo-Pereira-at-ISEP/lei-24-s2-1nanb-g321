package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

public class CreateGreenSpacesController {

        private GreenSpaceRepository greenSpaceRepository;
        private AuthenticationRepository authenticationRepository;
        private ManagerRepository managerRepository;
        private AuthenticationController authenticationController;

        //Repository instances are obtained from the Repositories class
        public CreateGreenSpacesController() {
            getGreenSpacesRepository();
            getAuthenticationRepository();
            getManagerRepository();
        }

        //Allows receiving the repositories as parameters for testing purposes
        public CreateGreenSpacesController(GreenSpaceRepository greenSpaceRepository,
                                   AuthenticationRepository authenticationRepository) {
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

        private ManagerRepository getManagerRepository() {
            if (managerRepository == null) {
                Repositories repositories = Repositories.getInstance();
                managerRepository = repositories.getManagerRepository();
            }
            return managerRepository;
        }

        public List<Manager> getAllManagers() {
            return getManagerRepository().getAllManagers();
        }

        public List<Manager> getManagerWithRoleGSM() {
            return managerRepository.getManagerByRole(AuthenticationController.ROLE_GSM);
        }

        public CreateGreenSpacesController(GreenSpaceRepository greenSpaceRepository) {
            this.greenSpaceRepository = greenSpaceRepository;
        }

        public List<GreenSpace> getAllGreenSpaces() {
            return greenSpaceRepository.getGreenSpaces();
        }

        public List<GreenSpace> getGreenSpacesManaged(String email) {
        return greenSpaceRepository.getGreenSpacesManaged(email);
    }

        public GreenSpace createGreenSpace(String name, double area, String classification, Manager manager ) {

            GreenSpace newGreenSpace = new GreenSpace(name, area, classification, manager);

            greenSpaceRepository.add(newGreenSpace);

            return newGreenSpace;
        }
    }
