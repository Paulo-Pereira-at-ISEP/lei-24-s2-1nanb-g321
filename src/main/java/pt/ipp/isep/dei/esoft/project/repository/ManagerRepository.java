package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerRepository {

    private List<Manager> managers;

    public ManagerRepository() {

        List<Manager> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"managers.ser");
        if(result == null){
            this.managers= new ArrayList<>();
        }else{
            this.managers = result;
        }
    }
    public void serialize(){
        Serialize.serialize(managers,Serialize.FOLDER_PATH + File.separator +"managers.ser");}

    public void addManager(Manager manager) {
        if (manager == null) {
            throw new NullPointerException("Manager cannot be null");
        }
        if (validateManager(manager)) {
            managers.add(manager);
            serialize();
        }
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
    
    public List<Manager> getManagerByRole(String role) {
        List<Manager> managersWithRole = new ArrayList<>();
        for (Manager manager : managers) {
            if (manager.getRole().equals(role)){
                managersWithRole.add(manager);
            }
        }
        return managersWithRole;
    }
    
    public Optional<Manager> add(Manager manager) {

        if (manager == null) {
            throw new NullPointerException("Manager cannot be null");
        }

        Optional<Manager> newManager = Optional.empty();
        boolean operationSuccess = false;

        if (validateManager(manager)) {
            newManager = Optional.of(manager.clone());
            operationSuccess = managers.add(newManager.get());
        }

        if (!operationSuccess) {
            newManager = Optional.empty();
        }

        serialize();
        return newManager;
    }


}