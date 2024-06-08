package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository implements Serializable {

    private final List<Team> teams;

    public TeamRepository() {

        List<Team> deserializedTeams = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"teams.ser");
        if (deserializedTeams == null) {
            this.teams = new ArrayList<>();
        } else {
            this.teams = deserializedTeams;
        }
    }
    public void serialize(){
        Serialize.serialize(teams,Serialize.FOLDER_PATH + File.separator +"teams.ser");}

    public void addTeam(Team team) {
        if (team == null) {
            throw new NullPointerException("Team cannot be null");
        }
        if (validateTeam(team)) {
            teams.add(team);
            serialize();
        }
    }

    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    private boolean validateTeam(Team team) {
        return !teams.contains(team);
    }


    public List<Team> getTeams() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(teams);
    }

    public Optional<Team> add(ArrayList<Collaborator> collaborators) {

        if (collaborators == null) {
            throw new NullPointerException("Collaborators cannot be null");
        }
        Team newTeam = new Team(collaborators);
        if (validateTeam(newTeam)) {
            Team clonedTeam = newTeam.clone();
            if (teams.add(newTeam)) {
                serialize();
                return Optional.of(newTeam);
            }
        }
        return Optional.empty();
    }
}
