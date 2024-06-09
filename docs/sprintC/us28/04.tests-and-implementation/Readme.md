# US28 - As a Collaborator, I wish to consult the tasks assigned to me between two dates.

## 4. Tests 

n/a

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