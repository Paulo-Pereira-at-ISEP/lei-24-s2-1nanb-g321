# US27 - As a GSM, I need to list all green spaces managed by me.

## 4. Tests 

n/a

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