package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Job {
    private String name;
    private String description;

    public Job(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Job(String name) {
        this.name = name;
        this.description = "Default description";
    }

    public Job() {
        this.name = "Default name";
        this.description = "Default description";
    }

    public static boolean contains(Job job) {
        return job.getName() != null && job.getName() != null && job.getDescription() != null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        Job job = (Job) o;
        return name.equals(job.name);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Job clone() {
        return new Job(this.name, this.description);
    }

    public int hashCode() {
        return Objects.hash(name, description);
    }
}

