package pt.ipp.isep.dei.esoft.project.ui.console.team;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateTeamUI implements Runnable {
    private final GenerateTeamController controller;

    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> skills;
    private List<Employee> employees;

    public GenerateTeamUI() {
        controller = new GenerateTeamController();
    }

    private GenerateTeamController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Team ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {

        Team team = controller.generateTeam(teamMinSize, teamMaxSize, skills);

        if (team != null && team.getEmployees().size() >= teamMinSize &&team.getEmployees().size() <= teamMaxSize && !team.getEmployees().isEmpty()) {
            listEmployees(team);
            String input = Utils.readLineFromConsole("\n Do you accept this team? (y/n)");
             if(input.equalsIgnoreCase("n") || input.isEmpty()){
                 System.out.println("A new team will be generated!\n ");
                 Team team1 = controller.generateSecondTeam(teamMinSize, teamMaxSize, skills);
                 listEmployees(team1);
                input = Utils.readLineFromConsole("\n Do you accept this team? (y/n)");

                 if(input.equalsIgnoreCase("y") || input.isEmpty()){
                     System.out.println("Team successfully created!\n ");
                 } else {
                     System.out.println("Team not created!\n ");
                 }

             } else if(input.equalsIgnoreCase("y") || input.isEmpty()) {
                 System.out.println("\nTeam successfully generated!");
             }else {
                 System.out.println("Team not created!\n ");
            }
        } else {
            System.out.println("\nNo team was created!");
        }
    }


    private void requestData() {

        //Request the Team Size from the console
        teamMaxSize = requestTeamSize("Max");
        do {
            teamMinSize = requestTeamSize("Min");
        } while (teamMinSize > teamMaxSize);
        skills = displayAndSelectSkill();

    }

    private int requestTeamSize(String type) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Team %s Size: ", type);
        return input.nextInt();
    }



    private ArrayList<Skill> displayAndSelectSkill() {
        //Display the list of skills
        ArrayList<Skill> skills = controller.getAllSkills();
        ArrayList<Skill> selectedSkills = new ArrayList<>();

        int listSize = skills.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        do {
            displaySkillsOptions(skills);
            System.out.println("  0 - Exit");
            System.out.print("Select a Skill: ");
            answer = input.nextInt();
            if (answer > 0 && answer <= listSize) {
                selectedSkills.add(skills.get(answer - 1));
            }
        } while (answer != 0);
        return selectedSkills;
    }

    private void displaySkillsOptions(List<Skill> skills) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }

    private void listEmployees(Team team) {

        List<Employee> employees = team.getEmployees();
        System.out.println("Team:");
        int counter = 1;
        for (Employee employee : employees) {
                System.out.println("[" + counter + "] ");
                System.out.println("    Name: " + employee.getName());
                System.out.println("    Email: " + employee.getEmail());
                System.out.println("    Skills: " + employee.getSkills());
                System.out.println("-----------------------------");
                counter++;
        }
    }
}