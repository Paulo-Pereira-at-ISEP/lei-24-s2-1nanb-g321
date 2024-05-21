package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private List<Collaborator> collaborators;

    public CollaboratorRepository() {
        this.collaborators = new ArrayList<>();
    }

    public void addCollaborator(Collaborator collaborator) {
        collaborators.add(collaborator);
    }

    public List<Collaborator> getAllCollaborators() {
        return new ArrayList<>(collaborators);
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        boolean isValid = !collaborators.contains(collaborator);
        return isValid;
    }
    
    public List<Collaborator> getCollaborators() {
        ArrayList<Collaborator> copy = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            copy.add(collaborator.clone());
        }
        return copy;
    }
    
    public Collaborator getCollaboratorByEmail(String email) {

        for (Collaborator collaborator : collaborators) {
            if (collaborator.getEmail().equals(email)){
                return collaborator;
            }
        }
        return null;
    }
    
    public Optional<Collaborator> add(Collaborator collaborator) {

        Optional<Collaborator> newCollaborator = Optional.empty(); // Initialize an empty Optional
        boolean operationSuccess = false;

        // Validate the employee
        if (validateCollaborator(collaborator)) {
            // If validation succeeds, create a clone of the employee and attempt to add it to the list
            newCollaborator = Optional.of(collaborator.clone());
            operationSuccess = collaborators.add(newCollaborator.get());
        }

        // If the operation was not successful, return an empty Optional
        if (!operationSuccess) {
            newCollaborator = Optional.empty();
        }

        return newCollaborator; // Return the Optional containing the added employee or an empty Optional
    }

}