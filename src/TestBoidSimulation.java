import gui.GUISimulator;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * TestBoidSimulation class to demonstrate a boid simulation using the BoidSimulation class.
 * It sets up a simulation environment with multiple boids and runs it using a GUI.
 */
public class TestBoidSimulation {

    public static void main(String[] args) {

        // Create a new GUI window with specified dimensions and background color
        GUISimulator gui = new GUISimulator(800, 600, Color.WHITE);

        // Initialize a random generator for creating random positions and velocities
        Random random = new Random();

        // Set the number of boids for the simulation
        int numBoids = 100;

        // Define behavior coefficients for cohesion, alignment, and separation
        float coeffCohesion = 0.09F;
        float coeffAlignment = 1;
        float coeffSeperation = 1.5F;

        // Define the diameter of each boid for visualization
        int diameter = 5;

        // Create arrays to store positions and velocities of each boid
        Point2D.Float[] positionPreys = new Point2D.Float[numBoids];
        Point2D.Float[] velocityPreys = new Point2D.Float[numBoids];

        // Initialize positions and velocities of boids with random values
        for(int i = 0; i < numBoids; i++) {
            positionPreys[i] = new Point2D.Float(random.nextFloat(500), random.nextFloat(500));
            velocityPreys[i] = new Point2D.Float(random.nextFloat(5), random.nextFloat(2));
        }

        // Create a BoidSimulation instance with the generated positions, velocities, and parameters
        BoidSimulation simulator = new BoidSimulation(positionPreys, velocityPreys,
                6, 2, 120, 1, gui, diameter, coeffCohesion, coeffAlignment, coeffSeperation);

        // Set the BoidSimulation instance as the simulable object in the GUI
        gui.setSimulable(simulator);
    }
}
