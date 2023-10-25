import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;
    public BallsSimulator(){

        this.balls = new Balls();
        balls.addBall(100,110);
        System.out.println(balls);

    }
    public BallsSimulator(GUISimulator gui) {
        this.balls = new Balls();
        this.gui = gui;
        this.balls.setWindowSize(gui.getWidth(), gui.getHeight());
        balls.addBall(100,110);
        System.out.println(balls);

    }
    public Balls getBalls(){
        return this.balls;
    }


    @Override
    public void next(){
        balls.translate(10,10);
        System.out.println("my balls were hot");
        System.out.println(this.balls.toString());
        drawBalls();
    }
    @Override
    public void restart(){
        balls.reInit();
        System.out.println(this.balls.toString());
        drawBalls();
    }

    public void drawBalls(){
        gui.reset();
        for (Point ball:balls.getBalls()){
            gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.WHITE, Color.WHITE, 10));

        }

    }

}
