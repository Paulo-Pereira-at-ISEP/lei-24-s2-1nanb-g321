package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static Repositories instance;
    private final AuthenticationRepository authenticationRepository;
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final TeamRepository teamRepository;
    private final TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final ManagerRepository managerRepository;
    private final CollaboratorRepository collaboratorRepository;

    private Repositories() {
        greenSpaceRepository = new GreenSpaceRepository();
        authenticationRepository = new AuthenticationRepository();
        skillRepository = new SkillRepository();
        jobRepository = new JobRepository();
        teamRepository = new TeamRepository();
        taskRepository = new TaskRepository();
        toDoListRepository = new ToDoListRepository();
        managerRepository = new ManagerRepository();
        collaboratorRepository = new CollaboratorRepository();
    }

    /**
     * Provides a thread-safe singleton instance of the `Repositories` class.
     *
     * @return The singleton instance of `Repositories`.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }


    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public ToDoListRepository getToDoListRepository() {
        return toDoListRepository;
    }

    public ManagerRepository getManagerRepository() {
        return managerRepository;
    }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;

    }
}