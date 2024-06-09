# US26 - As a GSM, I want to assign one or more vehicles to an entry in the Agenda.


## 4. Tests

**Test 1:** Asserts that the vehicle is present in the list of vehicles for the entry.

	 @Test
    public void testAssignVehicleToEntry() {
        agendaRepository.assignVehicleToEntry(entry.getId(), vehicle);
        assertTrue(entry.getVehicles().contains(vehicle));
    }

**Test 2:** Asserts that the entry is present and matches the added entry.

	 @Test
    public void testGetEntryById() {
        assertTrue(agendaRepository.getEntryById(entry.getId()).isPresent());
    }

}



## 5. Construction (Implementation)

### Class Entry



### Class Entry

```java
public class Entry {
    private List<Vehicle> vehicles;

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

public void addVehicle(Vehicle vehicle) {
    this.vehicles.add(vehicle);
}


public List<Vehicle> getVehicles() {
    return this.vehicles;
}
```


### Class CreateAddVehicleToEntryController

```java

public void assignVehicleToEntry(String entryId, Vehicle vehicle) {
    Entry entry = agendaRepository.getEntryById(entryId);
    if (entry != null) {
        entry.addVehicle(vehicle);
        agendaRepository.updateEntry(entry);
    }
}


```


### Class CreateAgendaRepository

```java
public void assignVehicleToEntry(String entryId, Vehicle vehicle) {
    Entry entry = entryMap.get(entryId);
    if (entry != null) {
        entry.addVehicle(vehicle);
    }
}
```


## 6. Integration and Demo

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a