package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.time.LocalDate;

public abstract class Employee {
    private static final String PASSWORD_DEFAULT = "admin";

    private String name;
    private LocalDate dateOfBirth;
    private LocalDate admissionDate;
    private String address;
    private int mobile;
    private String email;
    private String idDocType;
    private int docTypeNumber;
    private int taxPayerIdNumber;
    private Job job;
    private String password;
    private String role;

    public Employee(String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, String password, String role) {
        this.name = nome;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address = morada;
        this.mobile = telemovel;
        this.email = email;
        this.idDocType = idDocType;
        this.docTypeNumber = docTypeNumber;
        this.taxPayerIdNumber = taxPayerIdNumber;
        this.job = job;
        this.password = password;
        this.role = role;
    }

    public Employee(String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job) {
        this.name = nome;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address = morada;
        this.mobile = telemovel;
        this.email = email;
        this.idDocType = idDocType;
        this.docTypeNumber = docTypeNumber;
        this.taxPayerIdNumber = taxPayerIdNumber;
        this.job = job;
        this.password = PASSWORD_DEFAULT;
        this.role = AuthenticationController.ROLE_Collaborator;
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Employee(String email) {
        this.email = email;
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    public abstract Employee clone();

}