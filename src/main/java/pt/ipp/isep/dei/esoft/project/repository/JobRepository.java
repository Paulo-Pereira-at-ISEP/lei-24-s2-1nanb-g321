package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {

    private final List<Job> jobs;

    public JobRepository() {
        this.jobs = new ArrayList<>();
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs);
    }

    private boolean validateJob(Job job) {
        boolean isValid = !jobs.contains(job);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Job> getJobs() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobs);
    }
    /**
     * Attempts to add a new `Job` object to the internal collection of jobs.
     *
     * @param job The `Job` object to be added.
     * @return An `Optional` of `Job`. If the operation is successful, the `Optional`
     *         contains the added `Job` object. Otherwise, it is empty.
     *
     * @throws NullPointerException if the `job` parameter is null.
     *
     * @implNote This method first validates the provided `job` object using the `validateJob` method
     *          (assumed to be available). If validation is successful, it creates a copy of the `job`
     *          using the `clone` method and wraps it in an `Optional.of` object. Then, it attempts to add
     *          the `Optional` object (containing the cloned job) to the internal collection (`jobs`).
     *          If the addition is successful (returns `true`), the `Optional` with the added job is returned.
     *          Otherwise, an empty `Optional` is returned.
     */
    public Optional<Job> add(Job job) {

        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;

        if (validateJob(job)) {
            newJob = Optional.of(job.clone());
            operationSuccess = jobs.add(newJob.get());
        }

        if (!operationSuccess) {
            newJob = Optional.empty();
        }

        return newJob;

    }
}