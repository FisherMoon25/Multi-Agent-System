import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
public class Balls {
    private ArrayList<Point> balls;

    private ArrayList<Point> initialPositions;

    private ArrayList<Point> directions = new ArrayList<>();



    // Constructeur sans arguments
    public Balls() {
        this.balls = new ArrayList<>();
        this.initialPositions = new ArrayList<>();

    }


    public void translate(int dx, int dy, int windowWidth, int windowHeight) {
        for (int i = 0; i < this.balls.size(); i++) {
            Point ball = this.balls.get(i);
            Point direction = this.directions.get(i);
            int newX = ball.x + dx * direction.x;
            int newY = ball.y + dy * direction.y;

            // Vérifier les bords horizontaux
            if (newX < 0) {
                direction.x = 1;
                newX = 0; // Set the ball exactly at the edge
            } else if (newX > windowWidth) {
                direction.x = -1;
                newX = windowWidth; // Set the ball exactly at the edge
            }

            // Vérifier les bords verticaux
            if (newY < 0) {
                direction.y = 1;
                newY = 0; // Set the ball exactly at the edge
            } else if (newY > windowHeight) {
                direction.y = -1;
                newY = windowHeight; // Set the ball exactly at the edge
            }

            ball.setLocation(newX, newY);
        }
    }

    void addBall(int x,int y) {
        balls.add(new Point(x, y));
        initialPositions.add(new Point(x, y));
        directions.add(new Point(1, 1)); // Initial direction: vers le bas et à droite

    }
    public void reInit() {
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setLocation(initialPositions.get(i));
            directions.get(i).setLocation(new Point(1,1));

        }
    }
    public List<Point> getBalls() {
        return this.balls;
    }

    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        for (Point ball:balls){
            st.append("(").append(ball.x).append(",").append(ball.y).append(")");
        }
        return st.toString();
    }

}
