import gui.GUISimulator;
import java.awt.Color;

/**
 * TestGameOfLife class sets up and runs a simulation of Conway's Game of Life.
 */
public class TestGameOfLife {
    public static void main(String[] args) {
        // Create a new GUI window with specified dimensions and background color
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);

        // Initialize the Game of Life simulator with the GUI and grid dimensions
        GameOfLifeSimulator simulator = new GameOfLifeSimulator(gui, 100, 100);

        // Set the simulator as the simulable object in the GUI
        gui.setSimulable(simulator);

        // Create a still life pattern (block) at specified coordinates
        simulator.getGame().addCell(10, 10, 1);
        simulator.getGame().addCell(10, 11, 1);
        simulator.getGame().addCell(11, 10, 1);
        simulator.getGame().addCell(11, 11, 1);

        // Create an oscillator pattern (blinker) at specified coordinates
        simulator.getGame().addCell(20, 20, 1);
        simulator.getGame().addCell(20, 21, 1);
        simulator.getGame().addCell(20, 22, 1);

        // Create a spaceship pattern (glider) at specified coordinates
        simulator.getGame().addCell(30, 30, 1);
        simulator.getGame().addCell(31, 31, 1);
        simulator.getGame().addCell(31, 32, 1);
        simulator.getGame().addCell(30, 32, 1);
        simulator.getGame().addCell(29, 32, 1);

        simulator.drawGame();
    }
}
