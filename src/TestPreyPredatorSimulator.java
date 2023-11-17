import gui.GUISimulator;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class TestPreyPredatorSimulator {
    public static void main(String[] args){
        GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK ) ;
        Random random=new Random();
        int numPreys= 30;
        int numPredators = 2;
        float cohesionCoeff = 0.09F;
        float alignmentCoeff = 1;
        float seperationCoeff = 1.5F;
        int diameter = 5;
        Point2D.Float[] positionPreys=new Point2D.Float[numPreys];
        Point2D.Float[] velocityPreys=new Point2D.Float[numPreys];
        Point2D.Float[] positionPredators=new Point2D.Float[numPredators];
        Point2D.Float[] velocityPredators=new Point2D.Float[numPredators];

        for(int i=0;i<numPreys;i++){
            positionPreys[i] = new Point2D.Float(random.nextFloat(300,500), random.nextFloat(300,500));
            velocityPreys[i] = new Point2D.Float(random.nextFloat(15), random.nextFloat(10));
        }

        for(int i=0;i<numPredators;i++){
            positionPredators[i] = new Point2D.Float(random.nextFloat(0,300), random.nextFloat(0,500));
            velocityPredators[i] = new Point2D.Float(random.nextFloat(10,15), random.nextFloat(10,15));
        }
        Prey preys = new Prey(positionPreys,velocityPreys,15,3,120,1,diameter,cohesionCoeff,alignmentCoeff,seperationCoeff);
        Predator predators= new Predator(positionPredators,velocityPredators,100,5,120,1,diameter,cohesionCoeff,alignmentCoeff,seperationCoeff);
        PreyPredatorSimulator simul = new PreyPredatorSimulator(preys,predators,gui);
        gui.setSimulable(simul);


    }
}