import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;

import java.awt.*;
import java.util.List;

public class BallsSimulator implements Simulable {


    private Balls balls;
    private GUISimulator gui;

    public BallsSimulator() {
        this.balls = new Balls();
    }

    public BallsSimulator(GUISimulator gui) {
        this();
        this.gui = gui;
        this.balls.setSizeWindow(gui);
    }

    public Balls getBalls() {
        return this.balls;
    }

    @Override
    public void next() {
        balls.translate(7, 5);
        this.drawBalls();

    }

    @Override
    public void restart() {
        balls.reInit();
        this.drawBalls();
    }


    public void drawBalls() {
        gui.reset();
        for(Point ball: this.balls.getBalls()) {
            gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.WHITE, Color.WHITE, 10));
        }
    }

}
