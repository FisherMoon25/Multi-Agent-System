import gui.GUISimulator;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class TestPreyPredatorSimulator {
    public static void main(String[] args){
        GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK ) ;
        Random random=new Random();
        int numPreys= 20;
        int numPredators = 5;
        Point2D.Float[] positionPreys=new Point2D.Float[numPreys];
        Point2D.Float[] velocityPreys=new Point2D.Float[numPreys];
        Point2D.Float[] positionPredators=new Point2D.Float[numPredators];
        Point2D.Float[] velocityPredators=new Point2D.Float[numPredators];

        for(int i=0;i<numPreys;i++){
            positionPreys[i] = new Point2D.Float(random.nextInt(500), random.nextInt(500));
            velocityPreys[i] = new Point2D.Float(random.nextInt(15), random.nextInt(10));
        }

        for(int i=0;i<numPredators;i++){
            positionPredators[i] = new Point2D.Float(random.nextInt(500), random.nextInt(500));
            velocityPredators[i] = new Point2D.Float(random.nextInt(15), random.nextInt(10));
        }
        Prey preys = new Prey(positionPreys,velocityPreys,15,10,120,1);
        Predator predators= new Predator(positionPredators,velocityPredators,15,10,120,2);
        PreyPredatorSimulator simul = new PreyPredatorSimulator(preys,predators,gui);
        gui.setSimulable(simul);


    }
}
