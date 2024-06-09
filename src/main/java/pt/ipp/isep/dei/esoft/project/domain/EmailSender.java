package pt.ipp.isep.dei.esoft.project.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmailSender {

    private EmailConfig config;

    public EmailSender(EmailConfig config) {
        this.config = config;
    }

    public void sendEmail(String recipient, String subject, String body) {
        String emailContent = "From: " + config.getUsername() + "\n" +
                "To: " + recipient + "\n" +
                "Subject: " + subject + "\n" +
                "Body: " + body + "\n\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("emails.txt", true))) {
            writer.write(emailContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
