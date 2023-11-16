import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;
import java.awt.*;


public class BallsSimulator implements Simulable {
    private final Balls balls;
    private GUISimulator gui;
    private final EventManager eventManager;

    public BallsSimulator() {

        this.balls = new Balls();
        this.eventManager = new EventManager();
        this.eventManager.addEvent(new BallsEvent(1, balls, eventManager, gui));

    }

    public BallsSimulator(GUISimulator gui) {
        this.balls = new Balls();
        this.eventManager = new EventManager();
        this.eventManager.addEvent(new BallsEvent(1, balls, eventManager, gui));
        this.gui = gui;
    }

    public Balls getBalls() {
        return this.balls;
    }

    @Override
    public void next() {
        this.eventManager.next();
        drawBalls();
    }

    @Override
    public void restart() {
        balls.reInit();
        this.eventManager.restart();
        this.eventManager.addEvent(new BallsEvent(1, balls, eventManager, gui));
        drawBalls();
    }

    public void drawBalls() {
        gui.reset();
        for (Point ball:balls.getPositions()) {
            gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.WHITE, Color.WHITE, 10));

        }
    }
}
