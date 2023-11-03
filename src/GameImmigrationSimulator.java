import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class GameImmigrationSimulator implements Simulable {
    private GameImmigration game;
    private GUISimulator gui;
    private int cellSize = 10;

    public  GameImmigrationSimulator(GUISimulator gui,int rows,int columns,int nbOfStates){
        this.game = new GameImmigration(rows,columns,nbOfStates);
        this.gui = gui;
    }


    @Override
    public void next(){
        game.update();
        drawGame();
    }
    @Override
    public void restart(){
        game.reInit();
        drawGame();
    }

    private Color stateToColor(int state, int numberOfStates) {
        // Ensure we don't divide by zero if there is only one state.
        float brightness = numberOfStates > 1 ? (1.0f - (float) state / (numberOfStates - 1)) : 1.0f;
        return new Color(brightness, brightness, brightness); // Grayscale is when all RGB values are the same.
    }
    public void drawGame() {
        gui.reset();
        Cell[][] grid = game.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Color color = stateToColor(grid[i][j].getState(), game.getNbOfStates());
                gui.addGraphicalElement(new Rectangle(j * cellSize, i * cellSize, color, color, cellSize));

            }
        }
    }



    public GameImmigration getGame(){
        return this.game;
    }


}
