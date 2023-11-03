import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class GameOfLifeSimulator implements Simulable {
    private GameOfLife game;
    private GUISimulator gui;
    private int cellSize = 10;

    public  GameOfLifeSimulator(GUISimulator gui,int rows,int columns){
        this.game = new GameOfLife(rows,columns);
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

    public void drawGame(){
        gui.reset();
        Cell[][] grid =game.getGrid();
        for (int i=0; i<grid.length;i++){
            for (int j=0; j<grid[i].length;j++){
                if(grid[i][j].isAlive()){
                    gui.addGraphicalElement(new Rectangle(j*cellSize,i*cellSize, Color.BLUE,Color.BLUE,cellSize));
                }

            }
        }
    }
    public GameOfLife getGame(){
        return this.game;
    }


}
