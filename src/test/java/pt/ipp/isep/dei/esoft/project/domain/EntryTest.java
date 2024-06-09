package pt.ipp.isep.dei.esoft.project.domain;

import javafx.util.converter.LocalDateStringConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class EntryTest {
    @Test
    public void testCalculateEndTime() {

        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 10, 1);
        String address = "123 Main St";
        int mobile = 123456789;
        String email = "john.doe@example.com";
        String idDocType = "ID";
        int docTypeNumber = 12345;
        int taxPayerIdNumber = 67890;
        Job job = new Job("Software Engineer", "Develop software");
        String password = "password123";
        String role = "Developer";
        Skill skill = new Skill("detail", "oriented");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill);

        LocalTime startTime = LocalTime.of(10, 0); // Start time at 10:00
        int duration = 2; // Duration of 2 hours
        Collaborator collaborator = new Collaborator(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, skills);
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Entry entry = new Entry("Meeting", "Project meeting", "High", duration, new GreenSpace("Park",2000,"garden", new Manager("gsm1@this.app")), LocalDate.now(), 10, new Team(collaborators));

        entry.setStartTime(startTime);
        LocalTime endTime = entry.getEndTime();

        // Then
        assertEquals(LocalTime.of(12, 0), endTime); // Expected end time is 12:00 (2 hours after start time)
    }


    @Test
    public void testToString() {
        // Given
        Entry entry = new Entry("Meeting", "Project meeting", "High", 2, new GreenSpace("Park", 1000,"garden", new Manager("gsm1@this.app")), LocalDate.now(), 10);

        // When
        String result = entry.toString();

        // Then
        String expected = "Entry: \nGreenSpace= Park\nTitle=Meeting\nDescription=Project meeting\nUrgencyDegree=High\nDuration=2";
        assertEquals(expected, result);
    }

    // You can add more test cases to cover other methods and edge cases
}

