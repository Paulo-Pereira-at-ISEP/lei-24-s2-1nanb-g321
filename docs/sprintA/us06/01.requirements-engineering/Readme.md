# US06 -  As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency (in Kms)

## 1. Requirements Engineering

### 1.1. User Story Description

As an FM, I wish to register a vehicle new vehicle in system. This vehicle register need Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency (in Kms), Plate Number, Number of Passengers, Number of Doors, Fuel Type, Trailer Machines (Yes/No). FM updated vehicle's km weekly.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	"This type of vehicle can be only for passengers or mixed, light or heavy, open box or closed vans or trucks."
>   Type of vehicle's to register.

>   "Fleet Manager (FM) – a person who manages the fleet park, the machines, equipment and vehicles, ensuring their good condition and assigning them to the tasks to be carried out."
>   Person who will register vehicle.

**From the client clarifications:**

> **Question:** What extra data will I need to record??
>
> **Answer:** Vehicle registration, number of passengers, number of doors, type of fuel, is it possible to trailer machines.


> **Question:** Who updates vehicle kilometers and how often?
>
> **Answer:** FM updates kilometers weekly.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in. (i.e Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, )
* **AC2:** (In)Success of the operation.
* **AC3:** Update of vehicle's Kms by FM weekly.

### 1.4. Found out Dependencies

* There is no dependency.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Brand
    * Model
    * Type
    * Tare
    * Gross Weight
    * Current Km
    * Register Date
    * Acquisition Date
    * Maintenance/Check-up Frequency (in Km)
    * Plate Number
    * Passengers Number
    * Doors Number
    * Fuel Type
    * Trailer Machines (Yes/No)
    * Last Maintenance/Check-up (Km)
    * Last Maintenance/Check-up (Date)

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us06-system-sequence-diagram-alternative-one.svg)