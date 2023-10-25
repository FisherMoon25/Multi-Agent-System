import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
public class Balls {
    private ArrayList<Point> balls;
    private ArrayList<Point> initial_positions;

    public Balls(){
       balls = new ArrayList<Point>();
       initial_positions = new ArrayList<Point>();
    }
    void translate(int dx, int dy){
        for (Point ball :balls){
             ball.translate(dx,dy);
        }
    }
    void addBall(int x,int y){
        balls.add(new Point(x,y));
        initial_positions.add(new Point(x,y));

    }
    public void reInit() {
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setLocation(initial_positions.get(i));
        }
    }
    public List<Point> getBalls() {
        return new ArrayList<>(balls);
    }

    public String toString(){
        StringBuilder st = new StringBuilder();
        for (Point ball:balls){
            st.append("(").append(ball.x).append(",").append(ball.y).append(")");
        }
        return st.toString();
    }

}
