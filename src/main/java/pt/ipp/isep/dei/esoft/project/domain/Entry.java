package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Entry extends Task {
    private String  UrgencyDegree;
    private int duration;
    GreenSpace greenSpace;
    private LocalDate entryDate = LocalDate.EPOCH;
    private String status= "Planned";
    private Team team;

    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate entryDate, String status, Team team) {
        super(name, description);
        UrgencyDegree = urgencyDegree;
        this.duration = duration;
        this.greenSpace = greenSpace;
        this.entryDate = entryDate;
        this.status = status;
        this.team = team;
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
        return   "GreenSpace= " + getGreenSpace() +"Title=" + getName() +"Description=" + getDescription() + "Entry{" +
                "UrgencyDegree=" + UrgencyDegree +
                ", duration=" + duration +
                '}' ;
    }

    public Entry clone() {
        return new Entry(getName(), getDescription(), UrgencyDegree, duration, greenSpace, entryDate, status,team);
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
