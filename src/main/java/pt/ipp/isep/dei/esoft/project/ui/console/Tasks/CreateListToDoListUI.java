package pt.ipp.isep.dei.esoft.project.ui.console.Tasks;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.List;

public class CreateListToDoListUI implements Runnable{




        private final CreateEntryController entryController;

        public CreateListToDoListUI() {
            entryController = new CreateEntryController();
        }

        public void run() {
            System.out.println("\n\n--- To Do List ------------------------");
            listToDoList();
        }

        /**
         * Lists all registered skills along with their details.
         */
        private void listToDoList() {
            List<Entry> entrys = entryController.getAllEntrys();

            System.out.println("To Do List:");
            int counter = 1;
            for (Entry entry : entrys) {

                System.out.println("[" + counter + "]   GreenSpace: " + entry.getGreenSpace());
                System.out.println("      Manager: " + entry.getGreenSpace().getManager().getName());
                System.out.println("      Title: " + entry.getName());
                System.out.println("      Description: " + entry.getDescription());
                System.out.println("      Urgency Degree: " + entry.getUrgencyDegree());
                System.out.println("-------------------------");
                counter++;
            }
        }

    }
