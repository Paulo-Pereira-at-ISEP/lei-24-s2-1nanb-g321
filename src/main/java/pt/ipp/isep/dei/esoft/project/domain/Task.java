package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private static final long serialVersionUID = -2198104427632195287L;
    private String name;
    private String description;


    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(String name) {
        this.name = name;
        this.description = "Default task";
    }

    public Task() {
        this.name = "Default task";
        this.description = "Default task";
    }


    public static boolean contains(Task task) {
        return task != null && task.getName() != null && task.getDescription() != null;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {return name + " - " + description;}

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Task clone() {
        return new Task(this.name, this.description);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill)) {
            return false;
        }
        Task task = (Task) o;
        return name.equals(task.name);
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


    public int compareTo(Task otherTask) {
        if (this.name.equals(otherTask.name)) {
            // Skills are equal, group them together
            return 0;
        } else {
            // Skills are different, compare normally
            return this.name.compareTo(otherTask.name);
        }
    }

}

