package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class AgendaRepository {

    private final List<Entry> entries;


    public AgendaRepository() {
        List<Entry> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"AgendaEntries.ser");
        if(result == null){
            this.entries = new ArrayList<>();
        } else {
            this.entries = result;
        }
    }

    /**
     * Serializes the list of entries.
     */
    public void serialize() {
        Serialize.serialize(entries, Serialize.FOLDER_PATH + File.separator +"AgendaEntries.ser");
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
     * Retrieves entries by a specific date.
     *
     * @param date The date to filter entries by.
     * @return A list of entries with the specified date.
     */
    public List<Entry> getEntriesByDate(LocalDate date) {
        List<Entry> filteredEntries = new ArrayList<>();
        for (Entry entry : entries) {
            if (date.equals(entry.getEntryDate())) {
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }

    /**
     * Retrieves entries within a date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of entries within the specified date range.
     */
    public List<Entry> getEntriesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        List<Entry> filteredEntries = new ArrayList<>();
        for (Entry entry : entries) {
            LocalDate entryDate = entry.getEntryDate();
            if ((entryDate.isAfter(startDate) || entryDate.isEqual(startDate)) &&
                    (entryDate.isBefore(endDate) || entryDate.isEqual(endDate))) {
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }

    /**
     * Sorts a list of entries by date.
     *
     * @param entryList The list of entries to sort.
     * @return The sorted list of entries.
     */
    public List<Entry> sortEntriesByDate(List<Entry> entryList) {
        List<Entry> modifiableList = new ArrayList<>(entryList);
        modifiableList.sort(Comparator.comparing(Entry::getEntryDate));
        return modifiableList;
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
     * Retrieves a defensive (immutable) copy of the list of entries.
     *
     * @return The list of entries.
     */
    public List<Entry> getEntries() {
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
        if (validateEntry(entry)) {
            Entry clonedEntry = entry.clone();
            if (entries.add(clonedEntry)) {
                serialize();
                return Optional.of(clonedEntry);
            }
        }
        return Optional.empty();
    }
}
