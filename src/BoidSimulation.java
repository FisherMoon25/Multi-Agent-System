import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;
import java.awt.geom.Point2D;

public class BoidSimulation implements Simulable {


    private Boid flocks;
    private GUISimulator gui;

    private int diameter;


    public BoidSimulation(Point2D.Float[] position,Point2D.Float[] velocity, GUISimulator gui, int diameter, int n) {
        this.flocks = new Boid(position,velocity,5,2,120,1);
        this.gui = gui;
        this.diameter = diameter;

    }


    public void next() {
        flocks.update(gui.getWidth(),gui.getHeight());
        this.draw();
    }

    public void restart() {

    }

    public void draw() {
        gui.reset();
        int numPreys= this.flocks.getPosition().length;
        for (int i = 0;i<numPreys;i++){
            int x =(int)this.flocks.getPosition()[i].x;
            int y =(int)this.flocks.getPosition()[i].y;
            gui.addGraphicalElement(new Oval(x,y, Color.BLUE,Color.BLUE,this.flocks.getDiam()));
        }
    }


}