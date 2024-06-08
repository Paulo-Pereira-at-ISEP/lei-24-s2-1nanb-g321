package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
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
        }else{
            this.collaborators = result;
        }
    }
    public void serialize(){
        Serialize.serialize(collaborators,Serialize.FOLDER_PATH + File.separator + "collaborators.ser");}

    public void addCollaborator(Collaborator collaborator) {
        if (collaborator == null) {
            throw new NullPointerException("Collaborator cannot be null");
        }
        if (validateCollaborator(collaborator)) {
            collaborators.add(collaborator);
            serialize();
        }
    }

    public List<Collaborator> getAllCollaborators() {
        return new ArrayList<>(collaborators);
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        boolean isValid = !collaborators.contains(collaborator);
        return isValid;
    }
    
    public ArrayList<Collaborator> getCollaborators() {
        ArrayList<Collaborator> copy = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            copy.add(collaborator.clone());
        }
        return copy;
    }

    public ArrayList<Collaborator> getCollaboratorsWithoutTeam() {
        ArrayList<Collaborator> copy = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            if(!collaborator.getHasTeam()){
                copy.add(collaborator.clone());
            }
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

    public void hasTeam(Collaborator collaborator) {
        if (collaborators.contains(collaborator)) {
            collaborators.get(collaborators.indexOf(collaborator)).setHasTeam(true);
        }
    }

}