package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;

import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CreateTeamToEntryController {

    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private TaskRepository taskRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private AgendaRepository agendaRepository;
    private TeamRepository teamRepository;

    //Repository instances are obtained from the Repositories class
    public CreateTeamToEntryController() {
        getAuthenticationRepository();
        getCollaboratorRepository();
        getTaskRepository();
        getGreenSpacesRepository();
        getAgendaRepository();
        getTeamRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateTeamToEntryController(CollaboratorRepository collaboratorRepository,
                                       ToDoListRepository toDoListRepository, TaskRepository taskRepository,
                                       AuthenticationRepository authenticationRepository, TeamRepository teamRepository, AgendaRepository agendaRepository, GreenSpaceRepository greenSpaceRepository) {
        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.taskRepository = taskRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.agendaRepository = agendaRepository;
        this.teamRepository = teamRepository;
    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    private GreenSpaceRepository getGreenSpacesRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            Repositories repositories = Repositories.getInstance();
            taskRepository = repositories.getTaskRepository();
        }
        return taskRepository;
    }

    public CreateTeamToEntryController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Entry> getAllEntries() {
        return agendaRepository.getEntries();
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getAllCollaborators();
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public List<GreenSpace> getAllGreenSpaces() {
        return greenSpaceRepository.getAllGreenSpaces();
    }

    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    public List<Entry> getEntriesByDate(LocalDate date) {
        return agendaRepository.getEntriesByDate(date);
    }

    public void sendMessageToCollaborators(Team team, String entryName) {
        teamRepository.assignTeamToAgendaEntry(team, entryName);
    }

    public Entry createEntry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate date, int hour, Team team) {
        // Create entry with start time and calculate end time
        Entry newEntry = new Entry(name, description, urgencyDegree, duration, greenSpace, date, hour, team);
        newEntry.setStartTime(LocalTime.of(hour, 0)); // Assuming start time is at the beginning of the hour
        newEntry.setEndTime(newEntry.getStartTime().plusHours(duration));


        // Add entry to repository
        agendaRepository.addEntry(newEntry);


        return newEntry;
    }
}




