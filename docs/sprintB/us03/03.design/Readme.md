# US03 - As an HRM, I want to register a collaborator with a job and fundamental characteristics.

## 3. Design - User Story Realization

### 3.1. Rationale



| Interaction ID | Question: Which class is responsible for...                      | Answer                | Justification (with patterns)                                                                                                                  |
|:---------------|:-----------------------------------------------------------------|:----------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor                                   | CreateSkillUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                  |
|                | ... instantiating the class that handles the UI?                 | CreateSkillUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                  |
|                | ... coordinating the US?                                         | CreateSkillController | Ensures decouple between the UI layer and the Domain layer. (Controller, Low coupling, High Cohesion)                                          |
| Step 2         | ... instantiating the controller                                 | CreateSkillUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                  | 
| Step 3         | ... display the form for the actor to input data?                | CreateSkillUI         | Interaction with the user, there is no reason for other class to have this responsibility (Pure Fabrication)                                   |
| Step 4         | ... temporarily keeping the input data?                          | CreateSkillUI         | Before passing the data to the CreateSkillController for further coordination (Pure Fabrication)                                               |
| Step 5         | ... validating the inputted data?                                | CreateSkillUI         | Validating only data types, business rules are validated in the domain layer (Pure Fabrication)                                                |
| Step 6 and 7   | ... showing all data and requesting confirmation?                | CreateSkillUI         | Interaction with the user (Pure Fabrication)                                                                                                   |
| Step 8 and 20  | ... coordinating the creation the skill object?                  | CreateSkillController | Ensures decouple between the UI layer and the Domain layer. Answers the UI requests. (Controller, Low coupling, High Cohesion)                 |
| Step 9 and 10  | ... organize the repositories of the current app?                | Repositories          | There is no reason for other class to have this responsibility. Aggregates all the repositories. (Pure Fabrication)                            |
| Step 11 and 12 | ... know the current logged in user?                             | Repositories          | There is no reason for other class to have this responsibility. Has in itself the instance of the Authentication Repository (Pure Fabrication) |
| Step 13 and 16 | ... creating the skill object?                                   | Employee              | In the domain model is the responsible for creating Skills as a Manager (Creator)                                                              |
| Step 14 and 15 | ... knowing the required data to create a new instance of Skill? | Skill                 | Knows its own data (Information Expert)                                                                                                        |
|                | ... validating (mandatory) data locally??                        | Skill                 | Should be responsible for validating its own data (Information Expert)                                                                         |
| Step 17 and 19 | ... adding the skill to a collection?                            | SkillRepository       | Knows all the Skill instances (Information Expert, Pure Fabrication)                                                                           |
| Step 18        | ... globally validating duplicated records?                      | SkillRepository       | Knows all the Skill instances (Information Expert, Pure Fabrication)                                                                           |
| Step 21        | ... informing the operation success?                             | CreateSkillUI         | Interaction with the user (Pure Fabrication)                                                                                                   |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Employee
* Skill

Other software classes (i.e. Pure Fabrication) identified:

* CreateSkillUI
* CreateSkillController
* SkillRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us01-sequence-diagram-full.svg)

### Split Diagrams

The following partial diagrams are displayed to better illustrate the interactions between the classes for the reference notes of the full diagram.

**Get Employee From Session**

![Sequence Diagram - Partial - Create Task](svg/us01-sequence-diagram-partial-get-employee.svg)

**Validate Skill**

![Sequence Diagram - Partial - Get Employee](svg/us01-sequence-diagram-partial-validate-skill.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us01-class-diagram.svg)