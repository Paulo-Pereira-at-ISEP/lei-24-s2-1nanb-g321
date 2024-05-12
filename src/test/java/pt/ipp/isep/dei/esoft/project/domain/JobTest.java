package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    public void testConstructorWithNameAndDescription() {
        Job job = new Job("Software Engineer", "Develop software applications");
        assertEquals("Software Engineer", job.getName());
        assertEquals("Develop software applications", job.getDescription());
    }

    @Test
    public void testConstructorWithName() {
        Job job = new Job("Software Engineer");
        assertEquals("Software Engineer", job.getName());
        assertEquals("Default description", job.getDescription());
    }

    @Test
    public void testDefaultConstructor() {
        Job job = new Job();
        assertEquals("Default name", job.getName());
        assertEquals("Default description", job.getDescription());
    }

    @Test
    public void testContains() {
        Job job = new Job("Software Engineer", "Develop software applications");
        assertTrue(Job.contains(job));
    }

    @Test
    public void testContainsNullName() {
        Job job = new Job(null, "Develop software applications");
        assertFalse(Job.contains(job));
    }

    @Test
    public void testContainsNullDescription() {
        Job job = new Job("Software Engineer", null);
        assertFalse(Job.contains(job));
    }

    @Test
    public void testToString() {
        Job job = new Job("Software Engineer", "Develop software applications");
        assertEquals("Software Engineer - Develop software applications", job.toString());
    }

    @Test
    public void testClone() {
        Job originalJob = new Job("Software Engineer", "Develop software applications");
        Job clonedJob = originalJob.clone();
        assertEquals(originalJob.getName(), clonedJob.getName());
        assertEquals(originalJob.getDescription(), clonedJob.getDescription());
    }

    @Test
    public void testEquals() {
        Job job1 = new Job("Software Engineer", "Develop software applications");
        Job job2 = new Job("Software Engineer", "Another software engineering role");
        assertEquals(job1, job2);
    }

    @Test
    public void testNotEquals() {
        Job job1 = new Job("Software Engineer", "Develop software applications");
        Job job2 = new Job("Data Scientist", "Analyze data");
        assertNotEquals(job1, job2);
    }

}