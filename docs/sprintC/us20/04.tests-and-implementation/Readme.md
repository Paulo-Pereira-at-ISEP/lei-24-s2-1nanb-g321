# US20 - As a Green Space Manager (GSM), I want to register a green space (garden, medium-sized park or large-sized park) and its respective area.

## 4. Tests 

Test 1: Verify that the constructor of the GreenSpace class properly initializes the attributes and that the getter methods return the correct values.

	@Test
    public void testGreenSpace_ConstructorAndGetters() {
        String name = "Central Park";
        double area = 1000000.0;
        String classification = "Urban Park";
        Employee manager = new Manager("gsm1@this.app"); // Assuming Employee class exists

        GreenSpace greenSpace = new GreenSpace(name, area, classification, manager);

        assertEquals(greenSpace.getName(), name);
        assertEquals(greenSpace.getArea(), area, 0.001); // Delta for double comparison
        assertEquals(greenSpace.getClassification(), classification);
        assertEquals(greenSpace.getManager(), manager);
    }

**Test 2:** Check that it is possible to add a valid GreenSpace instance to the GreenSpaceRepository.

	@Test
    public void testGreenSpaceRepository_addGreenSpace_Valid() {
        GreenSpace greenSpace = new GreenSpace("Central Park", 1000000.0, "Urban Park", null); // Assuming Employee is nullable
        GreenSpaceRepository repository = new GreenSpaceRepository();

        repository.addGreenSpace(greenSpace);

        List<GreenSpace> greenSpaces = repository.getGreenSpaces();
        assertTrue(greenSpaces.contains(greenSpace));
    }

_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class GreenSpace

```java
    public GreenSpace(String name, double area, String classification, Employee manager) {
    this.name = name;
    this.area = area;
    this.classification = classification;
    this.manager = manager;
}
```

### Class CreateGreenSpaceController

```java
public GreenSpace createGreenSpace(String name, double area, String classification, Manager manager ) {

    GreenSpace newGreenSpace = new GreenSpace(name, area, classification, manager);

    greenSpaceRepository.add(newGreenSpace);

    return newGreenSpace;
}
```

### Class GreenSpaceRepository

```java
public Optional<GreenSpace> add(GreenSpace greenSpace) {
    
    if (greenSpace == null) {
        throw new NullPointerException("GreenSpace cannot be null");
    }

    if (validateGreenSpace(greenSpace)) {
        GreenSpace clonedGreenSpace = greenSpace.clone();
        if (greenSpaces.add(clonedGreenSpace)) {
            serialize();
            return Optional.of(clonedGreenSpace);
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