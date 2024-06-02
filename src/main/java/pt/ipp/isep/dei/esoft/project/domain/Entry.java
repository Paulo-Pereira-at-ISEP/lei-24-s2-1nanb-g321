package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Entry extends Task {
    private String  urgencyDegree;
    private int duration;
    GreenSpace greenSpace;
    private String status= "Planned";
    private LocalDate entryDate = LocalDate.EPOCH;
    private int hour;
    private Team team;

    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate entryDate, String status, Team team, int hour) {
        super(name, description);
        urgencyDegree = urgencyDegree;
        this.duration = duration;
        this.greenSpace = greenSpace;
        this.entryDate = entryDate;
        this.status = status;
        this.team = team;
        this.hour = hour;
        this.status = "Planned";
    }



    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate date, String status, int hour) {
    this.urgencyDegree = urgencyDegree;
    this.duration = duration;
    this.greenSpace = greenSpace;
    this.entryDate = date;
    this.status = status;
    this.hour = hour;
    }

    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace) {
    this.urgencyDegree = urgencyDegree;
    this.duration = duration;
    this.greenSpace = greenSpace;
    this.entryDate = LocalDate.now();


    }

    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        urgencyDegree = urgencyDegree;
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
                "UrgencyDegree=" + urgencyDegree +
                ", duration=" + duration +
                '}' ;
    }

    public Entry clone() {
        return new Entry(getName(), getDescription(), urgencyDegree, duration, greenSpace, entryDate, status,team, hour);
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
