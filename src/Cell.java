public class Cell {


    private int x;
    private int y;
    private boolean isActive;


    public Cell(int x, int y, boolean isActive) {

        this.x = x;
        this.y = y;
        this.isActive = isActive;


    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getState() {
        return this.isActive;
    }

    public void setState() {
        this.isActive = !this.isActive;
    }

    public void setState(boolean state) {
        this.isActive = state;
    }

    public boolean equals(Cell cell) {
        return this.x == cell.x && this.y == cell.y;
    }




}
