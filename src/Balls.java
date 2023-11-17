import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of balls with their positions and directions.
 * Allows for the translation of these balls within a defined window.
 */
public class Balls {
    private ArrayList<Point> balls;
    private ArrayList<Point> initialPositions;
    private ArrayList<Point> directions;

    /**
     * Constructor for Balls class.
     * Initializes empty lists for balls, initial positions, and directions.
     */
    public Balls() {
        this.balls = new ArrayList<>();
        this.initialPositions = new ArrayList<>();
        this.directions = new ArrayList<>();
    }

    /**
     * Translates the position of each ball in the collection.
     * The translation is bounded by the window dimensions.
     *
     * @param dx            The change in x-coordinate.
     * @param dy            The change in y-coordinate.
     * @param windowWidth   The width of the bounding window.
     * @param windowHeight  The height of the bounding window.
     */
    public void translate(int dx, int dy, int windowWidth, int windowHeight) {
        for (int i = 0; i < this.balls.size(); i++) {
            Point ball = this.balls.get(i);
            Point direction = this.directions.get(i);

            // Calculate new position
            int newX = ball.x + dx * direction.x;
            int newY = ball.y + dy * direction.y;

            // Check horizontal boundaries
            if (newX < 0) {
                direction.x = 1;
                newX = 0;
            } else if (newX > windowWidth) {
                direction.x = -1;
                newX = windowWidth;
            }

            // Check vertical boundaries
            if (newY < 0) {
                direction.y = 1;
                newY = 0;
            } else if (newY > windowHeight) {
                direction.y = -1;
                newY = windowHeight;
            }

            ball.setLocation(newX, newY);
        }
    }

    /**
     * Adds a new ball with specified initial position.
     *
     * @param x The x-coordinate of the ball.
     * @param y The y-coordinate of the ball.
     */
    public void addBall(int x, int y) {
        balls.add(new Point(x, y));
        initialPositions.add(new Point(x, y));
        directions.add(new Point(1, 1)); // Initial direction is diagonal (down-right)
    }

    /**
     * Reinitializes all balls to their original positions and directions.
     */
    public void reInit() {
        for (int i = 0; i < balls.size(); i++) {
            this.balls.get(i).setLocation(this.initialPositions.get(i));
            this.directions.get(i).setLocation(new Point(1, 1));
        }
    }

    /**
     * Gets the current list of balls.
     *
     * @return A list of points representing the balls' positions.
     */
    public List<Point> getBalls() {
        return this.balls;
    }

    /**
     * Returns a string representation of the balls' positions.
     *
     * @return A string that lists all the balls' coordinates.
     */
    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (Point ball : balls) {
            st.append("(").append(ball.x).append(",").append(ball.y).append(") ");
        }
        return st.toString().trim();
    }
}
