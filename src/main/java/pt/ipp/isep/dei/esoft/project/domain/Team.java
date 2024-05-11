package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> skills;
    private List<Employee> employees;


    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {
        this.teamMaxSize = teamMaxSize;
        this.teamMinSize = teamMinSize;
        this.skills = skills;
    }

    public Team(List<Employee> employees) {
        this.employees = employees;
    }


    public int getTeamMaxSize() {
        return teamMaxSize;
    }

    public int getTeamMinSize() {
        return teamMinSize;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public List<Employee> getEmployees() {
        return employees;
    }


    public void setTeamMaxSize(int teamMaxSize) {
        this.teamMaxSize = teamMaxSize;
    }

    public void setTeamMinSize(int teamMinSize) {
        this.teamMinSize = teamMinSize;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> generateTeam(List<Employee> listOfEmployees) {

        Integer[] scores=new Integer[listOfEmployees.size()];

        List<Employee> employees1 = sortEmployeesBySkillScore(scores, listOfEmployees);

        this.employees=employees1;

        return employees;
    }

    private List<Employee> sortEmployeesBySkillScore(Integer[] scores, List<Employee> listOfEmployees) {
        int scoreAux;

        List<Employee> employees1 = new ArrayList<>(listOfEmployees);

        //cria um array de scores
        for (Employee employee : employees1) {
            scoreAux = 0;
            for (Skill skill : skills) {

                //verificar se o employee tem a skill
                //se sim, incrementa score no vetor scores na posição employees.indexOf(employee)
                if (employee.getSkills().contains(skill)) {
                    scoreAux++;
                }

            }

            scores[employees1.indexOf(employee)] = scoreAux;
        }


        Employee employeeCopy;
        int scoreCopy;


        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores.length - 1 - i; j++) {

                if (scores[j] < scores[j + 1]) {

                    scoreCopy = scores[j];
                    scores[j]=scores[j+1];
                    scores[j+1]=scoreCopy;


                    employeeCopy= employees1.get(j);
                    employees1.set(j, employees1.get(j+1));
                    employees1.set(j+1, employeeCopy);

                }

            }
        }
        return employees1;
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
