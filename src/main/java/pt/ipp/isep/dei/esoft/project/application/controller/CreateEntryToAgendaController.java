package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.List;

public class CreateEntryToAgendaController {

        private ToDoListRepository toDoListRepository;
        private AuthenticationRepository authenticationRepository;
        private CollaboratorRepository collaboratorRepository;
        private TaskRepository taskRepository;
        private GreenSpaceRepository greenSpaceRepository;
        private AgendaRepository agendaRepository;
        private TeamRepository teamRepository;

        //Repository instances are obtained from the Repositories class
        public CreateEntryToAgendaController() {
            getToDoListRepository();
            getAuthenticationRepository();
            getCollaboratorRepository();
            getTaskRepository();
            getGreenSpacesRepository();
            getAgendaRepository();
            getTeamRepository();
        }

        //Allows receiving the repositories as parameters for testing purposes
        public CreateEntryToAgendaController(CollaboratorRepository collaboratorRepository,
                                     ToDoListRepository toDoListRepository,TaskRepository taskRepository ,
                                     AuthenticationRepository authenticationRepository, TeamRepository teamRepository,AgendaRepository agendaRepository ,GreenSpaceRepository greenSpaceRepository) {
            this.toDoListRepository = toDoListRepository;
            this.authenticationRepository = authenticationRepository;
            this.collaboratorRepository = collaboratorRepository;
            this.taskRepository = taskRepository;
            this.greenSpaceRepository = greenSpaceRepository;
            this.agendaRepository = agendaRepository;
            this.teamRepository = teamRepository;
        }

        private ToDoListRepository getToDoListRepository() {
            if (toDoListRepository == null) {
                Repositories repositories = Repositories.getInstance();
                toDoListRepository = repositories.getToDoListRepository();
            }
            return toDoListRepository;
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

        public CreateEntryToAgendaController(AgendaRepository agendaRepository) {
            this.agendaRepository = agendaRepository;
        }

        public List<Entry> getToDoListEntrys() {
            return toDoListRepository.getEntrys();
        }

        public List<Entry> getAllEntrys() {
            return agendaRepository.getEntrys();
        }
        public List<Entry> getAllToDoListEntrys (){
            return toDoListRepository.getEntrys();
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

        public Entry createEntry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate date, String status, int hour) {

            Entry newEntry = new Entry(name, description, urgencyDegree, duration, greenSpace, date, status, hour);

            agendaRepository.add(newEntry);

            return newEntry;
        }
    }


