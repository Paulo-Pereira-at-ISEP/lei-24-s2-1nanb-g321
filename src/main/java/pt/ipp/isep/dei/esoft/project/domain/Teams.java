package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

public class Teams {
    private int teamSize;
    public SkillRepository listOfSkills;

    public Teams(int teamSize, SkillRepository listOfSkills) {
        this.teamSize = teamSize;
        this.listOfSkills = listOfSkills;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}
