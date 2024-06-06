package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class Entry extends Task {
    private String  urgencyDegree;
    private int duration;
    GreenSpace greenSpace;
    private String status;
    private LocalDate entryDate;
    private int hour;
    private Team team;

    private LocalTime startTime;
    private LocalTime endTime;

    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate entryDate, int hour, Team team) {
        super(name, description);
        this.urgencyDegree = urgencyDegree;
        this.duration = duration;
        this.greenSpace = greenSpace;
        this.entryDate = entryDate;
        this.status = "Planned";
        this.hour = hour;
        this.team = team;
        this.startTime = LocalTime.of(hour, 0); // Assuming start time is at the beginning of the hour
        this.endTime = calculateEndTime();
    }

    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, LocalDate entryDate, int hour) {
        super(name, description);
        this.urgencyDegree = urgencyDegree;
        this.duration = duration;
        this.greenSpace = greenSpace;
        this.entryDate = entryDate;
        this.status = "Planned";
        this.hour = hour;
    }

    public Entry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace) {
        super(name, description);
        this.urgencyDegree = urgencyDegree;
        this.duration = duration;
        this.greenSpace = greenSpace;
        this.status = "Pending";
    }
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // Method to calculate end time based on start time and duration
    private LocalTime calculateEndTime() {
        return startTime.plusHours(duration);
    }


    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return  "Entry: \nGreenSpace= " + getGreenSpace().getName() +"\nTitle=" + super.getName() +"\nDescription=" + super.getDescription() +
                "\nUrgencyDegree=" + urgencyDegree +
                "\nDuration=" + duration +
                '}' ;
    }

    public Entry clone() {
        return new Entry(getName(), getDescription(), urgencyDegree, duration, greenSpace, entryDate, hour, team);
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
