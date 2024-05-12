package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void ensureTwoEmployeesWithSameEmailEquals() {
        Employee employee1 = new Employee("john.doe@this.company.com");
        Employee employee2 = new Employee("john.doe@this.company.com");
        assertEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeWithDifferentEmailNotEquals() {
        Employee employee1 = new Employee("john.doe@this.company.com");
        Employee employee2 = new Employee("jane.doe@this.company.com");
        assertNotEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeDoesNotEqualNull() {
        Employee employee1 = new Employee("john.doe@this.company.com");
        assertNotEquals(employee1, null);
    }

    @Test
    void ensureEmployeeDoesNotEqualOtherObject() {
        Employee employee1 = new Employee("john.doe@this.company.com");
        assertNotEquals(employee1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        Employee employee1 = new Employee("john.doe@this.company.com");
        assertEquals(employee1, employee1);
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        Employee employee1 = new Employee("john.doe@this.company.com");
        Employee employee2 = new Employee("jane.doe@this.company.com");
        assertNotEquals(employee1.hashCode(), employee2.hashCode());
    }


    @Test
    void ensureHasEmailFailsForDifferentEmails() {
        String email = "john.doe@this.company.com";
        Employee employee = new Employee(email);
        assertFalse(employee.hasEmail("jane.doe@this.company.com"));

    }

    @Test
    public void testConstructorWithSkills() {
        ArrayList<Skill> skills = new ArrayList<>();
        Employee employee = new Employee(skills);
        assertEquals(skills, employee.getSkills());
    }

    @Test
    public void testFullConstructor() {
        LocalDate dateOfBirth = LocalDate.of(1990, 5, 1);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);
        Job job = new Job("Software Engineer", "Develop software applications");
        ArrayList<Skill> skills = new ArrayList<>();
        Employee employee = new Employee("John Doe", dateOfBirth, admissionDate, "123 Main St", 123456789, "john@example.com", "ID", 123456, 789, job, skills, "Employee", "password");
        assertEquals("John Doe", employee.getName());
        assertEquals(dateOfBirth, employee.getDateOfBirth());
        assertEquals(admissionDate, employee.getAdmissionDate());
        assertEquals("123 Main St", employee.getAddress());
        assertEquals(123456789, employee.getMobile());
        assertEquals("john@example.com", employee.getEmail());
        assertEquals("ID", employee.getIdDocType());
        assertEquals(123456, employee.getDocTypeNumber());
        assertEquals(789, employee.getTaxPayerIdNumber());
        assertEquals(job, employee.getJob());
        assertEquals(skills, employee.getSkills());
        assertEquals("Employee", employee.getRole());
        assertEquals("password", employee.getPassword());
    }

    @Test
    public void testConstructorWithEmail() {
        Employee employee = new Employee("john@example.com");
        assertEquals("john@example.com", employee.getEmail());
    }

    @Test
    public void testDefaultConstructor() {
        Employee employee = new Employee("John Doe", LocalDate.of(1990, 5, 1), LocalDate.of(2020, 1, 1), "123 Main St", 912345678, "john@example.com", "CC", 12345678, 123456789);
        assertEquals("John Doe", employee.getName());
        assertEquals(LocalDate.of(1990, 5, 1), employee.getDateOfBirth());
        assertEquals(LocalDate.of(2020, 1, 1), employee.getAdmissionDate());
        assertEquals("123 Main St", employee.getAddress());
        assertEquals(912345678, employee.getMobile());
        assertEquals("john@example.com", employee.getEmail());
        assertEquals("CC", employee.getIdDocType());
        assertEquals(12345678, employee.getDocTypeNumber());
        assertEquals(123456789, employee.getTaxPayerIdNumber());
        assertEquals("Nenhum - Sem descrição", employee.getJob().toString());
        assertTrue(employee.getSkills().isEmpty());
        assertEquals(AuthenticationController.ROLE_Collaborator, employee.getRole());
        assertEquals(Employee.setPasswordDefault(), employee.getPassword());
    }

    @Test
    public void testContains() {
        Employee employee = new Employee("John Doe", LocalDate.of(1990, 5, 1), LocalDate.of(2020, 1, 1), "123 Main St", 123456789, "john@example.com", "ID", 123456, 789);
        assertTrue(Employee.contains(employee));
    }

    @Test
    public void testContainsNullEmployee() {
        assertFalse(Employee.contains(null));
    }

    @Test
    public void testContainsNullEmail() {
        Employee employee = new Employee("John Doe", LocalDate.of(1990, 5, 1), LocalDate.of(2020, 1, 1), "123 Main St", 123456789, null, "ID", 123456, 789);
        assertFalse(Employee.contains(employee));
    }

    @Test
    public void testContainsNullDateOfBirth() {
        Employee employee = new Employee("John Doe", null, LocalDate.of(2020, 1, 1), "123 Main St", 123456789, "john@example.com", "ID", 123456, 789);
        assertFalse(Employee.contains(employee));
    }

}