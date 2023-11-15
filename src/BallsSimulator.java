import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;

    private EventManager eventManager;
    public BallsSimulator(){

        this.balls = new Balls();
        this.eventManager = new EventManager();
        this.eventManager.addEvent(new BallsEvent(1,balls,eventManager));

    }
    public BallsSimulator(GUISimulator gui) {
        this.balls = new Balls();
        this.eventManager = new EventManager();
        this.eventManager.addEvent(new BallsEvent(1,balls,eventManager));

        this.gui = gui;
        this.balls.setWindowSize(gui.getWidth(), gui.getHeight());
        System.out.println(balls);

    }
    public Balls getBalls(){
        return this.balls;
    }


    @Override
    public void next(){
        this.eventManager.next();
        drawBalls();
    }
    @Override
    public void restart(){
        balls.reInit();
        this.eventManager.restart();
        this.eventManager.addEvent(new BallsEvent(1, balls,eventManager));
        drawBalls();
    }

    public void drawBalls(){
        gui.reset();
        for (Point ball:balls.getBalls()){
            gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.WHITE, Color.WHITE, 10));

        }

    }

}
