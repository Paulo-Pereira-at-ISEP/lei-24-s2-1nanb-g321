package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobsRepository {

    private final List<Job> jobs;

    public JobsRepository() {
        jobs = new ArrayList<>();
    }

    private boolean validateSkill(Skill skill) {
        boolean isValid = !Skill.contains(skill);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */

    public List<Job> getjobs() {
        return List.copyOf(jobs);
    }
}