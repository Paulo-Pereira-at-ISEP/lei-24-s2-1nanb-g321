# US21 - As a GSM, I want to add a new entry to the To-Do List.

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


_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class Entry

```java
    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace) {
    super(name, description);
    this.urgencyDegree = urgencyDegree;
    this.duration = duration;
    this.greenSpace = greenSpace;
    this.status = "Pending";
}
```

### Class CreateEntryController 

```java
    public Entry createEntry(Task task, String urgencyDegree, int duration, GreenSpace greenSpace) {

    Entry newEntry = new Entry(task.getName(), task.getDescription(), urgencyDegree, duration, greenSpace);

    toDoListRepository.add(newEntry);

    return newEntry;
}
```

## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a