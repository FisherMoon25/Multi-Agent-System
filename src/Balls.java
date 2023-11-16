import java.awt.*;
import java.util.ArrayList;
import java.lang.StringBuilder;


public class Balls {
    private ArrayList<Point> positions;
    private final ArrayList<Point> initPositions = new ArrayList<>();
    private ArrayList<Point> velocities;
    private final ArrayList<Point> initVelocities = new ArrayList<>();

    public Balls() {
        this.positions = new ArrayList<>();
        this.velocities = new ArrayList<>();
    }

    public Balls(ArrayList<Point> positions, ArrayList<Point> velocities) {
        this.positions = positions;
        this.velocities = velocities;
        int len = this.positions.size();
        for (int i = 0; i < len; i++) {
            this.initPositions.set(i, positions.get(i));
            this.initVelocities.set(i, velocities.get(i));
        }
    }

    public ArrayList<Point> getPositions() {
        return positions;
    }

    public ArrayList<Point> getVelocities() {
        return velocities;
    }

    public ArrayList<Point> getInitialPositions() {
        return initPositions;
    }

    public ArrayList<Point> getInitVelocities() {
        return initVelocities;
    }

    public void setPositions(ArrayList<Point> positions) {
        this.positions = positions;
    }

    public void setVelocities(ArrayList<Point> velocities) {
        this.velocities = velocities;
    }

    protected void handleRebound(int idx, int windowWidth, int widnowHeight) {
        Point p = this.positions.get(idx);
        int vx = this.velocities.get(idx).x;
        int vy = this.velocities.get(idx).y;
        if (p.x >= windowWidth || p.x < 0) {
            vx *= -1;
        }
        if (p.y >= widnowHeight || p.y < 0) {
            vy *= -1;
        }
        velocities.get(idx).setLocation(vx, vy);
    }

    public void translate(int dx, int dy, int windowWidth, int windowHeight) {
        int len = this.positions.size();
        for (int i = 0; i < len; i++) {
            handleRebound(i, windowWidth, windowHeight);
            int newX = dx + velocities.get(i).x;
            int newY = dy + velocities.get(i).y;

            positions.get(i).setLocation(newX, newY);
        }
    }

    void addBall(int x, int y) {
        positions.add(new Point(x, y));
        initPositions.add(new Point(x, y));
        velocities.add(new Point(1, 1)); // Initial velocity vector is towards the
        initVelocities.add(new Point(1, 1));

    }
    public void reInit() {
        for (int i = 0; i < positions.size(); i++) {
            positions.get(i).setLocation(initPositions.get(i));
            velocities.get(i).setLocation(initVelocities.get(i));
        }
    }

    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        for (Point ball: positions) {
            st.append("(").append(ball.x).append(",").append(ball.y).append(")");
        }
        return st.toString();
    }

}
