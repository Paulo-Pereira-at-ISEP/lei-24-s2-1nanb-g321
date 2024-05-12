package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SkillRepositoryTest {

    @Test
    public void testAddSkill() {
        SkillRepository repository = new SkillRepository();
        Skill skill = new Skill("Java");
        repository.addSkill(skill);
        List<Skill> skills = repository.getAllSkills();
        assertTrue(skills.contains(skill));
    }

    @Test
    public void testGetSkills() {
        SkillRepository repository = new SkillRepository();
        Skill skill = new Skill("Java");
        repository.addSkill(skill);
        List<Skill> skills = repository.getSkills();
        assertEquals(1, skills.size());
    }

    @Test
    public void testAddValidSkill() {
        SkillRepository repository = new SkillRepository();
        Skill skill = new Skill("Java");
        Optional<Skill> addedSkill = repository.add(skill);
        assertTrue(addedSkill.isPresent());
    }

    @Test
    public void testAddDuplicateSkill() {
        SkillRepository repository = new SkillRepository();
        Skill skill = new Skill("Java");
        repository.addSkill(skill); // Add the skill once
        Optional<Skill> addedSkill = repository.add(skill); // Attempt to add the same skill again
        assertTrue(addedSkill.isEmpty()); // Adding duplicate should fail
    }
}