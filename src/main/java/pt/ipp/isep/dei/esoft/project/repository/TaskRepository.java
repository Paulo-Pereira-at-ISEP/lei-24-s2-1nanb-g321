package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private static final long serialVersionUID = -2198104427632195287L; // Skill

    private final List<Task> tasks;

    public TaskRepository() {

        List<Task> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"tasks.ser");
        if(result == null){
            this.tasks = new ArrayList<>();
        }else{
            this.tasks = result;
        }
    }
    public void serialize(){
        Serialize.serialize(tasks,Serialize.FOLDER_PATH + File.separator +"tasks.ser");}

    public void addTask(Task task) {
        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }
        if (validateTask(task)) {
            tasks.add(task);
            serialize();
        }
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    private boolean validateTask(Task task) {
        boolean isValid = !tasks.contains(task);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Task> tasks() {

        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(tasks);
    }



    public Optional<Task> add(Task task) {

        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }

        if (validateTask(task)) {
            Task clonedTask = task.clone();
            if (tasks.add(clonedTask)) {
                serialize();
                return Optional.of(clonedTask);
            }
        }

        return Optional.empty();
    }
}