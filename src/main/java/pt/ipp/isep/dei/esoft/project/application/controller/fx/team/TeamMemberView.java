package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;

public class TeamMemberView {
    private final int teamId;
    private final String collaboratorName;
    private final String collaboratorEmail;
    private final Job collaboratorJob;
    private final ArrayList<Skill> collaboratorSkills;

    public TeamMemberView(int teamId, String collaboratorName, String collaboratorEmail, Job collaboratorJob, ArrayList<Skill> collaboratorSkills) {
        this.teamId = teamId;
        this.collaboratorName = collaboratorName;
        this.collaboratorEmail = collaboratorEmail;
        this.collaboratorJob = collaboratorJob;
        this.collaboratorSkills = collaboratorSkills;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getCollaboratorName() {
        return collaboratorName;
    }

    public String getCollaboratorEmail() {
        return collaboratorEmail;
    }

    public Job getCollaboratorJob() {
        return collaboratorJob;
    }

    public ArrayList<Skill> getCollaboratorSkills() {
        return collaboratorSkills;
    }
}