package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

public class CreateSkillController {

    private SkillRepository skillRepository;

    public List<Skill> getSkillList() {
        return skillRepository.getSkills();
    }

    public Skill createSkill(String name, String description) {

        Skill skill = new Skill(name, description);

        return skill;
    }
}