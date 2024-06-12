# US20 - As a Green Space Manager (GSM), I want to register a green space (garden, medium-sized park or large-sized park) and its respective area.

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...                 | Answer                     | Justification (with patterns)           |
|:---------------|:------------------------------------------------------------|:---------------------------|:----------------------------------------|
| Step 1         | ... interacting with the actor?                             | CreateGreenSpaceUI         | Pure Fabrication                        |
|                | ... instantiating the class that handles the UI?            | CreateGreenSpaceUI         | Pure Fabrication                        |
|                | ... instantiating the controller?                           | CreateGreenSpaceUI         | Pure Fabrication                        |
|                | ... coordinating the US?                                    | CreateGreenSpaceController | Controller                              |
| Step 2         | ... requesting the data needed to register the green space? | CreateGreenSpaceUI         | Pure Fabrication                        |
| Step 3         | ... display the form for the actor to input data?           | CreateGreenSpaceUI         | Pure Fabrication                        |
|                | ... temporarily keeping the input data?                     | CreateGreenSpaceUI         | Pure Fabrication                        |
|                | ... validating input data format locally?                   | CreateGreenSpaceUI         | Information Expert                      |
| Step 4         | ... showing the selected data and requesting confirmation?  | CreateGreenSpaceUI         | Pure Fabrication                        |
| Step 5         | ... display the form for the actor to input data?           | CreateGreenSpaceUI         | Pure Fabrication                        |
|                | ... temporarily keeping the input data?                     | CreateGreenSpaceUI         | Pure Fabrication                        |
|                | ... validating input data format locally?                   | CreateGreenSpaceUI         | Information Expert                      |
|                | ... creating a green space?                                 | Employee                   | Creator, Information Expert             |
|                | ... coordinating the US?                                    | CreateGreenSpaceController | Controller, Low coupling, High Cohesion |
|                | ... guaranteeing that only one instance is available?       | repositories               | singleton                               |
|                | ... organize the repositories of the current app?           | Repositories               | Pure Fabrication                        |
|                | ... know the current logged in user?                        | UserSession                | Pure Fabrication, Information Expert    |
|                | ... knowing the required data to register a Green Space?    | GreenSpace                 | Information Expert                      |
|                | ... adding the new Green Space to the collection?           | GreenSpaceRepository       | Information Expert, Pure Fabrication    |
| Step 6         | ... informing the operation success?                        | CreateGreenSpaceUI         | Pure Fabrication                        |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* GreenSpace

Other software classes (i.e. Pure Fabrication) identified: 

* CreateGreenSpaceUI  
* CreateGreenSpaceController
* GreenSpaceRepository


## 3.2. Sequence Diagram (SD)


### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us20-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us20-sequence-diagram-partial-get-employee.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us20-class-diagram.svg)