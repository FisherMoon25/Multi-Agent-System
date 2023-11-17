package gui;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Triangle class extends CenteredGraphicalElement to represent a triangle graphical element.
 * It can be used in GUI applications to draw triangles with specified colors, size, and orientation.
 */
public class Triangle extends CenteredGraphicalElement {
    private Color drawColor;     // Color used to draw the outline of the triangle
    private Color fillColor;     // Color used to fill the triangle
    private int base;            // Base length of the triangle
    private int height;          // Height of the triangle
    private Point2D.Float orientation; // Orientation vector of the triangle

    /**
     * Constructor to create a triangle graphical element.
     *
     * @param x           X-coordinate of the triangle's position.
     * @param y           Y-coordinate of the triangle's position.
     * @param drawColor   Color of the triangle's outline.
     * @param fillColor   Color to fill the triangle.
     * @param base        Base length of the triangle.
     * @param height      Height of the triangle.
     * @param orientation Orientation vector of the triangle.
     */
    public Triangle(int x, int y, Color drawColor, Color fillColor, int base, int height, Point2D.Float orientation) {
        super(x, y);
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.base = base;
        this.height = height;
        this.orientation = orientation;
    }

    /**
     * Method to paint the triangle on a Graphics2D object.
     * The triangle is drawn based on its properties such as position, color, size, and orientation.
     *
     * @param g Graphics2D object on which the triangle is to be drawn.
     */
    public void paint(Graphics2D g) {
        Stroke originalStroke = g.getStroke();
        g.setStroke(new BasicStroke(2.0F));
        Color originalColor = g.getColor();

        // Calculate points for the triangle based on its orientation and size
        double a = (this.orientation.getY()) / (this.orientation.getX());
        double frac = Math.sqrt(1 / (1 + a * a));
        int coeff = (this.orientation.getX() >= 0) ? -1 : 1;

        double diffx = coeff * ((float) this.height / 2) * frac;
        double diffy = coeff * ((float) this.height * a / 2) * frac;

        Point2D.Float pt_top = new Point2D.Float((float) (this.getX() - diffx), (float) (this.getY() - diffy));
        Point2D.Float pt_bot = new Point2D.Float((float) (this.getX() + diffx), (float) (this.getY() + diffy));

        double other_a = -1 / a;
        double ofrac = Math.sqrt(1 / (1 + other_a * other_a));
        double odiffx = coeff * ((float) this.base / 2) * ofrac;
        double odiffy = coeff * ((float) this.base * other_a / 2) * ofrac;

        Point2D.Float pt_1 = new Point2D.Float((float) (pt_bot.getX() - odiffx), (float) (pt_bot.getY() - odiffy));
        Point2D.Float pt_2 = new Point2D.Float((float) (pt_bot.getX() + odiffx), (float) (pt_bot.getY() + odiffy));

        int[] xPoints = {(int) pt_top.getX(), (int) pt_1.getX(), (int) pt_2.getX()};
        int[] yPoints = {(int) pt_top.getY(), (int) pt_1.getY(), (int) pt_2.getY()};

        // Fill the triangle if a fill color is specified
        if (this.fillColor != null) {
            g.setColor(this.fillColor);
            g.fillPolygon(xPoints, yPoints, 3);
        }

        // Draw the outline of the triangle
        g.setColor(this.drawColor);
        g.drawPolygon(xPoints, yPoints, 3);

        // Restore original color and stroke
        g.setColor(originalColor);
        g.setStroke(originalStroke);
    }

    /**
     * Provides a string representation of the Triangle object.
     *
     * @return A string that describes the Triangle, including its draw color.
     */
    @Override
    public String toString() {
        return this.drawColor.toString() + " triangle";
    }
}
