package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Collaborator in the system.
 * Extends the Employee class and implements the Serializable interface.
 */
public class Collaborator extends Employee implements Serializable {

    private ArrayList<Skill> skills;
    private boolean hasTeam;


    public Collaborator(String name, LocalDate dateOfBirth, LocalDate admissionDate, String address, int mobile,
                        String email, String idDocType, int docTypeNumber, int taxPayerIdNumber, Job job, String password,
                        String role, ArrayList<Skill> skills) {
        super(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role);
        this.skills = skills;
        this.hasTeam = false;
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

    /**
     * Adds skills to the collaborator's skills list.
     *
     * @param skills The list of skills to add.
     */
    public void addSkill(ArrayList<Skill> skills) {
        for (Skill s : skills) {
            if (!this.skills.contains(s))
                this.skills.add(s);
        }
    }

    /**
     * Creates a deep copy of the Collaborator object.
     *
     * @return A deep copy of the Collaborator object.
     */
    public Collaborator clone() {
        return new Collaborator(this.getName(), this.getDateOfBirth(), this.getAdmissionDate(), this.getAddress(),
                this.getMobile(), this.getEmail(), this.getIdDocType(), this.getDocTypeNumber(), this.getTaxPayerIdNumber(),
                this.getJob(), this.getPassword(), this.getRole(), this.skills);
    }


    public boolean getHasTeam() {
        return hasTeam;
    }

    public void setHasTeam(boolean hasTeam) {
        this.hasTeam = hasTeam;
    }

    /**
     * Receives a message and handles it (e.g., displays it).
     *
     * @param message The message received.
     */
    public void receiveMessage(String message) {
        System.out.println("Message received: " + message);
    }

    /**
     * Overrides the equals method to compare collaborator objects based on name and email.
     *
     * @param o Object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator that = (Collaborator) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail());
    }

    /**
     * Overrides the hashCode method.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail());
    }

    /**
     * Overrides the toString method to return the name of the collaborator.
     *
     * @return The name of the collaborator.
     */
    @Override
    public String toString() {
        return super.getName();
    }
}
