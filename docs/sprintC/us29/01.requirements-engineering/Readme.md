# US29 - As a Collaborator, I want to record the completion of a task.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Collaborator, I want to record the completion of a task that might have been attributed to me or my team.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> "Tasks are carried out on an occasional or regular basis, in one or more green spaces, for example: tree pruning, installation of an irrigation system, and installation of a lighting system."

> "Teams are temporary associations of employees who will carry out a determined set of tasks in one or more green spaces. When creating multipurpose teams, the number of members and the set of skills that must be covered are crucial."

> "The Agenda is a crucial mechanism for planning the week’s work. Each entry in the Agenda defines a task (that was previously included in the to-do list). A team will carry out that task in a green space at a certain time interval on a specific date."

> "... analyzing the Agenda entries and the pending tasks (to-do list) allows you to evaluate the work still to be done, the busyness of the week, and the work performed by a team in a green space at a determined time interval and on a specific date."
 
> "Collaborator – a person who is an employee in the organization and carries out design, construction and/or maintenance tasks for green areas, depending on their skills."
 
> "US05 - As an HRM, I want to generate a team proposal automatically."

> "US23 - As a GSM, I want to assign a Team to an entry in the Agenda."

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:** The Collaborator may only choose between the tasks that are not yet completed.
* **AC2:** The Collaborator may only choose between the tasks in which he is part of the team.
* **AC3:** A task should be completed.

### 1.4. Found out Dependencies

* There are dependencies with the:

    * "US21 - As a GSM,I want to add a new entry to the To-DoList." - There need to be entries in the "To-Do List" in order for the to be migrated to the "Agenda".
    * "US22 - As a GSM, I want to add a new entry in the Agenda." - An entry (task) shoud be effectively planned in the "Agenda".
    * "US23 - As a GSM, I want to assign a Team to an entry in the Agenda." - A team must be assigned to a Task in order for each Collaborator be able to set it as "Completed".

### 1.5 Input and Output Data

**Input Data:**

No input data needed.

**Output Data:**

* Confirmation of the selected data.
* Message of operation success

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us29-system-sequence-diagram.svg)