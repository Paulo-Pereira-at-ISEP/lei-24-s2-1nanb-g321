package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AgendaRepository {



    private final List<Entry> entrys;

        public AgendaRepository() {
            this.entrys = new ArrayList<>();
        }

        public void addEntry(Entry entry) {
            entrys.add(entry);
        }

        public ArrayList<Entry> getAllEntrys() {
            return new ArrayList<>(entrys);
        }
        public List<Entry> getEntriesByDate(LocalDate date) {

                List<Entry> filteredEntries = new ArrayList<>();
                for (Entry entry : entrys) {
                    if (date.equals(entry.getEntryDate())) {
                        filteredEntries.add(entry);
                    }
                }
                return filteredEntries;
            }
    public List<Entry> getEntriesBetweenTwoDates(LocalDate date, LocalDate date2) {
        List<Entry> allEntries = getAllEntrys();
        List<Entry> filteredEntries = new ArrayList<>();

        for (Entry entry : allEntries) {
            if ((entry.getEntryDate().isAfter(date) || entry.getEntryDate().isEqual(date)) &&
                    (entry.getEntryDate().isBefore(date2) || entry.getEntryDate().isEqual(date2))) {
                filteredEntries.add(entry);
            }
        }

        return filteredEntries;
    }
    public void sortEntriesByDate() {
        entrys.sort(Comparator.comparing(Entry::getEntryDate));
    }

        private boolean validateEntry(Entry entry) {
            boolean isValid = !entrys.contains(entry);
            return isValid;
        }


        /**
         * This method returns a defensive (immutable) copy of the list of skills.
         *
         * @return The list of skills.
         */
        public List<Entry> getEntrys() {
            //This is a defensive copy, so that the repository cannot be modified from the outside.
            return List.copyOf(entrys);
        }


        public Optional<Entry> add(Entry entry) {

            Optional<Entry> newEntry = Optional.empty();
            boolean operationSuccess = false;

            if (validateEntry(entry)) {
                newEntry = Optional.of(entry.clone());
                operationSuccess = entrys.add(newEntry.get());
            }

            if (!operationSuccess) {
                newEntry = Optional.empty();
            }

            return newEntry;

        }
    }


