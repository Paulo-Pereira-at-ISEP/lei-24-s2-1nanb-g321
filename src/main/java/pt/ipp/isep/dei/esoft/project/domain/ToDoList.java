package pt.ipp.isep.dei.esoft.project.domain;

public class ToDoList extends  Task{

    private String urgencyDegree;
    private int expectedDuration;
    GreenSpace greenSpace;


    public ToDoList (String name, String description, String urgencyDegree, int expectedDuration, GreenSpace greenSpace) {
        super(name, description); // Call Task constructor with name and description
        this.urgencyDegree = urgencyDegree;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
    }


    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }
    @Override
    public String toString() {
        return super.toString() + "\nUrgency Degree: " + urgencyDegree + "\nExpected Duration: " + expectedDuration + " minutes" +
                (greenSpace != null ? ("\nGreen Space: " + greenSpace.getName()) : ""); // Optional: Include GreenSpace name if available
    }
}

