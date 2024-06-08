package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class Skill implements Serializable {
    private static final long serialVersionUID = -6212549765225570048L;
    private String name;
    private String description;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Skill(String name) {
        this.name = name;
        this.description = "Default skill";
    }

    public Skill() {
        this.name = "Default skill";
        this.description = "Default skill";
    }

    /**
     * Checks if a `Skill` object is considered "present" in the context
     * used by this method.
     *
     * @param skill The `Skill` object to check for presence.
     * @return true if the `skill` object is not null and both its name and description fields are not null,
     *         false otherwise.
     */
    public static boolean contains(Skill skill) {
        return skill != null && skill.getName() != null && skill.getDescription() != null;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
/*
    @Override
    public String toString() {return name + " - " + description;}

 */
    @Override
    public String toString() {return this.name;}

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Skill clone() {
        return new Skill(this.name, this.description);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill)) {
            return false;
        }
        Skill skill = (Skill) o;
        return name.equals(skill.name);
    }
    /**
     * Calculates a hash code for this `Skill` object.
     *
     * @return A hash code value used for identification and comparison.
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    /**
     * Compares the current `Skill` object with another `Skill` object for ordering.
     *
     * @param otherSkill The `Skill` object to be compared with.
     * @return A negative integer, zero, or a positive integer as this skill is less than,
     *         equal to, or greater than the `otherSkill` based on their names.
     *
     * @throws NullPointerException if the `otherSkill` parameter is null.
     */
    public int compareTo(Skill otherSkill) {
        if (this.name.equals(otherSkill.name)) {
            // Skills are equal, group them together
            return 0;
        } else {
            // Skills are different, compare normally
            return this.name.compareTo(otherSkill.name);
        }
    }

}
