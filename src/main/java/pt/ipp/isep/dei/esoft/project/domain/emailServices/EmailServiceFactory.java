package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailServiceFactory {
    public static EmailService getEmailService() {
        Properties properties = new Properties();
        try (InputStream input = EmailServiceFactory.class.getClassLoader().getResourceAsStream("email_config.properties")) {
            properties.load(input);
            String providerClassName = properties.getProperty("email.service.provider");
            Class<?> providerClass = Class.forName(providerClassName);
            return (EmailService) providerClass.newInstance();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // Handle error
        }
    }
}

