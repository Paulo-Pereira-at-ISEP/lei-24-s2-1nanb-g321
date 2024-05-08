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

    public List<Employee> getAllEmployees() {
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
        return List.copyOf(employees);
    }

    public Optional<Employee> add(Employee employee) {

        Optional<Employee> newEmployee = Optional.empty();
        boolean operationSuccess = false;

        if (validateEmployee(employee)) {
            newEmployee = Optional.of(employee.clone());
            operationSuccess = employees.add(newEmployee.get());
        }

        if (!operationSuccess) {
            newEmployee = Optional.empty();
        }

        return newEmployee;

    }


}