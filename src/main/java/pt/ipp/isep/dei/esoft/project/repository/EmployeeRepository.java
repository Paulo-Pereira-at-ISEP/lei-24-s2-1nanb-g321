package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.*;

public class EmployeeRepository{

    private final List<Employee> employees;

    public EmployeeRepository() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    private boolean validateEmployee(Employee employee) {
        boolean isValid = !employees.contains(employee);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Employee> getEmployees() {
        ArrayList<Employee> copy = new ArrayList<>();
        for (Employee employee : employees) {
            copy.add(employee.clone());
        }
        return copy;
    }

    /**
     * Retrieves an `Employee` object from the system based on their email address.
     *
     * @param email The email address of the employee to search for.
     * @return The `Employee` object with the matching email address,
     *         or null if no employee is found.
     *
     * @implNote This method iterates through the internal collection of employees (`employees`)
     *          and compares their email addresses with the provided `email` parameter.
     *          If a match is found, the corresponding `Employee` object is returned.
     */
    public Employee getEmployeesByEmail(String email) {

        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)){
                return employee;
            }
        }
        return null;
    }

    /**
     * Adds a new employee to the employee list if validation succeeds.
     *
     * @param employee The employee to add.
     * @return An Optional containing the added employee if the operation succeeds, otherwise an empty Optional.
     */
    public Optional<Employee> add(Employee employee) {

        Optional<Employee> newEmployee = Optional.empty(); // Initialize an empty Optional
        boolean operationSuccess = false;

        // Validate the employee
        if (validateEmployee(employee)) {
            // If validation succeeds, create a clone of the employee and attempt to add it to the list
            newEmployee = Optional.of(employee.clone());
            operationSuccess = employees.add(newEmployee.get());
        }

        // If the operation was not successful, return an empty Optional
        if (!operationSuccess) {
            newEmployee = Optional.empty();
        }

        return newEmployee; // Return the Optional containing the added employee or an empty Optional
    }


}