package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

public class CreateJobController {

    private JobsRepository jobsRepository;

    public List<Job> jobs() {
        return jobsRepository.getjobs();
    }

    public Job createJob(String name, String description) {

        Job job = new Job(name, description);

        return job;
    }
}