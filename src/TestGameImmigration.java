import gui.GUISimulator;
import java.awt.Color;

public class TestGameImmigration {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);

        GameImmigrationSimulator simulator = new GameImmigrationSimulator(gui, 100, 100, 4);

        gui.setSimulable(simulator);


        // Add some random cells
        for (int i = 40; i < 50; i++) {
            for (int j = 40; j < 50; j++) {
                int randomState = (int) (Math.random() * 4);
                simulator.getGame().addCell(i, j, randomState);
            }
        }
        simulator.drawGame();

    }
}
