package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class CreateJobController {

    private JobRepository jobRepository;

    public List<Job> getJobList() {
        return jobRepository.getJobs();
    }

    public Job createJob(String name, String description) {

        Job job = new Job(name, description);

        return job;
    }
}