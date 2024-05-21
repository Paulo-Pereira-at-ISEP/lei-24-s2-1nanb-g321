package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Collaborator extends Employee{
    private ArrayList<Skill> skills;

    public Collaborator(String name, LocalDate dateOfBirth, LocalDate admissionDate, String address, int mobile, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, String password,String role, ArrayList<Skill> skills) {
        super(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role);
        this.skills = skills;
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

    public Collaborator clone() {
        return new Collaborator(this.getName(), this.getDateOfBirth(), this.getAdmissionDate(), this.getAddress(), this.getMobile(), this.getEmail(), this.getIdDocType(), this.getDocTypeNumber(), this.getTaxPayerIdNumber(), this.getJob(), this.getPassword(), this.getRole(), this.skills);
    }
}
