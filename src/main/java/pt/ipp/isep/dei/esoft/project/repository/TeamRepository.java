package pt.ipp.isep.dei.esoft.project.repository;

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

    private boolean validateTeam(Team team) {
        boolean isValid = !teams.contains(team);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of teams.
     *
     * @return The list of teams.
     */
    public List<Team> getTeams() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(teams);
    }

    public Optional<Team> add(Team team) {

        Optional<Team> newTeam = Optional.empty();
        boolean operationSuccess = false;

        if (validateTeam(team)) {
            newTeam = Optional.of(team.clone());
            operationSuccess = teams.add(newTeam.get());
        }

        if (!operationSuccess) {
            newTeam = Optional.empty();
        }

        return newTeam;

    }

}