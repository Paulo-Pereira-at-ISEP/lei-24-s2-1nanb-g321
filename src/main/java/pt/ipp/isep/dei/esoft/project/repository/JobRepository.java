package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {

    private final List<Job> jobs;

    public JobRepository() {

        List<Job> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"jobs.ser");
        if(result == null){
            this.jobs = new ArrayList<>();
        }else{
            this.jobs = result;
        }
    }
    public void serialize(){
        Serialize.serialize(jobs,Serialize.FOLDER_PATH + File.separator +"jobs.ser");}

    public void addJob(Job job) {
        if (job == null) {
            throw new NullPointerException("Job cannot be null");
        }
        if (validateJob(job)) {
            jobs.add(job);
            serialize();
        }
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

        if (job == null) {
            throw new NullPointerException("Job cannot be null");
        }

        if (validateJob(job)) {
            Job clonedJob = job.clone();
            if (jobs.add(clonedJob)) {
                serialize();
                return Optional.of(clonedJob);
            }
        }

        return Optional.empty();
    }
}