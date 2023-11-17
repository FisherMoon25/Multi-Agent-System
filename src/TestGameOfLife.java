import gui.GUISimulator;
import java.awt.Color;

public class TestGameOfLife {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
        GameOfLifeSimulator simulator = new GameOfLifeSimulator(gui, 100, 100);
        gui.setSimulable(simulator);

        // Still Life - Block
        simulator.getGame().addCell(10, 10, 1);
        simulator.getGame().addCell(10, 11, 1);
        simulator.getGame().addCell(11, 10, 1);
        simulator.getGame().addCell(11, 11, 1);

        // Oscillator - Blinker
        simulator.getGame().addCell(20, 20, 1);
        simulator.getGame().addCell(20, 21, 1);
        simulator.getGame().addCell(20, 22, 1);

        // Spaceship - Glider
        simulator.getGame().addCell(30, 30, 1);
        simulator.getGame().addCell(31, 31, 1);
        simulator.getGame().addCell(31, 32, 1);
        simulator.getGame().addCell(30, 32, 1);
        simulator.getGame().addCell(29, 32, 1);

        simulator.drawGame();

    }
}
