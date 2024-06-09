package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GreenSpaceRepository {
    private static final long serialVersionUID = 2454184235920533583L;

    private final List<GreenSpace> greenSpaces;


    public GreenSpaceRepository() {
        List<GreenSpace> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator + "greenspaces.ser");
        if (result == null) {
            this.greenSpaces = new ArrayList<>();
        } else {
            this.greenSpaces = result;
        }
    }

    /**
     * Serializes the list of green spaces.
     */
    public void serialize() {
        Serialize.serialize(greenSpaces, Serialize.FOLDER_PATH + File.separator + "greenspaces.ser");
    }

    /**
     * Adds a green space to the repository.
     *
     * @param greenSpace The green space to add.
     */
    public void addGreenSpace(GreenSpace greenSpace) {
        if (greenSpace == null) {
            throw new NullPointerException("GreenSpace cannot be null");
        }
        if (validateGreenSpace(greenSpace)) {
            greenSpaces.add(greenSpace);
            serialize();
        }
    }

    /**
     * Retrieves all green spaces in the repository.
     *
     * @return A list containing all green spaces.
     */
    public List<GreenSpace> getAllGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }

    /**
     * Validates a green space before adding it to the repository.
     *
     * @param greenSpace The green space to validate.
     * @return True if the green space is valid and can be added, false otherwise.
     */
    private boolean validateGreenSpace(GreenSpace greenSpace) {
        return !greenSpaces.contains(greenSpace);
    }

    /**
     * Retrieves a defensive (immutable) copy of the list of green spaces.
     *
     * @return The list of green spaces.
     */
    public List<GreenSpace> getGreenSpaces() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(greenSpaces);
    }

    /**
     * Retrieves green spaces managed by a user with the specified email.
     *
     * @param email The email of the user.
     * @return A list of green spaces managed by the user.
     */
    public List<GreenSpace> getGreenSpacesManaged(String email) {
        List<GreenSpace> greenSpacesManaged = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getManager().getEmail().equals(email)) {
                greenSpacesManaged.add(greenSpace);
            }
        }
        return greenSpacesManaged;
    }

    /**
     * Adds a green space to the repository and returns an optional containing the added green space.
     *
     * @param greenSpace The green space to add.
     * @return An optional containing the added green space if successful, otherwise an empty optional.
     */
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
