# US01 - As a Human Resources Manager (HRM), I want to register skills that may be appointed to a collaborator.

## 3. Design - User Story Realization 

### 3.1. Rationale



| Interaction ID | Question: Which class is responsible for...                                      | Answer                | Justification (with patterns)                                                                                                                                                                       |
|:---------------|:---------------------------------------------------------------------------------|:----------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor                                                   | CreateSkillUI         | Pure Fabrication (there is no reason to assign this responsibility to any existing class in the Domain Model)                                                                                       |
|                | ... instantiating the class tha handles the UI?                                  | CreateSkillUI         | Pure Fabrication                                                                                                                                                                                    |
|                | ... coordinating the US?                                                         | CreateSkillController | Controller                                                                                                                                                                                          |
| ???--->        | ... knowing the user using the system?                                           | UserSession           | IE: cf. A&A component documentation.                                                                                                                                                                |
|                |                                                                                  | Organization          | IE: knows/has its own Employees                                                                                                                                                                     |
|                |                                                                                  | Employee              | IE: knows its own data (e.g. email)                                                                                                                                                                 |
| Step 2         | ... display the form for the actor to input data?                                | CreateSkillUI         | Pure Fabrication (interaction with the user, there is no reason for other class to have this responsibility)                                                                                        |
| Step 3         | ... validating the inputted data?                                                | CreateSkillUI         | Pure Fabrication (validating only data types, business rules are validated in a more internal layer of the system)                                                                                  |
|                | ... temporarily keeping the input data?                                          | CreateSkillUI         | Pure Fabrication (before passing the data to the CreateSkillController for further coordination)                                                                                                    |
| Step 4         | ... showing all data and requesting confirmation?                                | CreateSkillUI         | Pure Fabrication (interaction with the user)                                                                                                                                                        |
| Step 5         | ... creating the skill object?                                                   | SkillRepository       | Pure Fabrication / Creator (there is no reason for other class to have this responsibility / aggregates instances of Skills, and Skills are not specific to the Organization nor the Collaborators) |
|                | ... validating (mandatory) data locally??                                        | Skill                 | Information Expert (should be responsible for validating its own data)                                                                                                                              |
|                | ... adding the skill to a collection and globally validating duplicated records? | SkillRepository       | Information Expert (knows all the Skill instances)                                                                                                                                                  |
| Step 6         | ... informing the operation success?                                             | CreateSkillUI         | Pure Fabrication                                                                                                                                                                                    |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* Task

Other software classes (i.e. Pure Fabrication) identified: 

* CreateTaskUI  
* CreateTaskController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us006-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us006-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us006-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us006-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)