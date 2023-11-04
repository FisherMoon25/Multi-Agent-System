import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class SchellingModelSimulator implements Simulable {
    private SchelingModel game;
    private GUISimulator gui;
    private int cellSize = 10;
    
    public SchellingModelSimulator(GUISimulator gui,int rows,int columns,int nbOfColors,int K) {
        this.game = new SchelingModel (rows, columns, nbOfColors, K);
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

        float h =  (float) state / numberOfStates;
        return Color.getHSBColor(h, 1.0f, 1.0f);
    }
    public void drawGame() {
        gui.reset();
        Cell[][] grid = game.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Color color = stateToColor(grid[i][j].getState(), game.getNbOfColors());
                gui.addGraphicalElement(new Rectangle(j * cellSize, i * cellSize, color, color, cellSize));

            }
        }
    }
    public SchelingModel getGame(){
        return this.game;
    }


}
