package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;

public class Agenda extends Entry{
    private LocalDate date;

    public Agenda(String name, String description, String urgencyDegree, int expectedDuration, GreenSpace greenSpace) {
        super(name, description, urgencyDegree, expectedDuration, greenSpace);
    }
}
