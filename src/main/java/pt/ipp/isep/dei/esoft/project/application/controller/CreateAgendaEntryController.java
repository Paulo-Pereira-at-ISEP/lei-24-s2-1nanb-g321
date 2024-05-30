package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class CreateAgendaEntryController {

        private AgendaRepository agendaRepository;
        private AuthenticationRepository authenticationRepository;
        private CollaboratorRepository collaboratorRepository;
        private TaskRepository taskRepository;
        private GreenSpaceRepository greenSpaceRepository;
        private ToDoListRepository toDoListRepository;
        private TeamRepository teamRepository;

        //Repository instances are obtained from the Repositories class
        public CreateAgendaEntryController() {
            getAgendaRepository();
            getAuthenticationRepository();
            getCollaboratorRepository();
            getTaskRepository();
            getGreenSpacesRepository();
            getToDoListRepository();
            getTeamRepository();
        }

        //Allows receiving the repositories as parameters for testing purposes
        public CreateAgendaEntryController(CollaboratorRepository collaboratorRepository,
                                     AgendaRepository agendaRepository,TaskRepository taskRepository ,
                                     AuthenticationRepository authenticationRepository,TeamRepository teamRepository ,ToDoListRepository  toDoListRepository ,GreenSpaceRepository greenSpaceRepository) {
            this.agendaRepository = agendaRepository;
            this.authenticationRepository = authenticationRepository;
            this.collaboratorRepository = collaboratorRepository;
            this.taskRepository = taskRepository;
            this.greenSpaceRepository = greenSpaceRepository;
            this.toDoListRepository = toDoListRepository;
            this.teamRepository = teamRepository;
        }

        private AgendaRepository getAgendaRepository() {
            if (agendaRepository == null) {
                Repositories repositories = Repositories.getInstance();
                agendaRepository = repositories.getAgendaRepository();
            }
            return agendaRepository;
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

        public CreateAgendaEntryController(AgendaRepository agendaRepository) {
            this.agendaRepository = agendaRepository;
        }

        public List<AgendaEntry> getAllAgendaEntrys() {
            return agendaRepository.getEntrys();
        }

        public List<Collaborator> getAllCollaborators() {
            return collaboratorRepository.getAllCollaborators();
        }
        public List<Task> getAllTasks() {
            return taskRepository.getAllTasks();
        }
    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }
        public List<GreenSpace> getAllGreenSpaces() {
            return greenSpaceRepository.getAllGreenSpaces();
        }
    public List<Entry> getAllEntrys() {
        return toDoListRepository.getEntrys();
    }
        public AgendaEntry createAgendaEntry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, String day, int hours, String status, Team team) {

            AgendaEntry newAgendaEntry = new AgendaEntry(name, description, urgencyDegree, duration, greenSpace, day, hours, status, team);

            agendaRepository.add(newAgendaEntry);

            return newAgendaEntry;
        }
    }


