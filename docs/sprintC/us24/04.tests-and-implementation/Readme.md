# US24 - As a GSM, I want to Postpone an entry in the Agenda to a specific future date.



## 4. Tests

**Test 1:** Check if a valid entry can be successfully added to the to-do list repository.

	@Test
    public void testPostponeEntry_ValidInput() {
    Entry entry = new Entry("Meeting", "Team discussion", "High", 60, new GreenSpace("Park"));
    LocalDate newDate = LocalDate.parse("2024-06-25"); 
    
    entry.postponeEntry(newDate);
    
    assertEquals(entry.getStatus(), "postponed"); 
    assertEquals(entry.getEntryDate(), newDate); 
    }

**Test 2:** Check if the UI handles the exception appropriately.

	@Test
    public void testPostponeEntry_EntryNotFound() throws Exception {
    when(agendaController.getAllEntries()).thenThrow(new Exception("Entry not found")); 
    agendaUI.postponeEntry(); 
   
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

public void postponeEntry(LocalDate newDate) {
    if (newDate.isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("New date cannot be in the past");
    }
    this.setStatus("postponed");
    this.setEntryDate(newDate);
}
```


### Class CreatePostponeAgendaController

```java

public void postponeEntry(String entryName, String newDate, LocalTime newStartTime) throws ValidationException {
    Entry entry = agendaRepository.getEntryByName(entryName);
    entry.setEntryDate(LocalDate.parse(newDate)); 
    entry.setStartTime(newStartTime);
    entry.setStatus("postponed"); // Update status upon postponement
    agendaRepository.addEntry(entry.clone());
    
}
```


### Class CreateAgendaRepository

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