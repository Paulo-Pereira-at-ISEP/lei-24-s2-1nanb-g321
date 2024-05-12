# US03 - As an HRM, I want to register a collaborator with a job and fundamental characteristics.

## 3. Design - User Story Realization

### 3.1. Rationale



| Interaction ID | Question: Which class is responsible for...                  | Answer                   | Justification (with patterns) |
|:---------------|:-------------------------------------------------------------|:-------------------------|:------------------------------|
| Step 1         | ... instantiating the class that handles the UI?             | CreateEmployeeUI         | Pure Fabrication              |
|                | ... coordinating the US?                                     | CreateEmployeeController | Controller                    |
|                | ... guaranteeing that only one instance is available?        | repositories             | singleton                     |
|                | ... instantiation of repositories?                           | CreateEmployeeController | controller                    |
|                | ... creating employee?                                       | Organization             | Creator                       |
| Step 2         | ... ask for the actor to input data?                         | CreateEmployeeUI         | Pure Fabrication              |
| Step 3         | ... validating the input data?                               | CreateEmployeeUI         | Pure Fabrication              |
|                | ... temporarily keeping the input data?                      | CreateEmployeeUI         | Pure Fabrication              |
| Step 4         | ... showing data to the actor?                               | CreateEmployeeUI         | Pure Fabrication              |
| Step 5         | ... validating data before adding employee (mandatory data)? | Employee                 | Information Expert            |
|                | ... adding a Employee and validating data?                   | Organization             | Information Expert            |
| Step 6         | ... informing the operation success?                         | CreateEmployeeUI         | Pure Fabrication              |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Employee
* Organization
* Job

Other software classes (i.e. Pure Fabrication) identified:

* CreateEmployeeUI
* CreateEmployeeController
* EmployeeRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us01-sequence-diagram-full.svg)

### Split Diagrams

The following partial diagrams are displayed to better illustrate the interactions between the classes for the reference notes of the full diagram.


**Validate Employee**

![Sequence Diagram - Partial - Get Employee](svg/us01-sequence-diagram-partial-validate-skill.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us01-class-diagram.svg)