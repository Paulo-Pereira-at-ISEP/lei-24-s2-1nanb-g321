package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.*;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> listOfSkills;
    private List<EmployeeRepository> employeeRepository;


    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> listOfSkills) {
        this.teamMaxSize = teamMaxSize;
        this.teamMinSize = teamMinSize;
        this.listOfSkills = listOfSkills;
    }

    public String toString() {
        return "Team Members: " + "\nTeam Size: " + "\nSkills: " + listOfSkills;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Team clone() {
        return new Team(this.teamMinSize, this.teamMaxSize, this.listOfSkills);
    }

}