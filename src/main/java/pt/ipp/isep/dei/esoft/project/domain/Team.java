package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.*;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private static ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<Collaborator> collaborators = new ArrayList<>();
    private final int id;

    private final TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();

    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills) {
        this.teamMinSize = teamMinSize;
        this.teamMaxSize = teamMaxSize;
        this.skills = skills;
        this.id = teamRepository.getAllTeams().size() + 1;
    }

    public Team(ArrayList<Collaborator> collaborators) {
        this.collaborators = collaborators;
        this.id = teamRepository.getAllTeams().size() + 1;
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

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public int getId() {
        return id;
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

    public void setCollaborators(ArrayList<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    public static ArrayList<Collaborator> createSuperTeam(int teamMaxSize, int teamMinSize, ArrayList<Skill> skills, ArrayList<Collaborator> listOfCollaborators) {
        // Ordenar colaboradores por habilidades necessárias (presumindo que a função sortCollaboratorBySkill está implementada)
        ArrayList<Collaborator> collaboratorSorted = sortCollaboratorBySkill(listOfCollaborators, skills);

        // Map para contar a necessidade de cada skill
        Map<Skill, Integer> remainingSkills = new HashMap<>();
        for (Skill skill : skills) {
            remainingSkills.put(skill, remainingSkills.getOrDefault(skill, 0) + 1);
        }

        // Lista para armazenar os colaboradores selecionados
        ArrayList<Collaborator> selectedTeam = new ArrayList<>();
        // Set para rastrear as habilidades que a equipe cobre
        Set<Skill> teamSkills = new HashSet<>();

        for (Collaborator collaborator : collaboratorSorted) {
            if (selectedTeam.size() >= teamMaxSize) {
                break;
            }

            boolean addedToTeam = false;
            for (Skill skill : collaborator.getSkills()) {
                if (remainingSkills.containsKey(skill)) {
                    // Adicionar colaborador se ele ainda não está na equipe
                    if (!selectedTeam.contains(collaborator)) {
                        selectedTeam.add(collaborator);
                        addedToTeam = true;
                    }
                    // Adicionar skill ao conjunto de skills da equipe
                    teamSkills.add(skill);
                    // Reduzir a contagem da habilidade necessária
                    remainingSkills.put(skill, remainingSkills.get(skill) - 1);
                    if (remainingSkills.get(skill) <= 0) {
                        remainingSkills.remove(skill);
                    }
                }
            }

            // Verifica se a equipe já possui todas as skills necessárias e tem tamanho mínimo
            if (addedToTeam && teamSkills.containsAll(skills) && selectedTeam.size() >= teamMinSize) {
                return selectedTeam;
            }
        }

        // Verifica se a equipe formada atende aos requisitos após o loop
        if (teamSkills.containsAll(skills) && selectedTeam.size() >= teamMinSize) {
            return selectedTeam;
        }

        return new ArrayList<>();
    }

    public static ArrayList<Collaborator> createSuperTeam2(int teamMaxSize,int teamMinSize, ArrayList<Skill> skills, ArrayList<Collaborator> listOfCollaborators, Team team) {
        ArrayList<Collaborator> tempTeam = listOfCollaborators;

        for (int i = 0; i < listOfCollaborators.size(); i++) {
            Collaborator collaborator = listOfCollaborators.get(i);
            if (team.getCollaborators().contains(collaborator)) {
                tempTeam.remove(i);
                i--;
            }
        }

        return createSuperTeam(teamMaxSize, teamMinSize, skills, tempTeam);
    }

    private static ArrayList<Collaborator> sortCollaboratorBySkill(List<Collaborator> listOfCollaborators, ArrayList<Skill> skills) {
        Map<Collaborator, Integer> map = new HashMap<>();

        for (Collaborator collaborator : listOfCollaborators) {
            int score = 0;
            for (Skill skill : skills) {
                if (collaborator.getSkills().contains(skill)) {
                    score++;
                }
            }
            map.put(collaborator, score);
        }

        ArrayList<Collaborator> sortedCollaborators = new ArrayList<>(listOfCollaborators);

        for (int i = 0; i < sortedCollaborators.size() - 1; i++) {
            for (int j = 0; j < sortedCollaborators.size() - i - 1; j++) {
                if (map.get(sortedCollaborators.get(j)) < map.get(sortedCollaborators.get(j + 1))) {
                    Collaborator temp = sortedCollaborators.get(j);
                    sortedCollaborators.set(j, sortedCollaborators.get(j + 1));
                    sortedCollaborators.set(j + 1, temp);
                }
            }
        }

        return sortedCollaborators;
    }

    public String toString() {
        return "Team Maximum Size: " + teamMaxSize + "\nTeam Minimum Size: " + teamMinSize + "\nSkills: " + skills;
    }

    public Team clone() {
        return new Team(this.teamMinSize, this.teamMaxSize, this.skills);
    }

}
