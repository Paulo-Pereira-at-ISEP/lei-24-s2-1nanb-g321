package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

public class Team {
    private String teamMembers;
    private int teamSize;
    public SkillRepository listOfSkills;

    public Team(String teamMembers, int teamSize, SkillRepository listOfSkills) {
        this.teamMembers = teamMembers;
        this.teamSize = teamSize;
        this.listOfSkills = listOfSkills;
    }

    public String toString(){
        return "Team Members: " + teamMembers + "\nTeam Size: " + teamSize + "\nSkills: " + listOfSkills;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}
