package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationRepository authenticationRepository;
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        authenticationRepository = new AuthenticationRepository();
        skillRepository = new SkillRepository();
        jobRepository = new JobRepository();
        employeeRepository = new EmployeeRepository();
        teamRepository = new TeamRepository();
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

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
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

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }
}