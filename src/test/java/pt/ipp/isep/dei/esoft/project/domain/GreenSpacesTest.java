package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreenSpacesTest {

    @Test
    public void testGreenSpace_ToString() {
        String name = "Central Park";
        GreenSpace greenSpace = new GreenSpace(name, 0.0, "", null);

        assertEquals(greenSpace.toString(), name);
    }
}
