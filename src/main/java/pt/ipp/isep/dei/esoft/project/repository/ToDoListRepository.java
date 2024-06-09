package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class ToDoListRepository {

    private final List<Entry> entries;


    public ToDoListRepository() {
        List<Entry> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator + "entries.ser");
        if (result == null) {
            this.entries = new ArrayList<>();
        } else {
            this.entries = result;
        }
    }

    /**
     * Serializes the list of entries.
     */
    public void serialize() {
        Serialize.serialize(entries, Serialize.FOLDER_PATH + File.separator + "entries.ser");
    }

    /**
     * Adds an entry to the repository.
     *
     * @param entry The entry to add.
     */
    public void addEntry(Entry entry) {
        if (entry == null) {
            throw new NullPointerException("Entry cannot be null");
        }
        if (validateEntry(entry)) {
            entries.add(entry);
            serialize();
        }
    }

    /**
     * Retrieves all entries in the repository.
     *
     * @return A list containing all entries.
     */
    public ArrayList<Entry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    /**
     * Validates an entry before adding it to the repository.
     *
     * @param entry The entry to validate.
     * @return True if the entry is valid and can be added, false otherwise.
     */
    private boolean validateEntry(Entry entry) {
        return !entries.contains(entry);
    }

    /**
     * Sorts entries by urgency degree.
     *
     * @param entryList The list of entries to sort.
     * @return A sorted list of entries.
     */
    public List<Entry> sortEntriesByUrgencyDegree(List<Entry> entryList) {
        List<Entry> modifiableList = new ArrayList<>(entryList);

        // Define a comparator using a lambda expression
        modifiableList.sort(Comparator.comparingInt(e -> getUrgencyValue(e.getUrgencyDegree())));

        return modifiableList;
    }

    private int getUrgencyValue(String urgencyDegree) {
        switch (urgencyDegree) {
            case "High":
                return 1;
            case "Medium":
                return 2;
            case "Low":
                return 3;
            default:
                throw new IllegalArgumentException("Unknown urgency degree: " + urgencyDegree);
        }
    }

    /**
     * Retrieves a defensive (immutable) copy of the list of entries.
     *
     * @return The list of entries.
     */
    public List<Entry> getEntries() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(entries);
    }

    /**
     * Adds an entry to the repository and returns an optional containing the added entry.
     *
     * @param entry The entry to add.
     * @return An optional containing the added entry if successful, otherwise an empty optional.
     */
    public Optional<Entry> add(Entry entry) {
        if (entry == null) {
            throw new NullPointerException("Entry cannot be null");
        }
        Optional<Entry> newEntry = Optional.empty();
        boolean operationSuccess = false;

        if (validateEntry(entry)) {
            newEntry = Optional.of(entry.clone());
            operationSuccess = entries.add(newEntry.get());
        }

        if (!operationSuccess) {
            newEntry = Optional.empty();
        }
        serialize();
        return newEntry;
    }
}
