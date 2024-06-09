package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgendaRepositoryTest {


        private AgendaRepository agendaRepository;

        @BeforeEach
        public void setUp() {
            agendaRepository = new AgendaRepository();
        }

        @Test
        public void testAddEntry_ValidEntry_SuccessfullyAdded() {
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
            Collaborator collaborator = new Collaborator(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, skills);
            ArrayList<Collaborator> collaborators = new ArrayList<>();
            collaborators.add(collaborator);
            // Given
            Entry entry = new Entry("Meeting", "Project meeting", "High", 2, new GreenSpace("Park", 2000, "garden", new Manager("gsm1@this.app")), LocalDate.now(), 10, new Team(collaborators));

            // When
            agendaRepository.addEntry(entry);

            // Then
            assertTrue(agendaRepository.getAllEntries().contains(entry));
        }

        @Test
        public void testGetEntriesByDate_EntriesExistInGivenDate_ReturnFilteredEntries() {
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
            Collaborator collaborator = new Collaborator(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, skills);
            ArrayList<Collaborator> collaborators = new ArrayList<>();
            collaborators.add(collaborator);
            LocalDate date = LocalDate.now();
            Entry entry1 = new Entry("Meeting", "Project meeting", "High", 2, new GreenSpace("Park", 2000,"garden", new Manager("gsm1@this.app")), date, 10, new Team(collaborators));
            Entry entry2 = new Entry("Training", "Project training", "Medium", 1, new GreenSpace("Office", 2000, "garden", new Manager("gsm1@this.app")), date, 14, new Team(collaborators));
            agendaRepository.addEntry(entry1);
            agendaRepository.addEntry(entry2);

            // When
            List<Entry> filteredEntries = agendaRepository.getEntriesByDate(date);

            // Then
            assertEquals(2, filteredEntries.size());
        }

        @Test
        public void testGetEntriesBetweenTwoDates_EntriesExistInGivenDateRange_ReturnFilteredEntries() {
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
            Collaborator collaborator = new Collaborator(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, skills);
            ArrayList<Collaborator> collaborators = new ArrayList<>();
            collaborators.add(collaborator);
            LocalDate date = LocalDate.now();


            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(7);
            Entry entry1 = new Entry("Meeting", "Project meeting", "High", 2, new GreenSpace("Park", 2000,"garden", new Manager("gsm1@this.app")), date, 10, new Team(collaborators));
            Entry entry2 = new Entry("Training", "Project training", "Medium", 1, new GreenSpace("Office", 2000, "garden", new Manager("gsm1@this.app")), date, 14, new Team(collaborators));
            agendaRepository.addEntry(entry1);
            agendaRepository.addEntry(entry2);


            List<Entry> filteredEntries = agendaRepository.getEntriesBetweenTwoDates(startDate, endDate);


            assertEquals(2, filteredEntries.size());
        }

    }

