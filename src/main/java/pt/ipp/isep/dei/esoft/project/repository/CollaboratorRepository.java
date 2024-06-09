package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CollaboratorRepository {

    private List<Collaborator> collaborators;

    public CollaboratorRepository() {
        List<Collaborator> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator + "collaborators.ser");
        if(result == null){
            this.collaborators = new ArrayList<>();
        } else {
            this.collaborators = result;
        }
    }

    /**
     * Serializes the list of collaborators.
     */
    public void serialize() {
        Serialize.serialize(collaborators, Serialize.FOLDER_PATH + File.separator + "collaborators.ser");
    }

    /**
     * Adds a collaborator to the repository.
     *
     * @param collaborator The collaborator to add.
     */
    public void addCollaborator(Collaborator collaborator) {
        if (collaborator == null) {
            throw new NullPointerException("Collaborator cannot be null");
        }
        if (validateCollaborator(collaborator)) {
            collaborators.add(collaborator);
            serialize();
        }
    }

    /**
     * Retrieves all collaborators in the repository.
     *
     * @return A list containing all collaborators.
     */
    public List<Collaborator> getAllCollaborators() {
        return new ArrayList<>(collaborators);
    }

    /**
     * Validates a collaborator before adding it to the repository.
     *
     * @param collaborator The collaborator to validate.
     * @return True if the collaborator is valid and can be added, false otherwise.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * Retrieves a defensive (immutable) copy of the list of collaborators.
     *
     * @return The list of collaborators.
     */
    public List<Collaborator> getCollaborators() {
        ArrayList<Collaborator> copy = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            copy.add(collaborator.clone());
        }
        return copy;
    }

    /**
     * Retrieves collaborators without a team.
     *
     * @return A list of collaborators without a team.
     */
    public ArrayList<Collaborator> getCollaboratorsWithoutTeam() {
        ArrayList<Collaborator> copy = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            if (!collaborator.getHasTeam()) {
                copy.add(collaborator.clone());
            }
        }
        return copy;
    }

    /**
     * Retrieves a collaborator by email.
     *
     * @param email The email of the collaborator.
     * @return The collaborator with the specified email, or null if not found.
     */
    public Collaborator getCollaboratorByEmail(String email) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getEmail().equals(email)){
                return collaborator;
            }
        }
        return null;
    }

    /**
     * Adds a collaborator to the repository and returns an optional containing the added collaborator.
     *
     * @param collaborator The collaborator to add.
     * @return An optional containing the added collaborator if successful, otherwise an empty optional.
     */
    public Optional<Collaborator> add(Collaborator collaborator) {
        if (collaborator == null) {
            throw new NullPointerException("Collaborator cannot be null");
        }
        if (validateCollaborator(collaborator)) {
            Collaborator clonedCollaborator = collaborator.clone();
            if (collaborators.add(clonedCollaborator)) {
                serialize();
                return Optional.of(clonedCollaborator);
            }
        }
        return Optional.empty();
    }

    /**
     * Marks a collaborator as having a team.
     *
     * @param collaborator The collaborator to mark.
     */
    public void hasTeam(Collaborator collaborator) {
        if (collaborators.contains(collaborator)) {
            collaborators.get(collaborators.indexOf(collaborator)).setHasTeam(true);
        }
    }
}
