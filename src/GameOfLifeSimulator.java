import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

/**
 * A simulator for Conway's Game of Life, implementing the Simulable interface.
 * This class manages the visualization of the game state in a GUI and handles
 * simulation steps and pattern initialization.
 */
public class GameOfLifeSimulator implements Simulable {
    private final GameOfLife game;
    private final GUISimulator gui;


    /**
     * Constructs a GameOfLifeSimulator with specified GUI dimensions.
     *
     * @param gui     The GUI simulator for visualization.
     * @param rows    The number of rows in the game grid.
     * @param columns The number of columns in the game grid.
     */
    public GameOfLifeSimulator(GUISimulator gui, int rows, int columns) {
        this.game = new GameOfLife(rows, columns);
        this.gui = gui;
    }

    /**
     * Advances the simulation to the next step.
     * Updates the game state and redraws the game in the GUI.
     */
    @Override
    public void next() {
        game.update();
        drawGame();
    }

    /**
     * Restarts the simulation to its initial state.
     * Reinitializes the game and redraws the initial state in the GUI.
     */
    @Override
    public void restart() {
        game.reInit();
        drawGame();
    }

    /**
     * Draws the current game state on the GUI.
     * Alive cells are represented as filled rectangles.
     */
    public void drawGame() {
        int cellSize = 10;
        gui.reset();
        Cell[][] grid = game.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isAlive()) {
                    gui.addGraphicalElement(new Rectangle(j * cellSize, i * cellSize, Color.BLACK, Color.BLUE, cellSize ));
                }
            }
        }
    }

    /**
     * Gets the current GameOfLife instance being simulated.
     *
     * @return The current GameOfLife instance.
     */
    public GameOfLife getGame() {
        return this.game;
    }
}
