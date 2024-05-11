package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamOperations extends Employee{
    private List<Skill> copyOfSkills;



    public TeamOperations (Employee employee, List<Skill> copyOfSkills) {
        super(employee.getName(), employee.getDateOfBirth(), employee.getAdmissionDate(), employee.getAddress(), employee.getMobile(), employee.getEmail(), employee.getIdDocType(), employee.getDocTypeNumber(), employee.getTaxPayerIdNumber(), employee.getJob(), (ArrayList<Skill>) List.copyOf(employee.getSkills()), employee.getRole(), employee.getPassword());
        this.copyOfSkills = copyOfSkills;
    }

    public List<Skill> getCopyOfSkills() {
        return copyOfSkills;
    }

    public void setCopyOfSkills(List<Skill> copyOfSkills) {
        this.copyOfSkills = copyOfSkills;
    }


}
