package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.util.*;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> skills;
    List<Employee> employees;


    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {
        this.teamMaxSize = teamMaxSize;
        this.teamMinSize = teamMinSize;
        this.skills = skills;
    }

    public Team(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> generateTeam(List<Employee> employees) {

        int scoreAux;

        //cria um array de scores
        Integer[] scores = new Integer[employees.size()];
        for (Employee employee : employees) {
            scoreAux = 0;
            for (Skill skill : skills) {

                //verificar se o employee tem a skill
                //se sim, incrementa score no vetor scores na posição employees.indexOf(employee)
                if (employee.getSkills().contains(skill)) {
                    scoreAux++;
                }

            }

            scores[employees.indexOf(employee)] = scoreAux;
        }

        int[] employeeTaxPayerId = new int[employees.size()];

        for (Employee employee : employees) {
            employeeTaxPayerId[employees.indexOf(employee)] = employee.getTaxPayerIdNumber();
        }





        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[i - 1]) {
                int posicaoAnterior = i - 1;
                int scoreAnterior = scores[i - 1];

                scores[posicaoAnterior] = scores[i];
                scores[i] = scoreAnterior;

                int employeeAnterior = employeeTaxPayerId[i - 1];

                employeeTaxPayerId[posicaoAnterior] = employeeTaxPayerId[i];
                employeeTaxPayerId[i] = employeeAnterior;

            }

        }


        return null;
    }


            public String toString () {
                return "Team Members: " + "\nTeam Size: " + "\nSkills: " + skills;
            }

            /**
             * Clone method.
             *
             * @return A clone of the current instance.
             */
            public Team clone () {
                return new Team(this.teamMinSize, this.teamMaxSize, this.skills);
            }


        }
