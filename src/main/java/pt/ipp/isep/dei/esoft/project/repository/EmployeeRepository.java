package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
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

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // Return a copy
    }

    public Optional<Employee> add(Employee employee) {

        if (validateEmployee(employee)) {
            // Create a new Employee object with constructor (assuming a copy constructor)
            Employee newEmployee = new Employee(employee.getName(), employee.getDateOfBirth(),
                    employee.getAdmissionDate(), employee.getAddress(),
                    employee.getMobile(), employee.getEmail(),
                    employee.getIdDocType(), employee.getDocTypeNumber(),
                    employee.getTaxPayerIdNumber(), employee.getRole());

            employees.add(newEmployee);
            return Optional.of(newEmployee);
        }
        return Optional.empty();
    }

    public void saveEmployee(Employee employee) {
    }

    public List<Employee> getEmployee() {
        return List.copyOf(employees);

    }
}


