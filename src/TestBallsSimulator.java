import gui.GUISimulator ;
import java.awt.Color ;

public class TestBallsSimulator {
    public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (1000 , 500 , Color.BLACK );
        BallsSimulator ballsSim = new BallsSimulator(gui);
        ballsSim.getBalls().AddBall(100, 10);
        ballsSim.getBalls().AddBall(40, 20);
        gui.setSimulable(ballsSim) ;
    }
}