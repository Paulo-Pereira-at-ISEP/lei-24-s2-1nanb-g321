package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

public class CreateEntryController {




        private ToDoListRepository toDoListRepository;
        private AuthenticationRepository authenticationRepository;
        private CollaboratorRepository collaboratorRepository;
        private TaskRepository taskRepository;

        //Repository instances are obtained from the Repositories class
        public CreateEntryController() {
            getToDoListRepository();
            getAuthenticationRepository();
            getCollaboratorRepository();
            getTaskRepository();
        }

        //Allows receiving the repositories as parameters for testing purposes
        public CreateEntryController(CollaboratorRepository collaboratorRepository,
                                     ToDoListRepository toDoListRepository,TaskRepository taskRepository ,
                                     AuthenticationRepository authenticationRepository) {
            this.toDoListRepository = toDoListRepository;
            this.authenticationRepository = authenticationRepository;
            this.collaboratorRepository = collaboratorRepository;
            this.taskRepository = taskRepository;
        }

        private ToDoListRepository getToDoListRepository() {
            if (toDoListRepository == null) {
                Repositories repositories = Repositories.getInstance();
                toDoListRepository = repositories.getToDoListRepository();
            }
            return toDoListRepository;
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
            return toDoListRepository.getEntrys();
        }

        public List<Collaborator> getAllCollaborators() {
            return collaboratorRepository.getAllCollaborators();
        }
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Entry createEntry(String name, String description, String urgencyDegree, int duration) {

            Entry newEntry = new Entry(name, description, urgencyDegree, duration);

            toDoListRepository.add(newEntry);

            return newEntry;
        }
    }

