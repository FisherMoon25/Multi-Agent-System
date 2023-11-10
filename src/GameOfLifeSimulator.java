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
                    gui.addGraphicalElement(new Rectangle(j * cellSize, i * cellSize, Color.BLUE, Color.BLUE, cellSize-1));
                }

            }
        }
    }
    private void initializeCoolPatterns() {
        // You can place known patterns like Gliders, Spaceships, Oscillators etc.
        // This is just an example with a glider and a small exploder.
        game.addCell(1, 2, 1); // Middle of the top row of the glider
        game.addCell(2, 3, 1); // Rightmost of the middle row of the glider
        game.addCell(3, 1, 1); // Leftmost of the bottom row of the glider
        game.addCell(3, 2, 1); // Middle of the bottom row of the glider
        game.addCell(3, 3, 1); // Rightmost of the bottom row of the glider

        int exploderStartX = 15;
        int exploderStartY = 15;
        game.addCell(exploderStartX, exploderStartY, 1);
        game.addCell(exploderStartX, exploderStartY + 1, 1);
        game.addCell(exploderStartX, exploderStartY + 2, 1);
        game.addCell(exploderStartX + 1, exploderStartY, 1);
        game.addCell(exploderStartX + 1, exploderStartY + 2, 1);
        game.addCell(exploderStartX + 2, exploderStartY + 1, 1);
    }
    public void addGosperGliderGun(int startX, int startY) {
        int[][] gunPattern = {
                {0, 24},
                {1, 22}, {1, 24},
                {2, 12}, {2, 13}, {2, 20}, {2, 21}, {2, 34}, {2, 35},
                {3, 11}, {3, 15}, {3, 20}, {3, 21}, {3, 34}, {3, 35},
                {4, 0}, {4, 1}, {4, 10}, {4, 16}, {4, 20}, {4, 21},
                {5, 0}, {5, 1}, {5, 10}, {5, 14}, {5, 16}, {5, 17}, {5, 22}, {5, 24},
                {6, 10}, {6, 16}, {6, 24},
                {7, 11}, {7, 15},
                {8, 12}, {8, 13}
        };

        for (int[] cell : gunPattern) {
            game.addCell(startX + cell[0], startY + cell[1], 1);
        }
    }
    public GameOfLife getGame(){
        return this.game;
    }


}
