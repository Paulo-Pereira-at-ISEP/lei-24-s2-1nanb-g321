package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JobRepositoryTest {

    @Test
    public void testAddJob() {
        JobRepository repository = new JobRepository();
        Job job = new Job("Software Engineer", "Develop software applications");
        repository.addJob(job);
        List<Job> jobs = repository.getAllJobs();
        assertTrue(jobs.contains(job));
    }

    @Test
    public void testGetJobs() {
        JobRepository repository = new JobRepository();
        Job job = new Job("Software Engineer", "Develop software applications");
        repository.addJob(job);
        List<Job> jobs = repository.getJobs();
        assertEquals(1, jobs.size());
    }

    @Test
    public void testAddValidJob() {
        JobRepository repository = new JobRepository();
        Job job = new Job("Software Engineer", "Develop software applications");
        Optional<Job> addedJob = repository.add(job);
        assertTrue(addedJob.isPresent());
    }

    @Test
    public void testAddDuplicateJob() {
        JobRepository repository = new JobRepository();
        Job job = new Job("Software Engineer", "Develop software applications");
        repository.addJob(job); // Add the job once
        Optional<Job> addedJob = repository.add(job); // Attempt to add the same job again
        assertTrue(addedJob.isEmpty()); // Adding duplicate should fail
    }
}