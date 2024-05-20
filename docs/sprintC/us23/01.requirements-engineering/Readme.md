# US23 - As a GSM, I want to assign a Team to an entry in the Agenda

## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to assign a selected team to a task in the agenda.
### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>	"Green Spaces Manager (GSM) - the person responsible for managing
the green spaces in charge of the organization."
>
> Person responsible for adding the task to the agenda.

>  "The Agenda is a crucial mechanism for planning the week’s work."
>
> Definition of agenda in the specifications document.

> "Teams are temporary associations of employees who will carry out a determined set of tasks in one or more green spaces. When creating multipurpose
teams, the number of members and the set of skills that must be covered are
crucial."
> 
> Definition of teams in the specifications document.

**From the client clarifications:**

> **Question:** Can a team be assigned to a task be reassigned to another?
>
> **Answer:** You cannot assign a team who is already assigned to a task.

> **Question:** What are the duration of the tasks?
>
> **Answer:** Half a day, quarter of a day or select specific hours.

### 1.3. Acceptance Criteria

* **AC1:** A message must be sent to all team members informing them about the assignment.
* **AC2:** Different email services can send the message. These services must be defined through a configuration file to allow the use of different platforms (e.g. Gmail, DEI’s email service, etc.).
* **AC3:** The GSM cannot assign a team who is already assigned to a task.
### 1.4. Found out Dependencies

* There is a dependency on "US22 - As a GSM, I want to add a new entry in the Agenda."
* There is a dependency on "US05 - As an HRM, I want to generate a team proposal automatically."
* 
### 1.5 Input and Output Data

**Input Data:**
* Selected data:
    * Tasks
    * Teams
    * Duration of the task.
**Output Data:**

* Success message with Team and Task listed.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us23-system-sequence-diagram.svg)