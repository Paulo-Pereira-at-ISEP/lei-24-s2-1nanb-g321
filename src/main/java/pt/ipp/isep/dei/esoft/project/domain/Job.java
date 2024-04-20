package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

public class Job {
    private String name;
    private String description;

    public Job(String name, String description) {
        // Check if the provided name and description are valid
        if (!isValidInput(name) || !isValidInput(description)){
            // Throw an exception if either name or description contains special characters or digits
            throw new IllegalArgumentException("The name and description of the job cannot contain special characters or digits.");
        }
        this.name = name;
        this.description = description;
    }

    private boolean isValidInput(String name) {
        String regex = "^[a-zA-Z0-9_-]+$";
        return name.matches(regex);
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
}
