package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {
    private String name;
    private String description;

    //private Employee manager;
    public Skill(String name, String description) {
        // Check if the provided name and description are valid
        if (!isValidInput(name) && !isValidInput(description)){
            // Throw an exception if either name or description contains special characters or digits
            throw new IllegalArgumentException("The name and description of the skill cannot contain special characters or digits.");
        }
        this.name = name;
        this.description = description;
    }

    public static boolean contains(Skill skill) {
        return skill != null && skill.getName() != null && skill.getDescription() != null;
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

    public Skill clone() {
        return new Skill(this.name, this.description);
    }
}
