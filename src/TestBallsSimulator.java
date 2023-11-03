import gui.GUISimulator;
import java.awt.Color;

public class TestBallsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLUE);
        BallsSimulator B = new BallsSimulator(gui);
        B.getBalls().addBall(100,110);
        gui.setSimulable(B);
    }
}
