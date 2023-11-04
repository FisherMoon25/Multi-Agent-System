import java.awt.Color;
import gui.GUISimulator;


public class TestGameImmigration {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);

        GameImmigrationSimulator simulator = new GameImmigrationSimulator(gui, 50 , 50,4);
        GameImmigration game = simulator.getGame();
        game.addCell(2, 1, 1);
        game.addCell(3, 2, 1);
        game.addCell(1, 3, 1);
        game.addCell(2, 3, 1);
        game.addCell(3, 3, 1);


        gui.setSimulable(simulator);
    }
}


