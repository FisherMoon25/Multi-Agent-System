

import java.util.Arrays;

import static java.lang.Boolean.*;

public class GameOfLife extends AbstractGameOfLife {


    public GameOfLife(int rows,int columns){
        super(rows,columns);

    }


    public int getNbAliveNeighbors(int row,int column){
        int count =0;
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                if (i!=0 || j!=0){
                    int newRow = (row+i+rows)%this.rows;
                    int newColumn=(column+j+columns)%this.columns;
                    if(grid[newRow][newColumn].isAlive()){
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
                if (!grid[i][j].isAlive()){
                    if (aliveNeighbors==3){
                        newGrid[i][j].setState(1);
                    }
                }
                else{
                    if(!(aliveNeighbors==2 || aliveNeighbors==3)){
                        newGrid[i][j].setState(0);
                    }
                }

            }
        }
        grid = newGrid;
    }




}

