package pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaces;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GreenSpacesUI implements Runnable{

    public GreenSpacesUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Green Space", new CreateGreenSpacesUI()));
        options.add(new MenuItem("List Green Spaces", new CreateGreenSpacesUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Green Spaces MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

