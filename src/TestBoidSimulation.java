import gui.GUISimulator;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class TestBoidSimulation {

    public static void main(String[] args) {

        GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
        Random random = new Random();
        Point2D.Float[] positionPreys=new Point2D.Float[20];
        Point2D.Float[] velocityPreys=new Point2D.Float[20];
        for(int i=0;i<20;i++){
            positionPreys[i] = new Point2D.Float(random.nextFloat(500), random.nextFloat(500));
            velocityPreys[i] = new Point2D.Float(random.nextFloat(5), random.nextFloat(2));
        }

        BoidSimulation simulator = new BoidSimulation(positionPreys,velocityPreys,15,2,120,1,gui, 7);
        //simulator.getFlocks().addBoid(new Boid(new Point2D.Float(100, 110), new Point2D.Float(5, -3), 10));
        //simulator.getFlocks().addBoid(new Boid(new Point2D.Float(400, 100), new Point2D.Float(-2, 10), 10));

        gui.setSimulable(simulator);



    }


}