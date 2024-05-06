package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.CreateVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class VFMUI implements Runnable {
    public VFMUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Vehicle", new CreateVehicleUI()));
        options.add(new MenuItem("Register Maintenance of Vehicle", new ShowTextUI("You have chosen to register a vehicle maintenance.")));
        options.add(new MenuItem("List Vehicle who needs Maintenance", new ShowTextUI("You have chosen to list vehicles who needs maintenance.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- VFM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}