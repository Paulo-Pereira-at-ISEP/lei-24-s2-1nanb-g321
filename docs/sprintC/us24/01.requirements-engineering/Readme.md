# US24 - As a GSM, I want to Postpone an entry in the Agenda to a specific future date.

## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to Postpone an entry in the Agenda to a specific future date due to various reasons such as personnel, equipment or fleet management.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>   "The Agenda is a crucial mechanism for planning the week’s work. Each entry in the Agenda defines a task (that was previously included in the to-do list)."

>   "A team will carry out that task in a green space at a certain time interval on a specific date."

>   "Comparatively analyzing the Agenda entries and the pending tasks (to-do list) allows you to evaluate the work still to be done, the busyness of the week, and the work performed by a team in a green space at a determined time interval and on a specific date."

>   "US22-AsaGSM,I want to add a new entry in the Agenda."

>   "US23 - As a GSM, I want to assign a Team to an entry in the Agenda."

>   "US25 - As a GSM, I want to Cancel an entry in the Agenda."

>   "US26 - As a GSM, I want to assign one or more vehicles to an entry in the Agenda."

**From the client clarifications:**

> **Question:** How should be handled the postponed entry? Should the current entry be deleted?  
>
> **Answer:** No. It should remain in the current Agenda Position, however, it's "status" should be altered to "postponed". Additionally, a copy of the current entry should be created with the updated date of execution.


### 1.3. Acceptance Criteria

* **AC1:** The date entered by the user should be higher than the initially planned date of the task.
* **AC2:** 
* **AC3:** The entry should be postponed.

### 1.4. Found out Dependencies

* There are dependencies with the:

  * "US21 - As a GSM,I want to add a new entry to the To-DoList." - There need to be entries in the "To-Do List" in order for the to be migrated to the "Agenda".
  * "US22 - As a GSM, I want to add a new entry in the Agenda." - An entry (task) shoud be effectively planned in the "Agenda".

### 1.5 Input and Output Data

**Input Data:**

* Date

**Output Data:**

* Confirmation of the selected entry and inputted date.
* Message of operation success

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us24-system-sequence-diagram.svg)