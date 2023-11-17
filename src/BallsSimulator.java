import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

/**
 * Simulates the motion of balls in a graphical user interface.
 * It manages the events related to the balls' movement and their rendering in the GUI.
 */
public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;
    private EventManager eventManager;

    /**
     * Default constructor initializing the simulator without a GUI.
     */
    public BallsSimulator() {
        this.balls = new Balls();
        this.eventManager = new EventManager();
        this.eventManager.addEvent(new BallsEvent(1, balls, eventManager, null));
    }

    /**
     * Constructor for initializing the simulator with a GUI.
     *
     * @param gui The GUISimulator where the balls will be displayed.
     */
    public BallsSimulator(GUISimulator gui) {
        this.balls = new Balls();
        this.eventManager = new EventManager();
        this.gui = gui;
        this.eventManager.addEvent(new BallsEvent(1, balls, eventManager, gui));
        System.out.println(balls);
    }

    /**
     * Gets the current Balls object being simulated.
     *
     * @return The current Balls object.
     */
    public Balls getBalls() {
        return this.balls;
    }

    /**
     * Advances the simulation to the next time step.
     * This method is called to progress the simulation and update the GUI.
     */
    @Override
    public void next() {
        this.eventManager.next();
        drawBalls();
    }

    /**
     * Restarts the simulation to its initial state.
     * This method resets the ball positions and reinitializes the event manager.
     */
    @Override
    public void restart() {
        balls.reInit();
        this.eventManager.restart();
        this.eventManager.addEvent(new BallsEvent(1, balls, eventManager, gui));
        drawBalls();
    }

    /**
     * Draws the balls on the GUI.
     * This method updates the GUI with the current positions of the balls.
     */
    private void drawBalls() {
        gui.reset();
        for (Point ball : balls.getBalls()) {
            gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.WHITE, Color.WHITE, 10));
        }
    }
}
