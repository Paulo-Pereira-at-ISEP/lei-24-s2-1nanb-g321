package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository {

    private final List<Skill> skills;
    public SkillRepository() { skills = new ArrayList<>();}

    private boolean validateSkill(Skill skill) {
        boolean isValid = !Skill.contains(skill);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Skill> getSkills() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }

    public Optional<Skill> add(Skill skill) {

        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skills.add(newSkill.get());
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;

    }
}