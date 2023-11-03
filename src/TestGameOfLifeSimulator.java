import gui.GUISimulator ;
import java.awt.Color ;

public class TestGameOfLifeSimulator {
    public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK );
        GameOfLifeSimulator game = new GameOfLifeSimulator(gui, 50, 50);

        game.getGame().addCell(2, 1);
        game.getGame().addCell(3, 2);
        game.getGame().addCell(1, 3);
        game.getGame().addCell(2, 3);
        game.getGame().addCell(3, 3);


        gui.setSimulable(game) ;
    }
}