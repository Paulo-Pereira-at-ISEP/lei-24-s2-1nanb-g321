package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateEntryController {




        private ToDoListRepository toDoListRepository;
        private AuthenticationRepository authenticationRepository;
        private CollaboratorRepository collaboratorRepository;
        private TaskRepository taskRepository;
        private GreenSpaceRepository greenSpaceRepository;

        //Repository instances are obtained from the Repositories class
        public CreateEntryController() {
            getToDoListRepository();
            getAuthenticationRepository();
            getCollaboratorRepository();
            getTaskRepository();
            getGreenSpacesRepository();
        }

        //Allows receiving the repositories as parameters for testing purposes
        public CreateEntryController(CollaboratorRepository collaboratorRepository,
                                     ToDoListRepository toDoListRepository,TaskRepository taskRepository ,
                                     AuthenticationRepository authenticationRepository, GreenSpaceRepository greenSpaceRepository) {
            this.toDoListRepository = toDoListRepository;
            this.authenticationRepository = authenticationRepository;
            this.collaboratorRepository = collaboratorRepository;
            this.taskRepository = taskRepository;
            this.greenSpaceRepository = greenSpaceRepository;
        }

        private ToDoListRepository getToDoListRepository() {
            if (toDoListRepository == null) {
                Repositories repositories = Repositories.getInstance();
                toDoListRepository = repositories.getToDoListRepository();
            }
            return toDoListRepository;
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

        public CreateEntryController(ToDoListRepository toDoListRepository) {
            this.toDoListRepository = toDoListRepository;
        }

        public List<Entry> getAllEntrys() {
            return toDoListRepository.getEntries();
        }
    public List<Entry> getEntriesByUrgencyDegree(List<Entry> entries) {
        return toDoListRepository.sortEntriesByUrgencyDegree(entries);
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

    public Entry createEntry(Task task, String urgencyDegree, int duration, GreenSpace greenSpace) {

            Entry newEntry = new Entry(task.getName(), task.getDescription(), urgencyDegree, duration, greenSpace);

            toDoListRepository.add(newEntry);

            return newEntry;
        }
    }

