# US08 -  As an FM, I want to list the vehicles needing the check-up. 

## 1. Requirements Engineering

### 1.1. User Story Description

As a fleet manager, I want to list the vehicles needing the check-up.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	"Fleet Manager (FM) – a person who manages the fleet park, the machines, equipment and vehicles, ensuring their good condition and assigning them to the tasks to be carried out." 
>   Person who will list the vehicles who need the check-up.

>   "Vehicles are needed to carry out the tasks assigned to the teams as well as to transport machines and equipment."
>   Uses of the vehicles.

>   "This type of vehicle can only be for passengers or mixed, light or heavy, open box or closed vans or trucks."
>   Types of vehicles to list.

**From the client clarifications:**

> **Question:** What content do you want presented in the list?
>
> **Answer:** List the vehicles that are almost needing a check-up.

> **Question:** What are the factors that define if a vehicle needs a check-up?
>
> **Answer:** The vehicle needs to be in the range of 200 to 300 km.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US06 - As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency (in Kms)." as we need to know which vehicles are registered and their information to know which vehicles we need to list for the check-up.
* There is a dependency on "US07 - As an FM, I wish to register a vehicle’s check-up." as we need to list the vehicles that have a check-up registered from the overall vehicles.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference
    * a designation 
    * an informal description
    * a technical description
    * an estimated duration
    * an estimated cost
	
* Selected data:
    * a task category 

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published".