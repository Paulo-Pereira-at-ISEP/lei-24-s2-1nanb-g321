package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository {



    private final List<AgendaEntry> entrys;

        public AgendaRepository() {
            this.entrys = new ArrayList<>();
        }

        public void addEntry(AgendaEntry entry) {
            entrys.add(entry);
        }

        public ArrayList<AgendaEntry> getAllEntrys() {
            return new ArrayList<>(entrys);
        }

        private boolean validateEntry(AgendaEntry entry) {
            boolean isValid = !entrys.contains(entry);
            return isValid;
        }

        /**
         * This method returns a defensive (immutable) copy of the list of skills.
         *
         * @return The list of skills.
         */
        public List<AgendaEntry> getEntrys() {
            //This is a defensive copy, so that the repository cannot be modified from the outside.
            return List.copyOf(entrys);
        }


        public Optional<AgendaEntry> add(AgendaEntry entry) {

            Optional<AgendaEntry> newEntry = Optional.empty();
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


