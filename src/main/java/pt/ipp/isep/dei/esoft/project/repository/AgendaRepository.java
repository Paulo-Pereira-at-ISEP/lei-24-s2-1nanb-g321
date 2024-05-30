package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.ArrayList;
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


