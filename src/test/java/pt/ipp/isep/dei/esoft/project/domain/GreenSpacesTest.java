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
    @Test
    public void testGreenSpace_ConstructorAndGetters() {
        String name = "Central Park";
        double area = 1000000.0;
        String classification = "Urban Park";
        Employee manager = new Manager("gsm1@this.app"); // Assuming Employee class exists

        GreenSpace greenSpace = new GreenSpace(name, area, classification, manager);

        assertEquals(greenSpace.getName(), name);
        assertEquals(greenSpace.getArea(), area, 0.001); // Delta for double comparison
        assertEquals(greenSpace.getClassification(), classification);
        assertEquals(greenSpace.getManager(), manager);
    }
}
