# US04 - As an HRM, I want to assign one or more skills to a collaborator.

## 3. Design - User Story Realization

### 3.1. Rationale



| Interaction ID | Question: Which class is responsible for...                  | Answer                   | Justification (with patterns) |
|:---------------|:-------------------------------------------------------------|:-------------------------|:------------------------------|
| Step 1         | ... instantiating the class that handles the UI?             | CreateAssignSkillsUI     | Pure Fabrication              |
| Step 2         | ... coordinating the US?                                     | CreateEmployeeController | Controller                    |
|                | ... guaranteeing that only one instance is available?        | repositories             | singleton                     |
|                | ... instantiation of repositories?                           | CreateEmployeeController | controller                    |
| Step 3         | ... getting the list of skills?                              | SkillsRepository         | Information Expert            |
| Step 4         | ... ask for the actor to select data?                        | CreateAssignSkillsUI     | Pure Fabrication              |
| Step 5         | ... getting the list of employees?                           | EmployeeRepository       | Information Expert            |
| Step 6         | ... ask the actor to select data?                            | CreateEmployeeUI         | Pure Fabrication              |
| Step 7         | ... validating data before adding employee (mandatory data)? | Employee                 | Information Expert            |
|                | ... adding a Employee and validating data?                   | EmployeeRepository       | Information Expert            |
| Step 8         | ... informing the operation success?                         | CreateAssignSkillsUI     | Pure Fabrication              |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Employee


Other software classes (i.e. Pure Fabrication) identified:

* CreateAssignSkillsUI
* CreateEmployeeController
* EmployeeRepository
* SkillRepository


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