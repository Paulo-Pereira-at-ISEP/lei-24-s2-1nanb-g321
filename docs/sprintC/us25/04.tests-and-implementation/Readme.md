# US25 - As a GSM, I want to Cancel an entry in the Agenda.



## 4. Tests

**Test 1:** Check if the setStatus method of the Entry class correctly updates the status of an entry when it is canceled.

	@Test
    public void testCancelEntry() {
        // Create a sample entry
        Entry entry = new Entry("Sample Entry", "Sample Description", "High", 2, new GreenSpace("Sample GreenSpace"), LocalDate.now(), 10);

        // Cancel the entry
        entry.setStatus("Canceled");

        // Check if the status is updated correctly
        assertEquals("Canceled", entry.getStatus());
    }

**Test 2:** Checks if the cancelEntry method of the AgendaRepository class correctly cancels an entry by updating its status to "Canceled".

	@Test
    public void testCancelEntry() {

        Entry entry = new Entry("Sample Entry", "Sample Description", "High", 2, new GreenSpace("Sample GreenSpace"), LocalDate.now(), 10);
        String entryId = entry.getId(); // Get the ID of the entry

        agendaRepository.addEntry(entry);
        agendaRepository.cancelEntry(entryId);
        var allEntries = agendaRepository.getAllEntrys();
        assertTrue(allEntries.stream().anyMatch(e -> e.getId().equals(entryId)));
        assertEquals("Canceled", allEntries.stream().filter(e -> e.getId().equals(entryId)).findFirst().get().getStatus());
    }
}

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
public void setStatus(String status) {
    this.status = status;
}

```


### Class CreateCancelAgendaController

```java

public void cancelEntry(String entryId) {
    agendaRepository.cancelEntry(entryId);
}
```


### Class CreateAgendaRepository

```java
public void cancelEntry(String entryId) {
    for (Entry entry : entrys) {
        if (entry.getId().equals(entryId)) {
            entry.setStatus("Canceled");
            serialize();
            break; // Once the entry is found and updated, exit the loop
        }
    }
}
```


## 6. Integration and Demo

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a