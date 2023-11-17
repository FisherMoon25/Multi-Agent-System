import gui.GUISimulator;
import java.awt.Color;

/**
 * TestBallsSimulator is a test class to demonstrate the simulation of balls
 * using the BallsSimulator class and the GUI framework.
 */
public class TestBallsSimulator {
    public static void main(String[] args) {
        // Create a new GUI window with specified dimensions and background color
        GUISimulator gui = new GUISimulator(500, 500, Color.BLUE);

        // Instantiate the BallsSimulator with the created GUI
        BallsSimulator B = new BallsSimulator(gui);

        // Add a ball to the BallsSimulator at specified coordinates
        B.getBalls().addBall(100, 110);

        // Set the BallsSimulator as the simulable object for the GUI
        gui.setSimulable(B);
    }
}
