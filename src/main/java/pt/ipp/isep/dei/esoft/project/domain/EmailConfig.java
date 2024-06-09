package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class EmailConfig implements Serializable {
            private String serviceProvider;
            private String username;
            private String password;


        public String getServiceProvider() {
            return serviceProvider;
        }

        public void setServiceProvider(String serviceProvider) {
            this.serviceProvider = serviceProvider;
        }


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
