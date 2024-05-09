package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.util.*;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> skills;
    private EmployeeRepository employeeRepository;

    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {
        this.teamMaxSize = teamMaxSize;
        this.teamMinSize = teamMinSize;
        this.skills = skills;
    }

    public String toString() {
        return "Team Members: " + "\nTeam Size: " + "\nSkills: " + skills;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Team clone() {
        return new Team(this.teamMinSize, this.teamMaxSize, this.skills);
    }

}