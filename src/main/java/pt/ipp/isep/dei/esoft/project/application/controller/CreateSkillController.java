package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class CreateSkillController {

    private SkillRepository skillRepository;

    public CreateSkillController (SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getSkillList() {
        return skillRepository.getSkills();
    }

    public Skill createSkill(String name, String description) {

        Skill newSkill = new Skill(name, description);

        return newSkill;
    }
}