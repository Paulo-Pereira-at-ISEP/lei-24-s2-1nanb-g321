package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    @Test
    public void testAddEmployee() {
        EmployeeRepository repository = new EmployeeRepository();
        Employee employee = new Employee("John Doe", "john.doe@example.com");
        repository.addEmployee(employee);
        List<Employee> employees = repository.getAllEmployees();
        assertTrue(employees.contains(employee));
    }

    @Test
    public void testGetEmployees() {
        EmployeeRepository repository = new EmployeeRepository();
        Employee employee = new Employee("John Doe", "john.doe@example.com");
        repository.addEmployee(employee);
        List<Employee> employees = repository.getEmployees();
        assertEquals(1, employees.size());
    }

    @Test
    public void testGetEmployeeByEmail() {
        EmployeeRepository repository = new EmployeeRepository();
        Employee employee = new Employee("John Doe", "john.doe@example.com");
        repository.addEmployee(employee);
        Employee foundEmployee = repository.getEmployeesByEmail("john.doe@example.com");
        assertNotNull(foundEmployee);
        assertEquals("John Doe", foundEmployee.getName());
    }

    @Test
    public void testAddValidEmployee() {
        EmployeeRepository repository = new EmployeeRepository();
        Employee employee = new Employee("John Doe", "john.doe@example.com");
        Optional<Employee> addedEmployee = repository.add(employee);
        assertTrue(addedEmployee.isPresent());
    }

    @Test
    public void testAddDuplicateEmployee() {
        EmployeeRepository repository = new EmployeeRepository();
        Employee employee = new Employee("John Doe", "john.doe@example.com");
        repository.addEmployee(employee); // Add the employee once
        Optional<Employee> addedEmployee = repository.add(employee); // Attempt to add the same employee again
        assertTrue(addedEmployee.isEmpty()); // Adding duplicate should fail
    }
}