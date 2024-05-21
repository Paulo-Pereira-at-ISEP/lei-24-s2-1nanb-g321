package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;

public class TeamDTO {
    private int id;
    private ArrayList<CollaboratorDTO> members = new ArrayList<>();

    public TeamDTO(int id, ArrayList<CollaboratorDTO> members) {
        this.id = id;
        this.members = members;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<CollaboratorDTO> getMembers() {
        return members;
    }
    public void setMembers(ArrayList<CollaboratorDTO> members) {
        this.members = members;
    }
}
