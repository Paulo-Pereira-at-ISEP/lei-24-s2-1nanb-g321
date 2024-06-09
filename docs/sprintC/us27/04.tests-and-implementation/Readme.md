# US27 - As a GSM, I need to list all green spaces managed by me.

## 4. Tests 

**Test 1:** 

    @Test
    public void testGreenSpace_ToString() {
    String name = "Central Park";
    GreenSpace greenSpace = new GreenSpace(name, 0.0, "", null);
    assertEquals(greenSpace.toString(), name);
    }

**Test 2:**

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

## 5. Construction (Implementation)

### Class CreateGreenSpaceController 

```java
public List<GreenSpace> getGreenSpacesManaged(String email) {
    return getGreenSpacesRepository().getGreenSpacesManaged(email);
}
```

### Class GreenSpaceRepository

```java
public List<GreenSpace> getGreenSpacesManaged(String email) {
    List<GreenSpace> greenSpacesManaged = new ArrayList<>();
    for (GreenSpace greenSpace : greenSpaces) {
        if (greenSpace.getManager().getEmail().equals(email) ) {
            greenSpacesManaged.add(greenSpace);
        }
    }
    return greenSpacesManaged;
}
```


## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some Green Spaces are bootstrapped while system starts.


## 7. Observations

n/a