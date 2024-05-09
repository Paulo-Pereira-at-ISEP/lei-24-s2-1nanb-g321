package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.util.*;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> skills;

    //private EmployeeRepository employeeRepository;

    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {
        this.teamMaxSize = teamMaxSize;
        this.teamMinSize = teamMinSize;
        this.skills = skills;
    }

    public Team generateTeam(List<Employee> employees){

        int scoreAux;

        //cria um array de scores
        Integer[] scores = new Integer[employees.size()];

        for (Employee employee : employees){
            scoreAux=0;
            for (Skill skill: skills){

                //verificar se o employee tem a skill
                    //se sim, incrementa score no vetor secores na posição employees.indexOf(employee)

                scoreAux++;


            }

            scores[employees.indexOf(employee)]=scoreAux;
        }

        //ordenar o array de scores e  (bubblesort)

        return null;
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