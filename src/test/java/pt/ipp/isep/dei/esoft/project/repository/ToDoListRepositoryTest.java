package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListRepositoryTest {

    private ToDoListRepository toDoListRepository;

    @BeforeEach
    public void setUp() {
        toDoListRepository = new ToDoListRepository();
    }

    @Test
    public void testAddEntry_ValidEntry_SuccessfullyAdded() {
        // Arrange
        Entry entry = createTestEntry();

        // Act
        toDoListRepository.addEntry(entry);

        // Assert
        assertTrue(toDoListRepository.getAllEntrys().contains(entry));
    }

    @Test
    public void testAddEntry_NullEntry_ExceptionThrown() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> toDoListRepository.addEntry(null));
    }

    @Test
    public void testSortEntriesByUrgencyDegree_EntryListSortedByUrgencyDegree() {
        // Arrange
        List<Entry> unsortedEntries = createUnsortedEntries();

        // Act
        List<Entry> sortedEntries = toDoListRepository.sortEntriesByUrgencyDegree(unsortedEntries);

        // Assert
        assertEquals(3, sortedEntries.size());
        assertEquals("High", sortedEntries.get(0).getUrgencyDegree());
        assertEquals("Medium", sortedEntries.get(1).getUrgencyDegree());
        assertEquals("Low", sortedEntries.get(2).getUrgencyDegree());
    }

    private Entry createTestEntry() {
        GreenSpace greenSpace = new GreenSpace("Park", 2000, "garden", new Manager("gsm1@this.app"));
        LocalDate date = LocalDate.now();
        return new Entry("Meeting", "Project meeting", "High", 2, greenSpace, date, 10);
    }

    private List<Entry> createUnsortedEntries() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry("Entry1", "Description1", "Medium", 1, null));
        entries.add(new Entry("Entry2", "Description2", "Low", 2, null));
        entries.add(new Entry("Entry3", "Description3", "High", 3, null));
        return entries;
    }
}

