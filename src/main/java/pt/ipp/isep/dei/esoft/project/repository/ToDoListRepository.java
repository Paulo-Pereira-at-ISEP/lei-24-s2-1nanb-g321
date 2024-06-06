package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ToDoListRepository {


        private final List<Entry> entrys;

        public ToDoListRepository() {
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
    public List<Entry> sortEntriesByUrgencyDegree(List<Entry> entryList) {
        List<Entry> modifiableList = new ArrayList<>(entryList);

        // Define a comparator using an anonymous inner class
        modifiableList.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry e1, Entry e2) {
                // Map urgency degrees to numeric values
                int urgency1 = getUrgencyValue(e1.getUrgencyDegree());
                int urgency2 = getUrgencyValue(e2.getUrgencyDegree());
                // Compare numeric values
                return Integer.compare(urgency1, urgency2);
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
        });

        return modifiableList;
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

