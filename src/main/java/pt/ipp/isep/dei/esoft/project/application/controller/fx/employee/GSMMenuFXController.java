package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class GSMMenuFXController {

    @FXML
    private Button greenSpacesButton;

    @FXML
    private Button tasksButton;

    @FXML
    private Button toDoListButton;

    @FXML
    private Button agendaButton;

    @FXML
    private Button logoutButton;

    @FXML
    private void initialize() {
        greenSpacesButton.setOnAction(event -> handleGreenSpaces());
        tasksButton.setOnAction(event -> handleTasks());
        toDoListButton.setOnAction(event -> handleToDoList());
        agendaButton.setOnAction(event -> handleAgenda());
        logoutButton.setOnAction(event -> handleLogout());
    }

    private void handleGreenSpaces() {
        UtilsFX.bottonControl("/fxml/greenSpace/GreenSpacesMenu.fxml", greenSpacesButton, "Green Spaces Menu");
    }

    private void handleTasks() {
        UtilsFX.bottonControl("/fxml/task/TaskMenu.fxml", tasksButton, "Task Menu");
    }

    private void handleToDoList() {
        UtilsFX.bottonControl("/fxml/toDoList/ToDoListMenu.fxml", tasksButton, "To Do List Menu");
    }

    private void handleAgenda() {
        UtilsFX.bottonControl("/fxml/agenda/AgendaMenu.fxml", tasksButton, "Agenda Menu");
    }

    private void handleLogout() {
        UtilsFX.bottonControl("/fxml/LoginView.fxml", logoutButton, "Login Menu");
    }
}
