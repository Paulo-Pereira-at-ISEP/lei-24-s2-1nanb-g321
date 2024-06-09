package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class MessageService implements Serializable {

    private EmailSender emailSender;

    public MessageService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMessage(Collaborator collaborator, String message) {
        String subject = "New Assignment";
        emailSender.sendEmail(collaborator.getEmail(), subject, message);
        System.out.println("Message sent to " + collaborator.getName() + ": " + message);
    }

    public void sendMessageToAllTeamMembers(Team team, String message) {
        for (Collaborator collaborator : team.getCollaborators()) {
            sendMessage(collaborator, message);
        }
    }
}
