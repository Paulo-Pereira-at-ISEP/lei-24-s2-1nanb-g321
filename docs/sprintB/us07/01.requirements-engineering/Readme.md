# US07 -  As an FM, I wish to register a vehicle’s check-up.


## 1. Requirements Engineering

### 1.1. User Story Description

As an FM, I wish to register a vehicle's check-up with date, Kms of vehicle and Plate.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	"Fleet Manager (FM) – a person who manages the fleet park, the machines, equipment and vehicles, ensuring their good condition and assigning them to the tasks to be carried out."
>   FM ensuring vehicle's good condition by sending them for check-up.

>  "US06 -  As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency (in Kms)"
>  The registered vehicle needs to have the frequency of maintenance and the kms of the last maintenance.

**From the client clarifications:**

> **Question:** What do I need to record when checking the vehicle?
>
> **Answer:** Date, Kms of vehicle, Plate Number.

### 1.3. Acceptance Criteria

* **AC1:** A vehicle's must be chosen from the list of vehicle's available.
* **AC2:** All required fields (fields defined in topic 1.5) must be filled in.
* **AC3:** A vehicle's check-up will be registered.
* **AC4:** (In)Success of the operation.

### 1.4. Found out Dependencies

* There is a dependency on "US06 - As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency (in Kms)" as there must be the frequency of maintenance and the kms of the last maintenance.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Date
    * Kms of Vehicle
	
* Selected data:
    * Plate Number

**Output Data:**

* (In)Success of the operation
* Next check-up Km.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - US07](svg/us07-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* After the Check-up updates the vehicle data, if necessary.