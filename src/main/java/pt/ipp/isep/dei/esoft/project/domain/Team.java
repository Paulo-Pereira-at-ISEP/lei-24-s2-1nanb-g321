package pt.ipp.isep.dei.esoft.project.domain;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Collections.sort;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();


    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {
        this.teamMinSize = teamMinSize;
        this.teamMaxSize = teamMaxSize;
        this.skills = skills;
    }

    public Team(ArrayList<Employee> employees) {
        this.employees = employees;
    }


    public int getTeamMaxSize() {
        return teamMaxSize;
    }

    public int getTeamMinSize() {
        return teamMinSize;
    }

    public List<Skill> getSkills() {
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

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    /**
     * Attempts to generate a team from a list of employees based on sorted emplyees by skill sets.
     *
     * @param listOfEmployees The list of `Employee` objects representing all available employees.
     * @return A new `List` of `Employee` objects representing the generated team.
     *
     *
     * @throws NullPointerException if the `listOfEmployees` parameter is null.
     *
     * @implNote This method attempts to create a team with a minimum size (`teamMinSize`)
     *          by considering the skills specified in the `skills` field.
     *          It iterates through the skills, searching for employees with those skills.
     *          - If an employee with the desired skill is already in the team, it removes the skill
     *            from that employee to avoid duplicates.
     *          - Otherwise, it prioritizes selecting employees from the pre-sorted list (`employeesSorted`).
     *          - If no suitable employee is found, it prints a message indicating team creation failed.
     */
    public List<Employee> generateTeam(List<Employee> listOfEmployees) {

        Integer[] scores = new Integer[listOfEmployees.size()];


        ArrayList<Employee> employeesSorted = sortEmployeesBySkillScore(scores, listOfEmployees);

        for (Skill skill : skills) {

            //pesquisa no 1 vetor
            Employee current = hasSkill(employees, skill);

            //procura no vetor da equipa
            //se encontra, remove a skill do employee encontrado
            //senao
            //procura no vetor dos employees ordenado
            //se encontrado adiciona a equipa
            if (current != null) {
                var skills = current.getSkills();
                skills.remove(skill);
                current.setSkills(skills);


            } else {
                //pesquisa no 2 vetor

                current = hasSkill(employeesSorted, skill);
                if (current != null) {
                    var skills = current.getSkills();
                    skills.remove(skill);
                    current.setSkills(skills);
                    employees.add(current);
                } else {
                    System.out.println("Team not created! ");
                }
            }

            int dif = teamMinSize - employees.size();
            int i = 0;
            while (dif != 0 && i < skills.size()) {
                Skill skill1 = skills.get(i);
                //percorre as skills
                //percorre os verifica se os employees tem a skill
                //se o employee devolvido ja estiver na equipa
                //remove o emplyee da lista de employees
                //procura novamente
                //quando encontrado
                //calcula o dif novamente
                Employee employeeToChoose = hasSkill(employeesSorted, skill1);

                if (employeeToChoose != null && employees.contains(employeeToChoose)) {
                    employeesSorted.remove(employeeToChoose);
                } else {
                    if (employeeToChoose != null)
                        employees.add(employeeToChoose);
                }
                i++;
                dif = teamMinSize - employees.size();
            }
        }
        return employees;
    }


    /**
     * Attempts to generate a team from a list of employees based on their skill sets,
     * similar to the `generateTeam` method.
     *
     * @param listOfEmployees The list of `Employee` objects representing all available employees.
     * @return A new `List` of `Employee` objects representing the generated team.
     *
     * @throws NullPointerException if the `listOfEmployees` parameter is null.
     *
     * @implNote This method attempts to create a team with a minimum size (`teamMinSize`)
     *          by considering the skills specified in the `skills` field.
     *          It iterates through the skills, searching for employees with those skills.
     *         - It iterates on a non-sorted employee list to generate a different team.

     */
    public List<Employee> generateSecondTeam(List<Employee> listOfEmployees) {


        for (Skill skill : skills) {

            //pesquisa no 1 vetor
            Employee current = hasSkill(employees, skill);

            //procura no vetor da equipa
            //se encontra, remove a skill do employee encontrado
            //senao
            //procura no vetor dos employees ordenado
            //se encontrado adiciona a equipa
            if (current != null) {
                var skills = current.getSkills();
                skills.remove(skill);
                current.setSkills(skills);


            } else {
                //pesquisa no 2 vetor

                current = hasSkill((ArrayList<Employee>) listOfEmployees, skill);
                if (current != null) {
                    var skills = current.getSkills();
                    skills.remove(skill);
                    current.setSkills(skills);
                    employees.add(current);
                } else {
                    System.out.println("Team not created! ");
                }
            }

            int dif = teamMinSize - employees.size();
            int i = 0;
            while (dif != 0 && i < skills.size()) {
                Skill skill1 = skills.get(i);
                //percorre as skills
                //percorre os verifica se os employees tem a skill
                //se o employee devolvido ja estiver na equipa
                //remove o emplyee da lista de employees
                //procura novamente
                //quando encontrado
                //calcula o dif novamente
                Employee employeeToChoose = hasSkill((ArrayList<Employee>) listOfEmployees, skill1);

                if (employeeToChoose != null && employees.contains(employeeToChoose)) {
                    listOfEmployees.remove(employeeToChoose);
                } else {
                    if (employeeToChoose != null)
                        employees.add(employeeToChoose);
                }
                i++;
                dif = teamMinSize - employees.size();
            }
        }
        return employees;
    }

    /**
     * Searches for an employee with a specific skill in a list of employees.
     *
     * @param employees A list of `Employee` objects.
     * @param skill The `Skill` object representing the skill to search for.
     * @return The first `Employee` object found with the specified skill,
     *         or null if no employee possesses that skill.
     */
    public Employee hasSkill(ArrayList<Employee> employees, Skill skill) {

        for (Employee employee : employees) {

            if (employee.getSkills().contains(skill)) {
                return employee;
            }
        }

        return null;
    }
    /**
     * Sorts a list of employees based on a skill-based score calculation.
     *
     * @param scores An integer array used internally to store temporary scores (potentially pre-allocated).
     *                 Its size should match the number of employees in `listOfEmployees`.
     * @param listOfEmployees The original, unsorted list of `Employee` objects.
     * @return A new `ArrayList` containing the employees from `listOfEmployees` sorted
     *         in descending order based on their calculated skill scores.
     *
     * @implNote This method calculates a score for each employee based on the number of skills they possess
     *          from a predefined set of skills (`skills`). It iterates through the employee list:
     *          - For each employee, it iterates through the `skills` list.
     *          - If the employee has a particular skill, its score is incremented.
     *          - The score is stored in the `scores` array at the corresponding index of the employee in the `listOfEmployees` list.
     *          Then, it uses a bubble sort algorithm to sort the `scores` array (and the `employees1` list in parallel)
     *          in descending order based on the scores.
     */
    private ArrayList<Employee> sortEmployeesBySkillScore(Integer[] scores, List<Employee> listOfEmployees) {
        int scoreAux;

        ArrayList<Employee> employees1 = new ArrayList<>(listOfEmployees);

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
                    scores[j] = scores[j + 1];
                    scores[j + 1] = scoreCopy;


                    employeeCopy = employees1.get(j);
                    employees1.set(j, employees1.get(j + 1));
                    employees1.set(j + 1, employeeCopy);

                }

            }
        }
        return employees1;
    }


    public String toString() {
        return "Team Maximum Size: " + teamMaxSize + "\nTeam Minimum Size: " + teamMinSize + "\nSkills: " + skills;
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
