import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

/**
 * A simulator for the Schelling segregation model, implementing the Simulable interface.
 * This class manages the visualization of the model state in a GUI and handles
 * simulation steps.
 */
public class SchellingModelSimulator implements Simulable {
    private final SchellingModel game;
    private final GUISimulator gui;

    /**
     * Constructs a SchellingModelSimulator with specified parameters.
     *
     * @param gui         The GUI simulator for visualization.
     * @param rows        The number of rows in the model grid.
     * @param columns     The number of columns in the model grid.
     * @param nbOfColors  The number of distinct colors (states) in the model.
     * @param K           The tolerance threshold in the Schelling model.
     */
    public SchellingModelSimulator(GUISimulator gui, int rows, int columns, int nbOfColors, int K) {
        this.game = new SchellingModel(rows, columns, nbOfColors, K);
        this.gui = gui;
        this.drawGame();
    }

    /**
     * Advances the simulation to the next step.
     * Updates the model state and redraws the game in the GUI.
     */
    @Override
    public void next() {
        game.update();
        drawGame();
    }

    /**
     * Restarts the simulation to its initial state.
     * Reinitializes the model and redraws the initial state in the GUI.
     */
    @Override
    public void restart() {
        game.reInit();
        drawGame();
    }

    /**
     * Converts a model state to a color for visualization.
     * This method maps different states to distinct colors.
     *
     * @param state          The state to be converted to color.
     * @param numberOfStates The total number of states (colors) in the model.
     * @return The color corresponding to the given state.
     */
    private Color stateToColor(int state, int numberOfStates) {
        if (state ==0) {
            return gui.getGraphics().getColor();
        }
        float h = (float) state / numberOfStates;
        return Color.getHSBColor(h, 1.0f, 1.0f);
    }

    /**
     * Draws the current model state on the GUI.
     * This method visualizes each cell of the model grid in the GUI.
     */
    public void drawGame() {
        gui.reset();
        Cell[][] grid = game.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Color color = stateToColor(grid[i][j].getState(), game.getNbOfColors());
                int cellSize = 10;
                gui.addGraphicalElement(new Rectangle(j * cellSize, i * cellSize, Color.DARK_GRAY, color, cellSize));
            }
        }
    }

    /**
     * Gets the current SchelingModel instance being simulated.
     *
     * @return The current SchelingModel instance.
     */
    public SchellingModel getGame() {
        return this.game;
    }
}
