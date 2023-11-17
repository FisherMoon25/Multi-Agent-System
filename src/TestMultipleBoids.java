import gui.GUISimulator;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TestMultipleBoids class to demonstrate a simulation of multiple groups of boids.
 */
public class TestMultipleBoids {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(800, 600, Color.WHITE);
        int diameter = 5;
        float coeffCohesion = 0.09F;
        float coeffAlignment = 1;
        float coeffSeperation = 1.5F;
        Random rand = new Random();
        int numTypes =3;

        // Parameters for Group 1
        int numBoidsGroup1 = 20;
        int numBoidsGroup2 = 15;
        int numBoidsGroup3 = 10;
        int totalLength = numBoidsGroup3+numBoidsGroup2+numBoidsGroup1;
        Point2D.Float[] positionsGroup = new Point2D.Float[totalLength];
        Point2D.Float[] velocitiesGroup = new Point2D.Float[totalLength];
        List<Integer> type = new ArrayList<>(totalLength);

        for (int i = 0; i < numBoidsGroup1; i++) {
            // Random positions within the GUI bounds
            positionsGroup[i] = new Point2D.Float(rand.nextInt(800), rand.nextInt(600));

            // Similar velocities with slight variations
            velocitiesGroup[i] = new Point2D.Float(1 + rand.nextFloat(), 1 + rand.nextFloat());
            type.add(1);

        }
        for (int i = numBoidsGroup1; i < numBoidsGroup1+numBoidsGroup2; i++) {
            // Starting from the top-right corner
            positionsGroup[i] = new Point2D.Float(600 + rand.nextInt(200), rand.nextInt(200));

            // Different general velocities
            velocitiesGroup[i] = new Point2D.Float(-1 - rand.nextFloat(), 1 + rand.nextFloat());
            type.add(2);
        }

        for (int i = numBoidsGroup1+numBoidsGroup2; i < totalLength; i++) {
            // Starting from the top-right corner
            positionsGroup[i] = new Point2D.Float(400 + rand.nextInt(100), rand.nextInt(500));

            // Different general velocities
            velocitiesGroup[i] = new Point2D.Float(-1 - rand.nextFloat(), 1 + rand.nextFloat());
            type.add(3);
        }

        MultipleBoidSimulation simulation = new MultipleBoidSimulation(positionsGroup, velocitiesGroup,5, 2, 120, 1, gui,diameter,coeffCohesion,coeffAlignment,coeffSeperation, type,numTypes);
        gui.setSimulable(simulation);
    }
}
