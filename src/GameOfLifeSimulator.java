import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class GameOfLifeSimulator implements Simulable {
    private GameOfLife game;
    private GUISimulator gui;
    private int cellSize = 10;

    public  GameOfLifeSimulator(GUISimulator gui,int rows,int columns){
        this.game = new GameOfLife(rows,columns);
        this.gui = gui;
    }


    @Override
    public void next(){
        game.update();
        drawGame();
    }
    @Override
    public void restart(){
        game.reInit();
        drawGame();
    }

    public void drawGame(){
        gui.reset();
        for (Cell cell: this.game.getActive()){
                System.out.println("y : "+cell.getY()*cellSize+" x : "+cell.getX()*cellSize);
                gui.addGraphicalElement(new Rectangle(cell.getY()*cellSize,cell.getX()*cellSize, Color.BLUE,Color.BLUE,cellSize-2));
        }
    }
    public GameOfLife getGame(){
        return this.game;
    }


}