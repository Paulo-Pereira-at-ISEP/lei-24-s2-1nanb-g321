# US29 - As a Collaborator, I want to record the completion of a task.

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values. 

	@Test
    public void testMarkEntryAsCompleted_Success() {
        when(AgendaRepository.getEntryByName(anyString())).thenReturn(entry);

        controller.markEntryAsCompleted("entryName");

        assertTrue(entry.isCompleted());
        verify(AgendaRepository, times(1)).updateEntry(entry);
    }
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. 

	@Test
    public void testMarkEntryAsCompleted_EntryNotFound() {
        when(AgendaRepository.getEntryById(anyString())).thenReturn(null);

        controller.markEntryAsCompleted("invalidId");

        assertFalse(entry.isCompleted());
        verify(AgendaRepository, never()).updateEntry(entry);
    }
}

## 5. Construction (Implementation)

### Class Entry

```java
public class Entry {
    private List<Vehicle> vehicles;
    private boolean completed;


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
        this.vehicles = new ArrayList<>();
    }
}

public boolean isCompleted() {
    return completed;
}

public void markAsCompleted() {
    completed = true;
}

```


### Class CreateMarkCompleteEntryController

```java

    public void markEntryAsCompleted(String entryName) {
    Entry entry = agendaRepository.getEntryById(entryname);
    if (entry != null) {
        entry.markAsCompleted();
        agendaRepository.updateEntry(entry); // Assuming you have an update method in the repository
    }
}



```


### Class CreateAgendaRepository

```java
 public void markEntryAsCompleted(String entryId) {
    Entry entry = getEntryById(entryId);
    if (entry != null) {
        entry.markAsCompleted();
    }
}
public void updateEntry(Entry updatedEntry) {
    for (int i = 0; i < entries.size(); i++) {
        Entry entry = entries.get(i);
        if (entry.getId().equals(updatedEntry.getId())) {
            entries.set(i, updatedEntry);
            break;
        }
    }
    serialize(); // Assuming you want to persist the changes
}

```



## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a