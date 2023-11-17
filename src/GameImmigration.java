public class GameImmigration extends AbstractGame {
    private final int nbOfStates;

    /**
     * Constructs a GameImmigration with specified grid dimensions and number of states.
     *
     * @param rows       The number of rows in the game grid.
     * @param columns    The number of columns in the game grid.
     * @param nbOfStates The number of different states a cell can have.
     */
    public GameImmigration(int rows, int columns, int nbOfStates) {
        super(rows, columns);
        this.nbOfStates = nbOfStates;
    }

    /**
     * Determines whether a neighboring cell is relevant for the current cell's next state.
     * In GameImmigration, a neighbor is relevant if its state is exactly one more than the current cell's state.
     *
     * @param originRow       The row index of the original cell.
     * @param originColumn    The column index of the original cell.
     * @param neighborRow     The row index of the neighboring cell.
     * @param neighborColumn  The column index of the neighboring cell.
     * @return true if the neighbor is relevant, false otherwise.
     */
    @Override
    public boolean isRelevantNeighbor(int originRow, int originColumn, int neighborRow, int neighborColumn) {
        int neighborState = grid[neighborRow][neighborColumn].getState();
        return grid[originRow][originColumn].getState() + 1 == neighborState;
    }

    /**
     * Updates the state of the game grid based on the GameImmigration rules.
     * A cell changes to the next state if it has three or more neighbors in the next state.
     */
    @Override
    public void update() {
        Cell[][] newGrid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newGrid[i][j] = new Cell(grid[i][j].getState());
            }
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                int aliveNeighbors = getNeighborCount(i, j);
                if (aliveNeighbors >= 3) {
                    newGrid[i][j].setState((newGrid[i][j].getState() + 1) % nbOfStates);
                }
            }
        }
        grid = newGrid;
    }

    /**
     * Gets the number of states available in the GameImmigration.
     *
     * @return The number of states.
     */
    public int getNbOfStates() {
        return this.nbOfStates;
    }
}
