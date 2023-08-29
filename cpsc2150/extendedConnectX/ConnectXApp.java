package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.controllers.*;
import cpsc2150.extendedConnectX.views.*;

/**
 * This class is the entry point of our program and just loads the set up screen and controller
 *
 * @version 2.0
 */
public class ConnectXApp {

    /**
     * <p>
     * This method is the main entry point into the program.
     * </p>
     *
     * @param args
     *            Command-line arguments (there shouldn't be any).
     */
    public static void main(String[] args) {
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
