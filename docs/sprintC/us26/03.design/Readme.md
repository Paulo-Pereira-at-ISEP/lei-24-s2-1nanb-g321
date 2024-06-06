# US26 - As a GSM, I want to assign one or more vehicles to an entry in the Agenda.

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                                                                                   | Answer                             | Justification (with patterns)                                                                                                                                                                                |
|:---------------|:------------------------------------------------------------------------------------------------------------------------------|:-----------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor                                                                                                | AddVehiclesToAgendaEntryUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                                                                                |
|                | ... instantiating the class that handles the UI?                                                                              | AddVehiclesToAgendaEntryUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                                                                                |
|                | ... coordinating the US?                                                                                                      | AddVehiclesToAgendaEntryController | Ensures decoupling between the UI layer and the Domain layer. (Controller, Low coupling, High Cohesion)                                                                                                      |
|                | ... instantiating the controller                                                                                              | AddVehiclesToAgendaEntryUI         | There is no reason to assign this responsibility to any existing class in the Domain Model (Pure Fabrication)                                                                                                |
|                | ... organize the repositories of the current app?                                                                             | Repositories                       | There is no reason for other class to have this responsibility. Aggregates all the repositories. (Pure Fabrication)                                                                                          |
|                | ... know the current logged in user?                                                                                          | UserSession                        | There is no reason for other class to have this responsibility. Has in itself the instance of the Authentication Repository (Pure Fabrication, Information Expert)                                           |
|                | ... knowing all the Agenda Entries and filter by criteria?                                                                    | AgendaRepository                   | Knows all the Agenda Entries instances (Information Expert, Pure Fabrication)                                                                                                                                |
| Step 2         | ... showing the entries collected at the Agenda and prompting the selection of an entry?                                      | AddVehiclesToAgendaEntryUI         | Interaction with the user (Pure Fabrication)                                                                                                                                                                 |
| Step 3         | ... display the form for the actor to input data?                                                                             | AddVehiclesToAgendaEntryUI         | Interaction with the user, there is no reason for other class to have this responsibility (Pure Fabrication)                                                                                                 |
|                | ... temporarily keeping the input data?                                                                                       | AddVehiclesToAgendaEntryUI         | Before passing the data to the AddVehiclesToAgendaEntryController for further coordination (Pure Fabrication)                                                                                                |
|                | ... validating  input data format locally?                                                                                    | AddVehiclesToAgendaEntryUI         | Should be responsible for validating the input data format (Information Expert)                                                                                                                              |
|                | ... coordinating the US?                                                                                                      | AddVehiclesToAgendaEntryController | Ensures decoupling between the UI layer and the Domain layer. (Controller, Low coupling, High Cohesion)                                                                                                      |
|                | ... filtering the vehicles being used in the given task execution time?                                                       | Employee                           | In the domain model the Employee, as a GSM, is the responsible for managing the Agenda. Therefore is will be able to acquire information concerning the vehicles related to each entry. (Information Expert) |
|                | ... organize the repositories of the current app?                                                                             | Repositories                       | There is no reason for other class to have this responsibility. Aggregates all the repositories. (Pure Fabrication)                                                                                          |
|                | ... knowing all the Vehicles registered in the system?                                                                        | VehicleRepository                  | Knows all the Vehicle instances (Information Expert, Pure Fabrication)                                                                                                                                       |
|                | ... filtering the vehicles being used in the given task execution time?                                                       | Employee                           | In the domain model the Employee, as a GSM, is the responsible for managing the Agenda. Therefore is will be able to acquire information concerning the vehicles related to each entry. (Information Expert) |
| Step 4         | ... showing all the available vehicles for the given task execution time and prompting the selection of one or more vehicles? | AddVehiclesToAgendaEntryUI         | Ensures decouple between the UI layer and the Domain layer. Answers the UI requests. (Controller, Low coupling, High Cohesion)                                                                               |
| Step 5         | ... display the form for the actor to input data?                                                                             | AddVehiclesToAgendaEntryUI         | Interaction with the user, there is no reason for other class to have this responsibility (Pure Fabrication)                                                                                                 |
|                | ... temporarily keeping the input data?                                                                                       | AddVehiclesToAgendaEntryUI         | Before passing the data to the AddVehiclesToAgendaEntryController for further coordination (Pure Fabrication)                                                                                                |
|                | ... validating  input data format locally?                                                                                    | AddVehiclesToAgendaEntryUI         | Should be responsible for validating the input data format (Information Expert)                                                                                                                              |
| Step 6         | ... showing the selected data end requesting confirmation?                                                                    | AddVehiclesToAgendaEntryUI         | Interaction with the user (Pure Fabrication)                                                                                                                                                                 |
| Step 7         | ... display the form for the actor to input data?                                                                             | AddVehiclesToAgendaEntryUI         | Interaction with the user, there is no reason for other class to have this responsibility (Pure Fabrication)                                                                                                 |
|                | ... temporarily keeping the input data?                                                                                       | AddVehiclesToAgendaEntryUI         | Before passing the data to the PostponeAgendaEntryController for further coordination (Pure Fabrication)                                                                                                     |
|                | ... validating  input data format locally?                                                                                    | AddVehiclesToAgendaEntryUI         | Should be responsible for validating the input data format (Information Expert)                                                                                                                              |
|                | ... coordinating the US?                                                                                                      | AddVehiclesToAgendaEntryController | Ensures decoupling between the UI layer and the Domain layer. (Controller, Low coupling, High Cohesion)                                                                                                      |
|                | ... adding Vehicles to an Entry?                                                                                              | Employee                           | In the domain model the Employee, as a GSM, is the responsible for managing the Agenda as well as it's Entries. (Information Expert)                                                                         |
|                | ... knowing the required data to add a Vehicle to an Entry?                                                                   | Entry                              | Knows its own data (Information Expert)                                                                                                                                                                      |
| Step 8         | ... informing the operation success?                                                                                          | AddVehiclesToAgendaEntryUI         | Interaction with the user (Pure Fabrication)                                                                                                                                                                 |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Agenda
* Vehicle
* Employee
 

Other software classes (i.e. Pure Fabrication) identified: 

* AddVehiclesToAgendaEntryUI
* AddVehiclesToAgendaEntryController
* AgendaRepository
* VehicleRepository


## 3.2. Sequence Diagram (SD)


### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us26-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us26-sequence-diagram-partial-get-employee.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us26-class-diagram.svg)