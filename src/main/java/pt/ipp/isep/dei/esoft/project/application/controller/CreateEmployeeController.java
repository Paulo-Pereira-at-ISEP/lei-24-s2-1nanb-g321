package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

public class CreateEmployeeController {

    private static EmployeeRepository employeeRepository;


    public List<Employee> employee() {
        return employeeRepository.getEmployee();
    }

    public Employee createEmployee(String name, LocalDate birthdate, LocalDate admissionDate, String adress,
                                   int mobile, String email, String docType, int docNumber, int taxPayerId, String role) {

        Employee employee = new Employee(name, birthdate, admissionDate, adress, mobile, email, docType, docNumber, taxPayerId, role);

        return employee;
    }
}
