package pt.ipp.isep.dei.esoft.project.domain;

public class Agenda extends Planning  {
private String date;

public Agenda(Task task, double expectedDuration, String urgencyDegree, String date) {
    super(task, expectedDuration, urgencyDegree);
    this.date = date;
}

public String getDate() {
    return date;
}

@Override
public boolean changeStatus() {
    return !status;
}
}
