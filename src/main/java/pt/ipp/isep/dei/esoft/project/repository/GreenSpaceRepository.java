package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpaceRepository {
    private static final long serialVersionUID = 2454184235920533583L;


    private final List<GreenSpace> greenSpaces;

    public GreenSpaceRepository() {

        List<GreenSpace> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"greenspaces.ser");
        if(result == null){
            this.greenSpaces = new ArrayList<>();
        }else{
            this.greenSpaces = result;
        }
    }
    public void serialize(){
        Serialize.serialize(greenSpaces,Serialize.FOLDER_PATH + File.separator +"greenspaces.ser");}

    public void addGreenSpace(GreenSpace greenSpace) {
        if (greenSpace == null) {
            throw new NullPointerException("GreenSpace cannot be null");
        }
        if (validateGreenSpace(greenSpace)) {
            greenSpaces.add(greenSpace);
            serialize();
        }
    }

    public List<GreenSpace> getAllGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }

    private boolean validateGreenSpace(GreenSpace greenSpace) {
        boolean isValid = !greenSpaces.contains(greenSpace);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<GreenSpace> getGreenSpaces() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(greenSpaces);
    }

    public List<GreenSpace> getGreenSpacesManaged(String email) {
        List<GreenSpace> greenSpacesManaged = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getManager().getEmail().equals(email) ) {
                greenSpacesManaged.add(greenSpace);
            }
        }
        return greenSpacesManaged;
    }

    public Optional<GreenSpace> add(GreenSpace greenSpace) {

        if (greenSpace == null) {
            throw new NullPointerException("GreenSpace cannot be null");
        }

        if (validateGreenSpace(greenSpace)) {
            GreenSpace clonedGreenSpace = greenSpace.clone();
            if (greenSpaces.add(clonedGreenSpace)) {
                serialize();
                return Optional.of(clonedGreenSpace);
            }
        }

        return Optional.empty();
    }
}