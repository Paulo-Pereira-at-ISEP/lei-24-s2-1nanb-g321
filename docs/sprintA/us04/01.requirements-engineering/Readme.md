# US04 - As an HRM, I want to assign one or more skills to a collaborator.



## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to assign one or more skills, such as driving vehicles of different types, operating machines such as backhoes or tractors, tree pruning or application of phytopharmaceutics to a collaborator.
### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	"...driving vehicles of different types (e.g. light, or heavy), operating machines such as backhoes or tractors; tree pruning; application of phytopharmaceuticals."
Those where the skills specified by the client.
>	

**From the client clarifications:**

> **Question:** Can skills only be assigned to a collaborator one time?
>
> **Answer:** They can be assigned throughout a collaborator career.

> **Question:** 
>
> **Answer:** 
### 1.3. Acceptance Criteria

* **AC1:** At least one "Skill" must be assigned.
* **AC2:** The field "skills" should be able to be edited.
* **AC3:** A message should appear, showing the skill that was assigned. 

### 1.4. Found out Dependencies

* There is a dependency on "US01 - As a Human Resources Manager (HRM), I want to register skills that may be appointed to a collaborator." as there must be at least one skill for the HRM to choose.

### 1.5 Input and Output Data

**Input Data:**
* Skill(s)

**Output Data:**
* (In)Success message 
*  Message with skill linked to a collaborator

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.