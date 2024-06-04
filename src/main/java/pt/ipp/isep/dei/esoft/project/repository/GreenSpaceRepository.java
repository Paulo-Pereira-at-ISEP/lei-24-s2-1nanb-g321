package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpaceRepository {

    private final List<GreenSpace> greenSpaces;

    public GreenSpaceRepository() {
        this.greenSpaces = new ArrayList<>();
    }

    public void addGreenSpace(GreenSpace greenSpace) {
        greenSpaces.add(greenSpace);
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

        Optional<GreenSpace> newGreenSpace = Optional.empty();
        boolean operationSuccess = false;

        if (validateGreenSpace(greenSpace)) {
            newGreenSpace = Optional.of(greenSpace.clone());
            operationSuccess = greenSpaces.add(newGreenSpace.get());
        }

        if (!operationSuccess) {
            newGreenSpace = Optional.empty();
        }

        return newGreenSpace;

    }
}