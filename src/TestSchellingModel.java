import java.awt.Color;
import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

public class TestSchellingModel {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);

        int rows = 100;
        int columns = 100;
        int numStates = 10; // including empty
        int K = 5; // the threshold of unlike neighbors that will trigger a move

        // Initialize the Schelling simulation with a GUI and parameters.
        SchellingModelSimulator simulator = new SchellingModelSimulator(gui, rows, columns, numStates, K);

        SchellingModel game = simulator.getGame();
        gui.setSimulable(simulator);


    }
}