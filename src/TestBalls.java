import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class TestBalls {
    public static void main(String[] args) {

        List<Point> initialPositions = new ArrayList<>();
        initialPositions.add(new Point(0, 0));
        initialPositions.add(new Point(1, 1));
        initialPositions.add(new Point(2, 2));

        Balls balls = new Balls(initialPositions);
        System.out.println("Initial State:");
        System.out.println(balls);

        balls.translate(1, 1);
        System.out.println("\nAfter Translation:");
        System.out.println(balls);

        balls.reInit();
        System.out.println("\nAfter Reset:");
        System.out.println(balls);

        balls.translate(1, 1);
        System.out.println("\nAfter Translation:");
        System.out.println(balls);

        balls.reInit();
        System.out.println("\nAfter Reset:");
        System.out.println(balls);

        System.out.println("\n");
    }
}
