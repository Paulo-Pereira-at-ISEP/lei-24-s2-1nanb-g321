package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {

    private final List<Task> tasks;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
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

        Optional<Task> newTask = Optional.empty();
        boolean operationSuccess = false;

        if (validateTask(task)) {
            newTask = Optional.of(task.clone());
            operationSuccess = tasks.add(newTask.get());
        }

        if (!operationSuccess) {
            newTask = Optional.empty();
        }

        return newTask;

    }
}