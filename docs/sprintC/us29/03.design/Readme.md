# US29 - As a Collaborator, I want to record the completion of a task.

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                                              | Answer                         | Justification (with patterns)                                                                                                                                      |
|:---------------|:-----------------------------------------------------------------------------------------|:-------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor                                                           | RecordTaskCompletionUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                                      |
|                | ... instantiating the class that handles the UI?                                         | RecordTaskCompletionUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                                      |
|                | ... coordinating the US?                                                                 | RecordTaskCompletionController | Ensures decoupling between the UI layer and the Domain layer. (Controller, Low coupling, High Cohesion)                                                            |
|                | ... instantiating the controller                                                         | RecordTaskCompletionUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                                      |
|                | ... organize the repositories of the current app?                                        | Repositories                   | There is no reason for other class to have this responsibility. Aggregates all the repositories. (Pure Fabrication)                                                |
|                | ... know the current logged in user?                                                     | UserSession                    | There is no reason for other class to have this responsibility. Has in itself the instance of the Authentication Repository (Pure Fabrication, Information Expert) |
|                | ... knowing all the Agenda Entries and filter by criteria?                               | AgendaRepository               | Knows all the Agenda Entries instances (Information Expert, Pure Fabrication)                                                                                      |
| Step 2         | ... showing the entries collected at the Agenda and prompting the selection of an entry? | RecordTaskCompletionUI         | Interaction with the user (Pure Fabrication)                                                                                                                       |
| Step 3         | ... display the form for the actor to input data?                                        | RecordTaskCompletionUI         | Interaction with the user, there is no reason for other class to have this responsibility (Pure Fabrication)                                                       |
|                | ... temporarily keeping the input data?                                                  | RecordTaskCompletionUI         | Before passing the data to the AddVehiclesToAgendaEntryController for further coordination (Pure Fabrication)                                                      |
|                | ... validating  input data format locally?                                               | RecordTaskCompletionUI         | Should be responsible for validating the input data format (Information Expert)                                                                                    |
| Step 4         | ... showing the selected data end requesting confirmation?                               | RecordTaskCompletionUI         | Interaction with the user (Pure Fabrication)                                                                                                                       |
| Step 5         | ... display the form for the actor to input data?                                        | RecordTaskCompletionUI         | Interaction with the user, there is no reason for other class to have this responsibility (Pure Fabrication)                                                       |
|                | ... temporarily keeping the input data?                                                  | RecordTaskCompletionUI         | Before passing the data to the PostponeAgendaEntryController for further coordination (Pure Fabrication)                                                           |
|                | ... validating  input data format locally?                                               | RecordTaskCompletionUI         | Should be responsible for validating the input data format (Information Expert)                                                                                    |
|                | ... coordinating the US?                                                                 | RecordTaskCompletionController | Ensures decoupling between the UI layer and the Domain layer. (Controller, Low coupling, High Cohesion)                                                            |
|                | ... record the task completion?                                                          | Collaborator                   | In the domain model the Collaborator is the responsible for record the completions of tasks. (Information Expert)                                                  |
|                | ... knowing the required data to record the completion of an Entry?                      | Entry                          | Knows its own data (Information Expert)                                                                                                                            |
| Step 6         | ... informing the operation success?                                                     | RecordTaskCompletionUI         | Interaction with the user (Pure Fabrication)                                                                                                                       |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Agenda
* Collaborator

Other software classes (i.e. Pure Fabrication) identified: 

* RecordTaskCompletionUI
* RecordTaskCompletionController
* AgendaRepository


## 3.2. Sequence Diagram (SD)


### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us29-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us29-sequence-diagram-partial-get-employee.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us29-class-diagram.svg)