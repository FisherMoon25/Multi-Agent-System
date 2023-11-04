import java.util.*;

public class SchelingModel {
    protected int rows;
    protected int columns;
    protected Cell[][] grid;
    protected Cell[][] initial_grid;

    private int threshold;

    private int  nbOfColors;
    private Set<Cell> vacantHouses;

    public SchelingModel(int rows,int columns,int nbOfColors,int  threshold){
        this.rows = rows;
        this.columns = columns;
        this.grid= new Cell[rows][columns];
        this.initial_grid=new Cell[rows][columns];
        this.threshold = threshold;
        this.nbOfColors = nbOfColors;
        this.vacantHouses = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Random random = new Random();
                boolean vacant = random.nextDouble() <0.1; // 10% vacants
                int state = vacant? 0 : random.nextInt(nbOfColors)+1;
                grid[i][j] = new Cell(state);
                initial_grid[i][j]= new Cell(state);
                if (vacant){
                    vacantHouses.add(grid[i][j]);
                }
            }
        }

    }
    public boolean shouldMove(int row ,int column){
        int count = 0;
        int cellState = grid[row][column].getState();
        for (int i=-1;i<=1;i++){
            for (int j =-1;j<=1;j++){
                if (i!=0 || j!=0){
                    int newRow = (row+i+this.rows)%this.rows;
                    int newColumn=(column+j+this.columns)%this.columns;
                    if(grid[newRow][newColumn].getState()!=cellState && grid[newRow][newColumn].getState()!=0){
                        count+=1;
                    }

                }
            }
        }
        return count>threshold;
    }

    public void  move(Cell from ,Cell to){
        to.setState(from.getState());
        from.setState(0);
        vacantHouses.add(from);
        vacantHouses.remove(to);

    }
    public Cell searchForNewHouse(Cell cell){
        if (vacantHouses.isEmpty()){
            return null;
        }
        Random random = new Random();
        int index= random.nextInt(vacantHouses.size());
        Iterator<Cell> iterator = vacantHouses.iterator();
        for (int i = 0 ; i<index;i++){
            iterator.next();
        }
        return iterator.next();
    }
    public void update(){
        // search all cells that should move
        ArrayList<Cell> cellsToMove = new ArrayList<>();
        for (int i =0 ; i< grid.length;i++){
            for (int j = 0 ; j<grid[i].length;j++){
                if (grid[i][j].getState()!=0 && shouldMove(i,j)){
                    cellsToMove.add(grid[i][j]);
                }
            }
        }
        // move all cells now
        for (Cell cell :cellsToMove){
            Cell to= searchForNewHouse(cell);
            if (to!=null){
                move(cell,to);
            }
        }

    }
    public void reInit() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].setState(initial_grid[i][j].getState());
            }
        }

    }
    public Cell[][] getGrid() {
        return this.grid;
    }
    public  int getNbOfColors(){return this.nbOfColors;}

    public void addCell(int row,int column,int state){
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            grid[row][column].setState(state);
            initial_grid[row][column].setState(state);
        }
    }



}
