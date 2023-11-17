import java.util.*;

/**
 * Implementation of Schelling's model of segregation.
 * In this model, cells represent agents of different types (colors). Agents move to a new location
 * if the number of similar neighbors is below a certain threshold.
 */
public class SchellingModel extends AbstractGameOfLife {

    private int threshold;
    private int nbOfColors;
    private Set<Cell> vacantHouses;

    /**
     * Constructs a SchellingModel with specified dimensions, number of colors, and threshold.
     *
     * @param rows       The number of rows in the model grid.
     * @param columns    The number of columns in the model grid.
     * @param nbOfColors The number of different colors (states) in the model.
     * @param threshold  The satisfaction threshold for agents.
     */
    public SchellingModel(int rows, int columns, int nbOfColors, int threshold) {
        super(rows, columns);
        this.threshold = threshold;
        this.nbOfColors = nbOfColors;
        this.vacantHouses = new HashSet<>();

        // Initialize the grid with random states, including vacant houses
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean vacant = random.nextDouble() < 0.1; // 10% chance of being vacant
                int state = vacant ? 0 : random.nextInt(nbOfColors) + 1;
                grid[i][j] = new Cell(state);
                initial_grid[i][j] = new Cell(state);
                if (vacant) {
                    vacantHouses.add(grid[i][j]);
                }
            }
        }
    }

    /**
     * Determines whether a neighboring cell is relevant for the current cell's satisfaction.
     * In SchellingModel, a neighbor is relevant if it is not of the same type and not vacant.
     *
     * @param originRow       The row index of the original cell.
     * @param originColumn    The column index of the original cell.
     * @param neighborRow     The row index of the neighboring cell.
     * @param neighborColumn  The column index of the neighboring cell.
     * @return true if the neighbor is relevant, false otherwise.
     */
    @Override
    public boolean isRelevantNeighbor(int originRow, int originColumn, int neighborRow, int neighborColumn) {
        int cellState = grid[originRow][originColumn].getState();
        return grid[neighborRow][neighborColumn].getState() != cellState && grid[neighborRow][neighborColumn].getState() != 0;
    }

    /**
     * Moves an agent from one cell to another.
     *
     * @param from The cell from which the agent moves.
     * @param to   The cell to which the agent moves.
     */
    public void move(Cell from, Cell to) {
        to.setState(from.getState());
        from.setState(0);
        vacantHouses.add(from);
        vacantHouses.remove(to);
    }

    /**
     * Searches for a new house for a dissatisfied agent.
     *
     * @param cell The cell representing the agent looking for a new house.
     * @return A vacant house cell, or null if none are available.
     */
    public Cell searchForNewHouse(Cell cell) {
        if (vacantHouses.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(vacantHouses.size());
        Iterator<Cell> iterator = vacantHouses.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    /**
     * Updates the state of the model based on Schelling's segregation rules.
     * Agents move to a new location if the number of similar neighbors is below the threshold.
     */
    @Override
    public void update() {
        ArrayList<Cell> cellsToMove = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getState() != 0 && getNeighborCount(i, j) > threshold) {
                    cellsToMove.add(grid[i][j]);
                }
            }
        }

        for (Cell cell : cellsToMove) {
            Cell to = searchForNewHouse(cell);
            if (to != null) {
                move(cell, to);
            }
        }
    }

    /**
     * Gets the number of colors (types of agents) in the model.
     *
     * @return The number of colors.
     */
    public int getNbOfColors() {
        return this.nbOfColors;
    }
}
