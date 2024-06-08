package pt.ipp.isep.dei.esoft.project.domain.emailServices;

public interface EmailService {
    void sendMessage(String recipient, String subject, String body);
}
