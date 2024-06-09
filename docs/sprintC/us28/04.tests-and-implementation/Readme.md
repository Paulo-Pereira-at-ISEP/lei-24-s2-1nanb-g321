# US28 - As a Collaborator, I wish to consult the tasks assigned to me between two dates.

## 4. Tests 

**Test 1**

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

## 5. Construction (Implementation)

### Class CreateEntryToAgendaController 

```java
public List<Entry> getAllEntries() {
    return agendaRepository.getEntrys();
}

public List<Entry> getSortedEntriesByDate(List<Entry> sortedEntries) {
    return agendaRepository.sortEntriesByDate(sortedEntries);
}
```

### Class AgendaRepository

```java
public ArrayList<Entry> getAllEntrys() {
    return new ArrayList<>(entrys);
}
public List<Entry> getEntriesByDate(LocalDate date) {

    List<Entry> filteredEntries = new ArrayList<>();
    for (Entry entry : entrys) {
        if (date.equals(entry.getEntryDate())) {
            filteredEntries.add(entry);
        }
    }
    return filteredEntries;
}
```


## 6. Integration and Demo 

* A new option on the Collaorator menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a