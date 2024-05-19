package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public abstract class Planning {

    Task task;
    double expectedDuration;

    String urgencyDegree;

    boolean status = false;

    public Planning(Task task, double expectedDuration, String urgencyDegree) {
    }

    public abstract boolean changeStatus();

    public double getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(double expectedDuration) {
        this.expectedDuration = expectedDuration;
    }
public Task getTask() {
    return task;
}

public void setTask(Task task) {
    this.task = task;
}
}
