package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    public void testGetInstance() {
        Repositories instance1 = Repositories.getInstance();
        Repositories instance2 = Repositories.getInstance();

        // Check if both instances are the same
        assertSame(instance1, instance2);
    }

//    @Test
//    public void testGetOrganizationRepository() {
        // Repositories instance = Repositories.getInstance();
      //  OrganizationRepository organizationRepository = instance.getOrganizationRepository();

        // Check if the organization repository is not null
    //    assertNotNull(organizationRepository);
  //  }

    @Test
    public void testGetAuthenticationRepository() {
        Repositories instance = Repositories.getInstance();
        AuthenticationRepository authenticationRepository = instance.getAuthenticationRepository();

        // Check if the authentication repository is not null
        assertNotNull(authenticationRepository);
    }

    @Test
    public void testGetSkillRepository() {
        Repositories instance = Repositories.getInstance();
        SkillRepository skillRepository = instance.getSkillRepository();

        // Check if the skill repository is not null
        assertNotNull(skillRepository);
    }

    @Test
    public void testGetJobRepository() {
        Repositories instance = Repositories.getInstance();
        JobRepository jobRepository = instance.getJobRepository();

        // Check if the job repository is not null
        assertNotNull(jobRepository);
    }

    @Test
    public void testGetTeamRepository() {
        Repositories instance = Repositories.getInstance();
        TeamRepository teamRepository = instance.getTeamRepository();

        // Check if the team repository is not null
        assertNotNull(teamRepository);
    }
}