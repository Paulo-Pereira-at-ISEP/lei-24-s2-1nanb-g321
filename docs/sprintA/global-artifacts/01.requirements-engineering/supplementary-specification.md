# Supplementary Specification (FURPS+)

## Functionality


>Business rules validation should be respected when recording and updating data.

>Authentication with a password of seven alphanumeric characters, including three capital letters and two digits should be required for all those who wish to use the application, 

>The system should be designed to prevent storage of repeated objects within each extent.


## Usability


>The Graphical User Interface (GUI) should be designed keeping in mind colour blind friendly patterns. 

>During the system development process Javadoc should be used to generate useful documentation for Java code.



## Reliability

>Unit tests for all methods, except for methods that implement Input/Output operations should be implemented by the development team.

>Coverage report should be generated using the JaCoCo plugin.

## Performance

>The system should be designed to be responsive under minimum requirements even in older machines.

>The application's data architecture should be designed to be stored locally in the file system, rather than using complex databases, in order to prioritize speed and loading time.  

## Supportability

>The class structure should be designed to allow easy maintenance and the addition of new features, following the best OO practices.

>The application should be capable of integration with third-party systems, allowing both data import and export. 

## +

### Design Constraints

>The application should be developed in Java language using the IntelliJ IDE.

>All the images/figures produced during the software development process should be recorded in SVG format.

>During the system development, the dev team should adopt the best practices for identifying requirements, and for OO software analysis and design. 

### Implementation Constraints

>The application should support the English language.
 
>The application should be designed to be multiple OS compliant (i.e. Windows, macOS, Linux). 

>During the system development, the team should adopt recognized coding standards (e.g., CamelCase).

>The application should use object serialization to ensure data persistence between two runs of the application.

>The unit tests should be implemented using the JUnit 5 framework. 

### Interface Constraints

>The application’s graphical interface should be developed in JavaFX 11.

### Physical Constraints
>No physical constrains were identified