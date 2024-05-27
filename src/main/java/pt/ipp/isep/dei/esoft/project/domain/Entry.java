package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Entry extends Task{
    private String  UrgencyDegree;
    private int duration;

    public Entry(String name, String description, String urgencyDegree, int duration) {
        super(name, description);
        UrgencyDegree = urgencyDegree;
        this.duration = duration;
    }

    public String getUrgencyDegree() {
        return UrgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        UrgencyDegree = urgencyDegree;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return  "Title=" + getName() +"Description=" + getDescription() + "Entry{" +
                "UrgencyDegree=" + UrgencyDegree +
                ", duration=" + duration +
                '}' ;
    }
    public Entry clone() {
        return new Entry(getName(), getDescription(), UrgencyDegree, duration);
    }

}
