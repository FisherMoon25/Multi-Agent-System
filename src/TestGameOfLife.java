import java.awt.Point;

public class TestGameOfLife {

    public static void main(String[] args) {

        GameOfLife grid = new GameOfLife(5, 5);
        grid.addCell(0, 0);
        grid.addCell(0, 1);
        grid.addCell(0, 2);

        grid.showActive();

        grid.update();


        grid.showActive();

        grid.reInit();

        grid.showActive();


    }


}
