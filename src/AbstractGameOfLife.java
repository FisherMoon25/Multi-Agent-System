import static java.lang.Boolean.FALSE;

/**
 * Abstract base class for different variations of Conway's Game of Life.
 * This class manages the game grid and provides basic functionalities such as initialization,
 * adding cells, and counting neighbors. Specific game logic is to be implemented in subclasses.
 */
public abstract class AbstractGameOfLife {

    protected int rows;
    protected int columns;
    protected Cell[][] grid;
    protected Cell[][] initial_grid;

    /**
     * Constructs an abstract Game of Life with the specified grid dimensions.
     *
     * @param rows    The number of rows in the game grid.
     * @param columns The number of columns in the game grid.
     */
    public AbstractGameOfLife(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
        this.initial_grid = new Cell[rows][columns];

        // Initialize the grid with dead cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(0);
                initial_grid[i][j] = new Cell(0);
            }
        }
    }

    /**
     * Returns the current game grid.
     *
     * @return The current state of the game grid.
     */
    public Cell[][] getGrid() {
        return this.grid;
    }

    /**
     * Adds a cell with a specified state at the given row and column.
     *
     * @param row   The row index of the cell.
     * @param column The column index of the cell.
     * @param state  The initial state of the cell.
     */
    public void addCell(int row, int column, int state) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            grid[row][column].setState(state);
            initial_grid[row][column].setState(state);
        }
    }

    /**
     * Reinitializes the game grid to its initial state.
     */
    public void reInit() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].setState(initial_grid[i][j].getState());
            }
        }
    }

    /**
     * Counts the relevant neighbors of a cell at a specified position.
     * The definition of a relevant neighbor is determined by subclasses.
     *
     * @param row    The row index of the cell.
     * @param column The column index of the cell.
     * @return The count of relevant neighbors.
     */
    public int getNeighborCount(int row, int column) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    int newRow = (row + i + this.rows) % this.rows;
                    int newColumn = (column + j + this.columns) % this.columns;
                    if (isRelevantNeighbor(row, column, newRow, newColumn)) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Determines whether a neighboring cell is relevant for the current cell's next state.
     * This method should be implemented by subclasses to define specific rules.
     *
     * @param originRow       The row index of the original cell.
     * @param originColumn    The column index of the original cell.
     * @param neighborRow     The row index of the neighboring cell.
     * @param neighborColumn  The column index of the neighboring cell.
     * @return true if the neighbor is relevant, false otherwise.
     */
    public abstract boolean isRelevantNeighbor(int originRow, int originColumn, int neighborRow, int neighborColumn);

    /**
     * Updates the state of the game grid.
     * This method should be implemented by subclasses to define specific game logic.
     */
    public abstract void update();
}
