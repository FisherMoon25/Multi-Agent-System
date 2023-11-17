import java.util.Arrays;
import static java.lang.Boolean.*;

/**
 * Implementation of Conway's Game of Life.
 * In this cellular automaton, cells can either be alive or dead, and their states
 * are updated based on the number of alive neighbors they have.
 */
public class GameOfLife extends AbstractGameOfLife {

    /**
     * Constructs a GameOfLife with specified grid dimensions.
     *
     * @param rows    The number of rows in the game grid.
     * @param columns The number of columns in the game grid.
     */
    public GameOfLife(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * Determines whether a neighboring cell is relevant for the current cell's next state.
     * In Conway's Game of Life, a neighbor is relevant if it is alive.
     *
     * @param originRow       The row index of the original cell.
     * @param originColumn    The column index of the original cell.
     * @param neighborRow     The row index of the neighboring cell.
     * @param neighborColumn  The column index of the neighboring cell.
     * @return true if the neighbor is alive, false otherwise.
     */
    @Override
    public boolean isRelevantNeighbor(int originRow, int originColumn, int neighborRow, int neighborColumn) {
        return grid[neighborRow][neighborColumn].isAlive();
    }

    /**
     * Updates the state of the game grid based on Conway's Game of Life rules.
     * A cell becomes alive if it is dead and has exactly three alive neighbors,
     * and stays alive if it has two or three alive neighbors.
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
                if (!grid[i][j].isAlive()) {
                    if (aliveNeighbors == 3) {
                        newGrid[i][j].setState(1); // Cell becomes alive
                    }
                } else {
                    if (!(aliveNeighbors == 2 || aliveNeighbors == 3)) {
                        newGrid[i][j].setState(0); // Cell dies
                    }
                }
            }
        }

        grid = newGrid;
    }
}
