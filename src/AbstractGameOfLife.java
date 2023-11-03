import static java.lang.Boolean.FALSE;

public abstract class AbstractGameOfLife {

    protected int rows;
    protected int columns;
    protected Cell[][] grid;
    protected Cell[][] initial_grid;

    public AbstractGameOfLife(int rows,int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
        this.initial_grid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(0);
                initial_grid[i][j]= new Cell(0);
            }
        }

    }
    public Cell[][] getGrid() {
        return this.grid;
    }
    public void addCell(int row,int column,int state){
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            grid[row][column].setState(state);
            initial_grid[row][column].setState(state);
        }
    }

    public void reInit() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].setState(initial_grid[i][j].getState());
            }
        }

    }
    public abstract int  getNbAliveNeighbors(int row,int column);
    public  abstract void update();



}
