package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository  implements Serializable {
     // Skill


    private final List<Skill> skills;

    public SkillRepository() {

        List<Skill> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"skills.ser");
        if(result == null){
            this.skills = new ArrayList<>();
        }else{
            this.skills = result;
        }
    }
    public void serialize(){
        Serialize.serialize(skills,Serialize.FOLDER_PATH + File.separator +"skills.ser");}



    public void addSkill(Skill skill) {
        if (skill == null) {
            throw new NullPointerException("Skill cannot be null");
        }
        if (validateSkill(skill)) {
            skills.add(skill);
            serialize();
        }
    }

    public ArrayList<Skill> getAllSkills() {
        return new ArrayList<>(skills);
    }

    private boolean validateSkill(Skill skill) {
        boolean isValid = !skills.contains(skill);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Skill> getSkills() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }


    /**
     * Attempts to add a new `Skill` object to the internal collection of skills.
     *
     * @param skill The `Skill` object to be added.
     * @return An `Optional` of `Skill`. If the operation is successful, the `Optional`
     *         contains the added `Skill` object. Otherwise, it is empty.
     *
     * @throws NullPointerException if the `skill` parameter is null.
     *
     * @implNote This method first validates the provided `skill` object using the `validateSkill` method
     *          (assumed to be available). If validation is successful, it creates a copy of the `skill`
     *          using the `clone` method and wraps it in an `Optional.of` object. Then, it attempts to add
     *          the `Optional` object (containing the cloned skill) to the internal collection (`skills`).
     *          If the addition is successful (returns `true`), the `Optional` with the added skill is returned.
     *          Otherwise, an empty `Optional` is returned.
     */
    public Optional<Skill> add(Skill skill) {

        if (skill == null) {
            throw new NullPointerException("Skill cannot be null");
        }

        if (validateSkill(skill)) {
            Skill clonedSkill = skill.clone();
            if (skills.add(clonedSkill)) {
                serialize();
                return Optional.of(clonedSkill);
            }
        }

        return Optional.empty();
    }
}