import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Balls {
    private List<Point> balls;
    private List<Point> initPos;

    public Balls()
    {
        this.balls = new ArrayList<Point>();
        this.initPos = new ArrayList<Point>();
    }

    public void addBall(int x, int y)
    {
        this.balls.add(new Point(x, y));
        this.initPos.add(new Point(x, y));
    }
    public Balls(List<Point> balls)
    {
        this.balls = new ArrayList<Point>();
        this.initPos = new ArrayList<Point>();
        for (Point ball: balls)
        {
            this.addBall((int) ball.getX(), (int) ball.getY());
        }
    }

    public void translate(int dx, int dy) {
        for (Point ball : balls) {
            ball.translate(dx, dy);
        }
    }

    public void reInit()
    {
        for (int idx = 0; idx < balls.size(); idx++)
        {
            balls.get(idx).setLocation(this.initPos.get(idx));
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Point ball : balls) {
            str.append(ball.toString());
            str.append(" ");
        }
        return str.toString().trim();
    }
}
