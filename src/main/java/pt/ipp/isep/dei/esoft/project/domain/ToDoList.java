package pt.ipp.isep.dei.esoft.project.domain;

public class ToDoList extends Planning {

    public ToDoList(Task task, double expectedDuration, String urgencyDegree) {
        super(task, expectedDuration, urgencyDegree); // Call superclass constructor
    }

    @Override
    public boolean changeStatus() {

        return status;
    }
    public ToDoList clone() {
        return new ToDoList(this.task, this.expectedDuration, this.urgencyDegree);
    }
}

