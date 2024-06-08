package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.Utils.Serialize;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AgendaRepository {



    private final List<Entry> entrys;

    public AgendaRepository() {

        List<Entry> result = Serialize.deserialize(Serialize.FOLDER_PATH + File.separator +"AgendaEntrys.ser");
        if(result == null){
            this.entrys = new ArrayList<>();
        }else{
            this.entrys = result;
        }
    }
    public void serialize(){
        Serialize.serialize(entrys,Serialize.FOLDER_PATH + File.separator +"AgendaEntrys.ser");}

        public void addEntry(Entry entry) {
            if (entry == null) {
                throw new NullPointerException("Entry cannot be null");
            }
            if (validateEntry(entry)) {
                entrys.add(entry);
                serialize();
            }
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
    public List<Entry> sortEntriesByDate(List<Entry> entryList) {
        List<Entry> modifiableList = new ArrayList<>(entryList);
        modifiableList.sort(Comparator.comparing(Entry::getEntryDate));
        return modifiableList;
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

            if (entry == null) {
                throw new NullPointerException("Entry cannot be null");
            }

            if (validateEntry(entry)) {
                Entry clonedEntry = entry.clone();
                if (entrys.add(clonedEntry)) {
                    serialize();
                    return Optional.of(clonedEntry);
                }
            }

            return Optional.empty();
        }
    }


