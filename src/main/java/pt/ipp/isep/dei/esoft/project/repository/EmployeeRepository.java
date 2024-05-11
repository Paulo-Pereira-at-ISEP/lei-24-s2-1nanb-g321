package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;

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
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return new ArrayList<>(List.copyOf(employees));
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