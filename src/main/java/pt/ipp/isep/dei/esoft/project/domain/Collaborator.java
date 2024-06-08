package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Collaborator extends Employee implements Serializable {

    private ArrayList<Skill> skills;
    private boolean hasTeam;

    public Collaborator(String name, LocalDate dateOfBirth, LocalDate admissionDate, String address, int mobile, String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, String password,String role, ArrayList<Skill> skills) {
        super(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role);
        this.skills = skills;
        this.hasTeam = false;
    }

    public ArrayList<Skill> getSkills() {
        // Creating a deep copy of the skills list to avoid unintentional changes
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
        // Creating a deep copy of the Collaborator object
        return new Collaborator(this.getName(), this.getDateOfBirth(), this.getAdmissionDate(), this.getAddress(), this.getMobile(), this.getEmail(), this.getIdDocType(), this.getDocTypeNumber(), this.getTaxPayerIdNumber(), this.getJob(), this.getPassword(), this.getRole(), this.skills);
    }

    public boolean getHasTeam() {
        return hasTeam;
    }

    public void setHasTeam(boolean hasTeam) {
        this.hasTeam = hasTeam;
    }

    public void receiveMessage(String message) {
        // Implement the logic to receive the message (e.g., display it)
        System.out.println("Message received: " + message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator that = (Collaborator) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail());
    }

    public String toString() {
        return super.getName();
    }
}
