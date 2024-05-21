package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository{

    private final List<Team> teams;

    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    private boolean validateTeam(ArrayList<Collaborator> collaborators) {
        boolean isValid = !teams.contains(collaborators);
        return isValid;
    }


    public List<Team> getTeams() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(teams);
    }

    public Optional<Team> add(ArrayList<Collaborator> collaborators) {

        Optional<Team> newTeam = Optional.empty();
        boolean operationSuccess = false;

        if (validateTeam(collaborators)) {
            newTeam = Optional.of((Team) collaborators.clone());
            operationSuccess = teams.add(newTeam.get());
        }

        if (!operationSuccess) {
            newTeam = Optional.empty();
        }

        return newTeam;
    }
}
