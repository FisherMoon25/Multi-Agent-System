/**
 * Represents a cell with a state.
 * This class is typically used in simulations like cellular automata,
 * where the state of a cell can represent different conditions (e.g., alive or dead).
 */
public class Cell {
    private int state;

    /**
     * Constructs a Cell with the specified initial state.
     *
     * @param state The initial state of the cell.
     */
    public Cell(int state) {
        this.state = state;
    }

    /**
     * Returns the current state of the cell.
     *
     * @return The state of the cell.
     */
    public int getState() {
        return this.state;
    }

    /**
     * Sets the state of the cell.
     *
     * @param state The new state to be set for the cell.
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Determines if the cell is 'alive'.
     * Depending on the context, 'alive' can mean different things but typically
     * it refers to a specific state of the cell (e.g., state 1).
     *
     * @return true if the cell is in the 'alive' state, false otherwise.
     */
    public boolean isAlive() {
        return this.state == 1;
    }
}
