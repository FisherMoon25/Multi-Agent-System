import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

/**
 * A simulator for the GameImmigration model, implementing the Simulable interface.
 * This class is responsible for visualizing the game state in a GUI and handling
 * the simulation steps.
 */
public class GameImmigrationSimulator implements Simulable {
    private final GameImmigration game;
    private final GUISimulator gui;

    /**
     * Constructs a GameImmigrationSimulator with specified parameters.
     *
     * @param gui        The GUI simulator for visualization.
     * @param rows       The number of rows in the game grid.
     * @param columns    The number of columns in the game grid.
     * @param nbOfStates The number of different states in the game.
     */
    public GameImmigrationSimulator(GUISimulator gui, int rows, int columns, int nbOfStates) {
        this.game = new GameImmigration(rows, columns, nbOfStates);
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
     * Converts a game state to a color for visualization.
     * This method maps different states to distinct colors.
     *
     * @param state          The state to be converted to color.
     * @param numberOfStates The total number of states in the game.
     * @return The color corresponding to the given state.
     */
    private Color stateToColor(int state, int numberOfStates) {
        if (state==0){
            return gui.getGraphics().getColor();
        }
        float hue = (float) state / numberOfStates;
        return Color.getHSBColor(hue, 1.0f, 1.0f);
    }

    /**
     * Draws the current game state on the GUI.
     * This method visualizes each cell of the game grid in the GUI.
     */
    public void drawGame() {
        gui.reset();
        Cell[][] grid = game.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Color color = stateToColor(grid[i][j].getState(), game.getNbOfStates());
                int cellSize = 10;
                gui.addGraphicalElement(new Rectangle(j * cellSize, i * cellSize, Color.DARK_GRAY, color, cellSize));
            }
        }
    }

    /**
     * Gets the current GameImmigration instance being simulated.
     *
     * @return The current GameImmigration instance.
     */
    public GameImmigration getGame() {
        return this.game;
    }
}
