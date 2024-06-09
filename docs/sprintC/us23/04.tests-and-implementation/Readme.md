# US08 - As a VFM, I want the system to produce a list (report) of vehicles needing maintenance.

## 4. Tests

**Test 1:** Verifies that a valid entry can be successfully added to the ToDoListRepository.

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
            assertTrue(agendaRepository.getAllEntrys().contains(entry));
        }

**Test 2:** Checks that the getEntriesByDate() method of the ToDoListRepository correctly filters and returns entries that match a given date.
    
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

**Test 3:** that the getEntriesBetweenTwoDates() method of the ToDoListRepository correctly filters and returns entries that fall within a specified date range.

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

            // Given
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(7);
            Entry entry1 = new Entry("Meeting", "Project meeting", "High", 2, new GreenSpace("Park", 2000,"garden", new Manager("gsm1@this.app")), date, 10, new Team(collaborators));
            Entry entry2 = new Entry("Training", "Project training", "Medium", 1, new GreenSpace("Office", 2000, "garden", new Manager("gsm1@this.app")), date, 14, new Team(collaborators));
            agendaRepository.addEntry(entry1);
            agendaRepository.addEntry(entry2);

            // When
            List<Entry> filteredEntries = agendaRepository.getEntriesBetweenTwoDates(startDate, endDate);

            // Then
            assertEquals(2, filteredEntries.size());
        }




## 5. Construction (Implementation)

### Class Entry



### Class Entry

```java
 public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate entryDate, int hour) {
    super(name, description);
    this.urgencyDegree = urgencyDegree;
    this.duration = duration;
    this.greenSpace = greenSpace;
    this.entryDate = entryDate;
    this.status = "Planned";
    this.hour = hour;
}
```

### Class CreateTeamToEntryController 

```java
public Entry createEntry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate date, int hour) {

    Entry newEntry = new Entry(name, description, urgencyDegree, duration, greenSpace, date, hour);

    agendaRepository.add(newEntry);

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