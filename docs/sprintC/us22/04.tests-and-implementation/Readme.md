# US08 - As a VFM, I want the system to produce a list (report) of vehicles needing maintenance.

## 4. Tests

**Test 1:** Check if a valid entry can be successfully added to the to-do list repository.

	@Test
    public void testAddEntry_ValidEntry_SuccessfullyAdded() {
        // Arrange
        Entry entry = createTestEntry();

        // Act
        toDoListRepository.addEntry(entry);

        // Assert
        assertTrue(toDoListRepository.getAllEntrys().contains(entry));
    }

**Test 2:** Checks if when a null entry is attempted to be added to the to-do list repository, a NullPointerException is thrown, as expected.
	
    @Test
    public void testAddEntry_NullEntry_ExceptionThrown() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> toDoListRepository.addEntry(null));
    }

**Test 3:** Check that the sortEntriesByUrgencyDegree() method of the ToDoListRepository sorts a list of entries based on their urgency degree correctly.

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



## 5. Construction (Implementation)

### Class Entry



### Class Entry

```java
 public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate entryDate, int hour, Team team) {
    super(name, description);
    this.urgencyDegree = urgencyDegree;
    this.duration = duration;
    this.greenSpace = greenSpace;
    this.entryDate = entryDate;
    this.status = "Planned";
    this.hour = hour;
    this.team = team;
    this.startTime = LocalTime.of(hour, 0); // Assuming start time is at the beginning of the hour
    this.endTime = calculateEndTime();
}
```

### Class CreateTeamToEntryController

```java
public Entry createEntry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate date, int hour, Team team) {
    // Create entry with start time and calculate end time
    Entry newEntry = new Entry(name, description, urgencyDegree, duration, greenSpace, date, hour, team);
    newEntry.setStartTime(LocalTime.of(hour, 0)); // Assuming start time is at the beginning of the hour
    newEntry.setEndTime(newEntry.getStartTime().plusHours(duration));


    // Add entry to repository
    agendaRepository.addEntry(newEntry);


    return newEntry;
}
```

### Class CreateTeamToEntryRepository

```java
public Optional<Entry> add(Entry entry) {

    if (entry == null) {
        throw new NullPointerException("Entry cannot be null");
    }

    if (validateEntry(entry)) {
        Entry clonedEntry = entry.clone();
        if (entrys.add(clonedEntry)) {
            serialize();
            return Optional.of(clonedEntry);
        }
    }

    return Optional.empty();
}
```


## 6. Integration and Demo

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a