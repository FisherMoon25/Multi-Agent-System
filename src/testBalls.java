public class testBalls {
    public static void main(String[] args) {
        // Create a Balls instance and add some balls
        Balls balls = new Balls();
        balls.addBall(1, 1);
        balls.addBall(2, 2);
        balls.addBall(3, 3);
        System.out.println("Initial positions of the balls: " + balls.toString());

        // Test translating the balls
        balls.translate(2, 2);
        System.out.println("Positions after translating by (2, 2): " + balls.toString());
        assert balls.toString().equals("(3, 3) (4, 4) (5, 5) ");

        // Test reinitializing the balls
        balls.reInit();
        System.out.println("Positions after reinitialization: " + balls.toString());
        assert balls.toString().equals("(1, 1) (2, 2) (3, 3) ");

        // Test translating in the negative direction
        balls.translate(-1, -1);
        System.out.println("Positions after translating by (-1, -1): " + balls.toString());
        assert balls.toString().equals("(0, 0) (1, 1) (2, 2) ");

        // Test reinitializing again
        balls.reInit();
        System.out.println("Positions after another reinitialization: " + balls.toString());
        assert balls.toString().equals("(1, 1) (2, 2) (3, 3) ");


    }
}

