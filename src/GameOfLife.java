import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.util.HashSet;

public class GameOfLife {



    private List<Cell> active;
    private List<Cell> initCell;

    private int rows;
    private int columns;




    public List<Cell> getActive() {
        return this.active;
    }

    public GameOfLife(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        this.active = new ArrayList<>();
        this.initCell = new ArrayList<>();

    }

    public void showActive() {
        StringBuilder s = new StringBuilder();
        for (Cell cell: active) {
            s.append("("+cell.getX()+","+cell.getY()+")");
        }
        System.out.println(s.toString());

    }



    public void addCell(int x, int y) {
        this.active.add(new Cell(x, y, true));
        this.initCell.add(new Cell(x, y, true));
    }

    public void removeCell(Cell cell) {
        this.active.remove(cell);
    }


    public ArrayList<Cell> getNeighbor(Cell cell) {
        ArrayList<Cell> neighborhood = new ArrayList<>();
        for (int i = -1; i<2; i++) {
            for (int j = -1; j<2; j ++) {
                int newx = (cell.getX() + i + this.columns)%this.columns;
                int newy = (cell.getY() + j + this.rows)%this.rows;
                Cell neighbor = new Cell(newx, newy, true);
                boolean contain = false;
                for (Cell n: active) {
                    if (n.equals(neighbor)) {
                        contain = true;
                    }
                }
                if (/*this.active.contains(neighbor)*/ contain) {
                    neighborhood.add(neighbor);
                } else {
                    neighbor.setState();
                    neighborhood.add(neighbor);
                }
            }
        }
        return neighborhood;
    }

    public int countActive(ArrayList<Cell> list) {
        int acc = 0;
        for (Cell cell: list) {
            if (cell.getState()) {
                acc += 1;
            }
        }

        return acc;
    }


    public void update() {

        ArrayList<Cell> to_add = new ArrayList<>();
        ArrayList<Cell> to_del = new ArrayList<>();


        for (Cell cell: active) {


            ArrayList<Cell> neighbors = this.getNeighbor(cell);

            for (Cell newcell: neighbors) {

                if (newcell.equals(cell)) {

                    assert(newcell.getState()); // I guess so

                    int activecount = countActive(neighbors)-1;

                    if (!(activecount == 2 || activecount == 3)) {
                        boolean test = true;
                        for (Cell c: to_del) {
                            if (c.equals(newcell)) {
                                test = false;
                            }
                            break;
                        }
                        if (test) {
                            to_del.add(newcell);
                        }
                    }

                } else {

                    if (!newcell.getState()) {

                        int activecount = countActive(this.getNeighbor(newcell));

                        if (activecount == 3) {
                            boolean test = true;
                            for (Cell c: to_add) {
                                if (c.equals(newcell)) {
                                    test = false;
                                    break;
                                }
                            }
                            if (test) {
                                to_add.add(new Cell(newcell.getX(), newcell.getY(), true));
                            }
                        }

                    }

                }

            }

        }



        for (Cell cell: to_del) {

            for (Cell c: active) {
                if (c.equals(cell)) {
                    active.remove(c);
                    break;
                }
            }


            //active.remove(cell);
        }

        active.addAll(to_add);

    }


    public void reInit() {

        active.clear();
        active.addAll(this.initCell);

    }





}
