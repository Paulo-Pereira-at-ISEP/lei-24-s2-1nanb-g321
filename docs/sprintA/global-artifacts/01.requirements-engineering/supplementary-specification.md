# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

(fill in here)
>Business rules validation are respected when recording and updating data.

>For all those who wish to use the application, authentication with a password of seven alphanumeric characters, including three capital letters and two digits is required.

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

>All operations present (un)success messages.

>During the system development process Javadoc was used to generate useful documentation for Java code.



## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

(fill in here )

>Unit tests for all methods, except for methods that implement Input/Output operations were implemented by the development team.

>Coverage report was generated using the JaCoCo plugin.

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

(fill in here )

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

(fill in here )

>The class structure was designed to allow easy maintenance and the addition of new features, following the best OO practices.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

(fill in here )

>The application was developed in Java language using the IntelliJ IDE.

>All the images/figures produced during the software development process were recorded in SVG format.

>During the system development, the dev team adopted the best practices for identifying requirements, and for OO software analysis and design. 

### Implementation Constraints

_Specifies or constraints the code or construction of a system such as:
mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

(fill in here )

>The application supports the English language.

>During the system development, the team adopted recognized coding standards (e.g., CamelCase).

>The application uses object serialization to ensure data persistence between two runs of the application.

>The unit tests should be implemented using the JUnit 5 framework. 

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

(fill in here )

>The application’s graphical interface was developed in JavaFX 11.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )