package pt.ipp.isep.dei.esoft.project.dto;

import java.util.ArrayList;

public class CollaboratorDTO {
    private String name;
    private String email;
    private ArrayList<SkillsDTO> skills = new ArrayList<>();

    public CollaboratorDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public ArrayList<SkillsDTO> getSkills() {
        return skills;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSkills(ArrayList<SkillsDTO> skills) {
        this.skills = skills;
    }

}
