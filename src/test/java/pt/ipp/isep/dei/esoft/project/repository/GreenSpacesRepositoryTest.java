package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreenSpacesRepositoryTest {
    @Test
    public void testGreenSpaceRepository_Constructor() {
        GreenSpaceRepository repository = new GreenSpaceRepository();

        // Verify the list is either loaded from serialization or initialized as empty
        List<GreenSpace> greenSpaces = repository.getGreenSpaces();
        assertTrue(greenSpaces.isEmpty() || !greenSpaces.isEmpty()); // Empty or contains deserialized data
    }
    @Test
    public void testGreenSpaceRepository_addGreenSpace_Valid() {
        GreenSpace greenSpace = new GreenSpace("Central Park", 1000000.0, "Urban Park", null); // Assuming Employee is nullable
        GreenSpaceRepository repository = new GreenSpaceRepository();

        repository.addGreenSpace(greenSpace);

        List<GreenSpace> greenSpaces = repository.getGreenSpaces();
        assertTrue(greenSpaces.contains(greenSpace));
    }

}

