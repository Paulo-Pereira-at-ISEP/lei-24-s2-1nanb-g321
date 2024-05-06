package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate admissionDate;
    private String address;
    private int mobile;
    private String email;
    private String idDocType;
    private int docTypeNumber;
    private int taxPayerIdNumber;
    private String role;
    private Job job;
    private ArrayList<Skill> skill;

public Employee(ArrayList<Skill> skill){
 this.skill = skill;
}
    //constructor
    public Employee (String nome,LocalDate dateOfBirth,LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, String role) {
        this.name = nome;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address = morada;
        this.mobile = telemovel;
        this.email = email;
        this.idDocType = idDocType;
        this.docTypeNumber = docTypeNumber;
        this.taxPayerIdNumber = taxPayerIdNumber;
        this.role = role;
        this.job = new Job("Nenhum", "Sem descrição");
        this.skill = new ArrayList<>();
    }

    public Employee (String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, String role, Job job, ArrayList<Skill> skill) {
        this.name = nome;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address = morada;
        this.mobile = telemovel;
        this.email = email;
        this.idDocType = idDocType;
        this.docTypeNumber = docTypeNumber;
        this.taxPayerIdNumber = taxPayerIdNumber;
        this.role = role;
        this.job = job;
        this.skill = skill;
    }
    public Employee (String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, String role, Job job) {
        this.name = nome;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address = morada;
        this.mobile = telemovel;
        this.email = email;
        this.idDocType = idDocType;
        this.docTypeNumber = docTypeNumber;
        this.taxPayerIdNumber = taxPayerIdNumber;
        this.role = role;
        this.job = job;
    }
    public Employee (String email){
        this.email = email;
    }

    public static boolean contains(Employee employee) {
        return employee != null && employee.getEmail() != null && employee.getDateOfBirth() != null;
    }






    public void getEmployee(String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, String role){
        this.name = nome;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address = morada;
        this.mobile = telemovel;
        this.email = email;
        this.idDocType = idDocType;
        this.docTypeNumber = docTypeNumber;
        this.taxPayerIdNumber = taxPayerIdNumber;
        this.role = role;
    }

    //boolean to make employee manager

    //checks if two employees are equal based on their email addresses.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return email.equals(employee.email);
    }

    //method that provides a string representation of an employee’s detail
    @Override
    public String toString() {
        return "Collaborator: " + name + "\nBirth date: "+ dateOfBirth + "\nAdmission Date: " + admissionDate + "\nAddress: " + address + "\nMobile: "
                + mobile + "\nE-mail: " + email + "\nDocument: " + idDocType + "\nID number: " + docTypeNumber + "\nTax payer ID: " + taxPayerIdNumber +
                "\nJob: "+ "\nSkill(s): " + skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdDocType() {
        return idDocType;
    }

    public void setIdDocType(String idDocType) {
        this.idDocType = idDocType;
    }

    public int getDocTypeNumber() {
        return docTypeNumber;
    }

    public void setDocTypeNumber(int docTypeNumber) {
        this.docTypeNumber = docTypeNumber;
    }

    public int getTaxPayerIdNumber() {
        return taxPayerIdNumber;
    }

    public void setTaxPayerIdNumber(int taxPayerIdNumber) {
        this.taxPayerIdNumber = taxPayerIdNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(ArrayList<Skill> skill) {
        this.skill = skill;
    }

    public void addSkill(ArrayList<Skill> skill){
        this.skill.addAll(skill);
    }

    public boolean hasEmail(String email) {
        return false;
    }

/**
 * Clone method.
 *
 * @return A clone of the current instance.
 */
    public Employee clone() {
    return new Employee(this.name, this.dateOfBirth, this.admissionDate, this.address, this.mobile, this.email, this.idDocType, this.docTypeNumber, this.taxPayerIdNumber, this.role, this.job, this.skill);
    }
}