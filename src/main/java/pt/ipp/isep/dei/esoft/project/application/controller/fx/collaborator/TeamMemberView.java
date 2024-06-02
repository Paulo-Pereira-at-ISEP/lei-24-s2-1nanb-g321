package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

public class TeamMemberView {

    private final int teamId; // Nome da equipe, se necessário
    private final String collaboratorName;


    public TeamMemberView(int teamId, String collaboratorName) {
        this.teamId = teamId;
        this.collaboratorName = collaboratorName;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getCollaboratorName() {
        return collaboratorName;
    }
}