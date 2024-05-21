package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerRepository {

    private List<Manager> managers;

    public ManagerRepository() {
        this.managers = new ArrayList<>();
    }

    public void addManager(Manager manager) {
        managers.add(manager);
    }

    public List<Manager> getAllManagers() {
        return new ArrayList<>(managers);
    }

    private boolean validateManager(Manager manager) {
        boolean isValid = !managers.contains(manager);
        return isValid;
    }
    
    public List<Manager> getManagers() {
        ArrayList<Manager> copy = new ArrayList<>();
        for (Manager manager : managers) {
            copy.add(manager.clone());
        }
        return copy;
    }
    
    public Manager getManagerByEmail(String email) {

        for (Manager manager : managers) {
            if (manager.getEmail().equals(email)){
                return manager;
            }
        }
        return null;
    }
    
    public Optional<Manager> add(Manager manager) {

        Optional<Manager> newManager = Optional.empty(); // Initialize an empty Optional
        boolean operationSuccess = false;

        // Validate the employee
        if (validateManager(manager)) {
            // If validation succeeds, create a clone of the employee and attempt to add it to the list
            newManager = Optional.of(manager.clone());
            operationSuccess = managers.add(newManager.get());
        }

        // If the operation was not successful, return an empty Optional
        if (!operationSuccess) {
            newManager = Optional.empty();
        }

        return newManager; // Return the Optional containing the added employee or an empty Optional
    }


}