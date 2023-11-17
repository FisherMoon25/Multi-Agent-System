import java.awt.Color;
import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

/**
 * TestSchellingModel class is used to demonstrate a simulation of the Schelling segregation model.
 */
public class TestSchellingModel {
    public static void main(String[] args) {
        // Create a new GUI window with specified dimensions and background color
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);

        // Set the dimensions for the grid (rows and columns)
        int rows = 100;
        int columns = 100;

        // Define the number of different states (including the empty state)
        int numStates = 10;

        // Define the tolerance threshold (K) - the number of  neighbors that trigger a move
        int K = 5;


        SchellingModelSimulator simulator = new SchellingModelSimulator(gui, rows, columns, numStates, K);


        gui.setSimulable(simulator);
    }
}
