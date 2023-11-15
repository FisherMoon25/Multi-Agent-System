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
            positionPreys[i] = new Point2D.Float(random.nextInt(500), random.nextInt(500));
            velocityPreys[i] = new Point2D.Float(random.nextInt(15), random.nextInt(10));
        }
        BoidSimulation simulator = new BoidSimulation(positionPreys,velocityPreys,gui, 7, 50);
        //simulator.getFlocks().addBoid(new Boid(new Point2D.Float(100, 110), new Point2D.Float(5, -3), 10));
        //simulator.getFlocks().addBoid(new Boid(new Point2D.Float(400, 100), new Point2D.Float(-2, 10), 10));

        gui.setSimulable(simulator);



    }


}