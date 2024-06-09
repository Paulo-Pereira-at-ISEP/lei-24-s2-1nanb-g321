package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.EmailConfig;
import pt.ipp.isep.dei.esoft.project.domain.EmailSender;
import pt.ipp.isep.dei.esoft.project.domain.MessageService;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository implements Serializable {

    private final List<Team> teams;
    private final MessageService messageService;

    public TeamRepository() {
        EmailConfig emailConfig = loadEmailConfig();
        EmailSender emailSender = new EmailSender(emailConfig);
        this.messageService = new MessageService(emailSender);
        List<Team> deserializedTeams = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator + "teams.ser");
        if (deserializedTeams == null) {
            this.teams = new ArrayList<>();
        } else {
            this.teams = deserializedTeams;
        }
    }

    public void serialize() {
        Serialize.serialize(teams, Serialize.FOLDER_PATH + File.separator + "teams.ser");
    }

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

    public void assignTeamToAgendaEntry(Team team, String agendaEntry) {
        if (team == null || agendaEntry == null) {
            throw new NullPointerException("Team and agenda entry cannot be null");
        }
        // Logic to assign the team to the agenda entry (not implemented here)
        // ...

        // Notify all team members

        messageService.sendMessageToAllTeamMembers(team, "You have been assigned to a new agenda entry: " + agendaEntry + " on this date: ");
    }

    private EmailConfig loadEmailConfig() {
        // Implement logic to load EmailConfig from a file or environment variables
        EmailConfig config = new EmailConfig();
        config.setServiceProvider("manager@musgosublime.com");
        config.setUsername("Musgo Sublme");
        config.setPassword("admin");
        return config;
    }
}
