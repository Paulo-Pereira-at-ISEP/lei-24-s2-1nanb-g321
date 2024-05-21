/*
package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Organization {
    private final String vatNumber;
    private final List<Manager> managers;
    private String name;
    private String website;
    private String phone;
    private String email;


    public Organization(String vatNumber) {
        this.vatNumber = vatNumber;
        managers = new ArrayList<>();
    }

    public boolean employs(Employee employee) {
        return managers.contains(employee);
    }


    public boolean anyEmployeeHasEmail(String email) {
        boolean result = false;
        for (Manager manager : managers) {
            if (manager.hasEmail(email)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        Organization that = (Organization) o;
        return vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }


    //add employee to organization
    public boolean addManager(Manager manager) {
        boolean success = false;
        if (validateManager(manager)) {
            success = managers.add(employee);
        }
        return success;
    }


    private boolean validateEmployee(Employee employee) {
        return employeesDoNotContain(employee);
    }

    private boolean employeesDoNotContain(Employee employee) {
        return !employees.contains(employee);
    }

    //Clone organization
    public Organization clone() {
        Organization clone = new Organization(this.vatNumber);
        clone.name = (this.name);
        clone.website = (this.website);
        clone.phone = (this.phone);
        clone.email = (this.email);

        for (Manager in : this.managers) {
            clone.managers.add(in.clone());
        }

        return clone;
    }


}
        */

