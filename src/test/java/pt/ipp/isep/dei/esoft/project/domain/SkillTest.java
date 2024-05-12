package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    public void testConstructorWithNameAndDescription() {
        Skill skill = new Skill("Programming", "Coding skill");
        assertEquals("Programming", skill.getName());
        assertEquals("Coding skill", skill.getDescription());
    }

    @Test
    public void testConstructorWithName() {
        Skill skill = new Skill("Programming");
        assertEquals("Programming", skill.getName());
        assertEquals("Default skill", skill.getDescription());
    }

    @Test
    public void testDefaultConstructor() {
        Skill skill = new Skill();
        assertEquals("Default skill", skill.getName());
        assertEquals("Default skill", skill.getDescription());
    }

    @Test
    public void testContains() {
        Skill skill = new Skill("Programming", "Coding skill");
        assertTrue(Skill.contains(skill));
    }

    @Test
    public void testContainsNullSkill() {
        assertFalse(Skill.contains(null));
    }

    @Test
    public void testContainsNullName() {
        Skill skill = new Skill(null, "Coding skill");
        assertFalse(Skill.contains(skill));
    }

    @Test
    public void testContainsNullDescription() {
        Skill skill = new Skill("Programming", null);
        assertFalse(Skill.contains(skill));
    }

    @Test
    public void testToString() {
        Skill skill = new Skill("Programming", "Coding skill");
        assertEquals("Programming - Coding skill", skill.toString());
    }

    @Test
    public void testClone() {
        Skill originalSkill = new Skill("Programming", "Coding skill");
        Skill clonedSkill = originalSkill.clone();
        assertEquals(originalSkill.getName(), clonedSkill.getName());
        assertEquals(originalSkill.getDescription(), clonedSkill.getDescription());
    }

    @Test
    public void testEquals() {
        Skill skill1 = new Skill("Programming", "Coding skill");
        Skill skill2 = new Skill("Programming", "Another coding skill");
        assertEquals(skill1, skill2);
    }

    @Test
    public void testNotEquals() {
        Skill skill1 = new Skill("Programming", "Coding skill");
        Skill skill2 = new Skill("Testing", "Testing skill");
        assertNotEquals(skill1, skill2);
    }

    @Test
    public void testCompareTo() {
        Skill skill1 = new Skill("Programming");
        Skill skill2 = new Skill("Testing");
        assertTrue(skill1.compareTo(skill2) < 0);
    }
}