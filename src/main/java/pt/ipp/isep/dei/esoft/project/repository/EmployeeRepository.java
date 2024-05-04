package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    private boolean validateEmployee(Employee employee) {
        boolean isValid = !Employee.contains(employee);
        return isValid;
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // Return a copy
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

    public void saveEmployee(Employee employee) {

    }

    public List<Employee> getEmployee() {
        return List.copyOf(employees);

    }
}
