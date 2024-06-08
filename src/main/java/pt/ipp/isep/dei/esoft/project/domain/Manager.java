package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Manager extends Employee implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String department;


    public Manager(String name, LocalDate dateOfBirth, LocalDate admissionDate, String address, int mobile, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, String password, String department, String role) {
        super(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role);
        this.department = department;
    }

    public Manager(String email) {
        super(email);
    }

    public String getName(){
        return super.getName();
    }

    public String getDepartment() {
        return department;
    }

    public Manager clone() {
        return new Manager(this.getName(), this.getDateOfBirth(), this.getAdmissionDate(), this.getAddress(), this.getMobile(), this.getEmail(), this.getIdDocType(), this.getDocTypeNumber(), this.getTaxPayerIdNumber(), this.getJob(), this.getPassword(), this.getRole(), this.department);
    }

    public String toString() {
        return super.getName();
    }
}
