public class GameImmigration extends AbstractGameOfLife {
    private int nbOfStates;

    public GameImmigration(int rows,int columns,int nbOfStates){
        super(rows,columns);
        this.nbOfStates= nbOfStates;
    }

    @Override
    public int getNbAliveNeighbors(int row, int column) {
        int count = 0;
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                if (i!=0 || j!=0){
                    int newRow = (row+i+rows)%this.rows;
                    int newColumn=(column+j+columns)%this.columns;
                    int neighborState = grid[newRow][newColumn].getState();
                    if(grid[row][column].getState()+1==neighborState){
                        count+=1;
                    }

                }

            }
        }
        return count;
    }
    public void update(){
        Cell[][] newGrid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newGrid[i][j] = new Cell(grid[i][j].getState());
            }
        }
        for (int i=0;i<this.rows;i++){
            for (int j=0;j<this.columns;j++){
                int aliveNeighbors = getNbAliveNeighbors(i,j);
                if (aliveNeighbors>=3){
                        newGrid[i][j].setState((newGrid[i][j].getState()+1)%nbOfStates);

                }
            }
        }
        grid = newGrid;
    }
    public int getNbOfStates(){
        return this.nbOfStates;
    }
}

