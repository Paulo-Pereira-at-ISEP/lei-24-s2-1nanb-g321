package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {
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

    public static boolean contains(Skill skill) {
        return skill != null && skill.getName() != null && skill.getDescription() != null;
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
    public Skill clone() {
        return new Skill(this.name, this.description);
    }
}
