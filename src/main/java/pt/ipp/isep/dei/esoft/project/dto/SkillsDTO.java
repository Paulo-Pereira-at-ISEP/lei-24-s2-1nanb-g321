package pt.ipp.isep.dei.esoft.project.dto;

import java.util.ArrayList;

public class SkillsDTO {
    private ArrayList<String> skills = new ArrayList<>();

    public SkillsDTO(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

}
