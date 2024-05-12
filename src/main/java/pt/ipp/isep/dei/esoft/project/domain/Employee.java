package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {
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
    private ArrayList<Skill> skills;

    private String role;
    private String password;

    public Employee(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Employee(String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, ArrayList<Skill> skills, String role, String password) {
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
        this.skills = skills;
        this.role = role;
        this.password = password;
    }

    public Employee(String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber) {
        this.name = nome;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address = morada;
        this.mobile = telemovel;
        this.email = email;
        this.idDocType = idDocType;
        this.docTypeNumber = docTypeNumber;
        this.taxPayerIdNumber = taxPayerIdNumber;
        this.job = new Job("Nenhum", "Sem descrição");
        this.skills = new ArrayList<>();
        this.role = AuthenticationController.ROLE_Collaborator;
        this.password = PASSWORD_DEFAULT;
    }

    public Employee(String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, ArrayList<Skill> skills) {
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
        this.skills = skills;
        this.role = AuthenticationController.ROLE_Collaborator;
        this.password = PASSWORD_DEFAULT;
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
        this.role = AuthenticationController.ROLE_Collaborator;
        this.password = PASSWORD_DEFAULT;
    }

    public Employee(String email) {
        this.email = email;
    }

    public static boolean contains(Employee employee) {
        return employee != null && employee.getEmail() != null && employee.getDateOfBirth() != null;
    }

    public void getEmployee(String nome, LocalDate dateOfBirth, LocalDate admissionDate, String morada, int telemovel, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, String role) {
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
        return "Collaborator: " + name + "\nBirth date: " + dateOfBirth + "\nAdmission Date: " + admissionDate + "\nAddress: " + address + "\nMobile: "
                + mobile + "\nE-mail: " + email + "\nDocument: " + idDocType + "\nID number: " + docTypeNumber + "\nTax payer ID: " + taxPayerIdNumber +
                "\nJob: " + job + "\nSkill(s): " + skills;
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

    public ArrayList<Skill> getSkills() {
        ArrayList<Skill> copy = new ArrayList<>();
        for (Skill skill : skills) {
            copy.add(skill.clone());
        }
        return copy;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(ArrayList<Skill> skills) {

        for (Skill s : skills){
            if (!this.skills.contains(s))
                this.skills.add(s);
        }

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String setPasswordDefault() {
        return PASSWORD_DEFAULT;
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
        return new Employee(this.name, this.dateOfBirth, this.admissionDate, this.address, this.mobile, this.email, this.idDocType, this.docTypeNumber, this.taxPayerIdNumber, this.job, this.skills, this.role, this.password);
    }

}