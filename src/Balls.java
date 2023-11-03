import gui.GUISimulator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Balls {

    private List<Point> BallsList;
    private List<Point> BallsInit;
    private List<Point> BallsDirec;


    private int windowWidth = 500;
    private int windowHeight = 500;


    public Balls() {
        this.BallsList = new ArrayList<>();
        this.BallsInit = new ArrayList<>();
        this.BallsDirec = new ArrayList<>();

    }

    public Balls(int windowWidth, int windowHeight) {
        this();
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public List<Point> getBalls() {
        return this.BallsList;
    }

    public void setSizeWindow(GUISimulator window) {
        this.windowHeight = window.getHeight();
        this.windowWidth = window.getWidth();
    }

    public void AddBall(int x, int y) {
        BallsList.add(new Point(x, y));
        BallsInit.add(new Point(x, y));
        BallsDirec.add(new Point(1, 1));
    }

    public void translate(int dx, int dy) {

        for (int i = 0; i<this.BallsList.size(); i ++) {

            Point ball = this.BallsList.get(i);
            Point ballDirec = this.BallsDirec.get(i);

            int newx = ball.x + ballDirec.x * dx;
            int newy = ball.y + ballDirec.y * dy;

            System.out.println(ball.x);
            System.out.println(newx);

            if (newx < 0) {
                int dist = newx % this.windowWidth;
                ballDirec.x = (ballDirec.x == 1)?-1:1;
                newx -= ballDirec.x * 2 * dist;
            } else if (newx > this.windowWidth) {
                int dist = newx % this.windowWidth;
                ballDirec.x = (ballDirec.x == 1)?-1:1;
                newx += ballDirec.x * 2 * dist;
            }

            if (newy > this.windowHeight) {
                int dist = newy % this.windowHeight;
                ballDirec.y = (ballDirec.y == 1)?-1:1;
                newy += ballDirec.y * 2 * dist;
            } else if (newy < 0) {
                int dist = newy % this.windowHeight;
                ballDirec.y = (ballDirec.y == 1)?-1:1;
                newy -= ballDirec.y * 2 * dist;
            }

            ball.setLocation(newx, newy);


        }



    }

    public void reInit() {

        for (int i = 0; i < BallsList.size(); i++) {
            BallsList.get(i).setLocation(BallsInit.get(i));
            BallsDirec.get(i).setLocation(new Point(1, 1));
        }

    }


    public String toString() {
        StringBuilder to_paste = new StringBuilder();
        for(Point ball: BallsList) {
            String element = " | x : " + ball.x + " - y : " + ball.y;
            to_paste.append(element);
        }
        return to_paste.toString();
    }


}
