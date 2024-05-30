package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class AgendaEntry extends Entry {

    String day;
    int hourOfDay;
    String status = "PLANNED";
    Team team;


    public AgendaEntry(String name, String description, String urgencyDegree, int duration, GreenSpace greenSpace, String day, int hourOfDay,String status, Team team) {
        super(name, description, urgencyDegree, duration, greenSpace);
        this.day = day;
        this.hourOfDay = hourOfDay;
        this.status = status;
        this.team = team;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    @Override
    public String toString() {
        return "AgendaEntry{" +
                "day='" + day + '\'' +
                ", hourOfDay=" + hourOfDay +
                ", greenSpace=" + greenSpace + "team=" + team +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgendaEntry)) return false;
        if (!super.equals(o)) return false;
        AgendaEntry that = (AgendaEntry) o;
        return hourOfDay == that.hourOfDay && Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), day, hourOfDay);
    }
    public AgendaEntry clone() {
        return new AgendaEntry(getName(), getDescription(), getUrgencyDegree(), getDuration(), getGreenSpace(), day, hourOfDay, status, team);
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
