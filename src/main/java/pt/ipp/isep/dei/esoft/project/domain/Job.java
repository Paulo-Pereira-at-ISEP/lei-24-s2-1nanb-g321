package pt.ipp.isep.dei.esoft.project.domain;

public class Job {
    private String name;
    private String description;

    public Job(String name, String description) {
        // Check if the provided name and description are valid
        if (!isValidInput(name) && !isValidInput(description)){
            // Throw an exception if either name or description contains special characters or digits
            throw new IllegalArgumentException("The name and description of the job cannot contain special characters or digits.");
        }
        this.name = name;
        this.description = description;
    }

    public static boolean contains(Job job) {
        return job.getName() != null;
    }


    private boolean isValidInput(String name) {
        // Allow only letters and spaces
        String regex = "^[a-zA-Z\\s]+$";
        // Check if the input matches the regular expression
        return name.matches(regex);
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
    public Job clone() {
        return new Job(this.name, this.description);
    }
}
