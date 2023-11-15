package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Polygon;

public class Triangle extends CenteredGraphicalElement {
    private Color drawColor;
    private Color fillColor;
    private int base;
    private int height;

    public Triangle(int x, int y, Color drawColor, Color fillColor, int base, int height) {
        super(x, y);
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.base = base;
        this.height = height;
    }

    public void paint(Graphics2D g) {
        Stroke originalStroke = g.getStroke();
        g.setStroke(new BasicStroke(2.0F));
        Color originalColor = g.getColor();

        int[] xPoints = {getX() - base / 2, getX(), getX() + base / 2};
        int[] yPoints = {getY() + height / 2, getY() - height / 2, getY() + height / 2};

        if (this.fillColor != null) {
            g.setColor(this.fillColor);
            g.fillPolygon(xPoints, yPoints, 3);
        }

        g.setColor(this.drawColor);
        g.drawPolygon(xPoints, yPoints, 3);

        g.setColor(originalColor);
        g.setStroke(originalStroke);
    }

    @Override
    public String toString() {
        return this.drawColor.toString() + " triangle";
    }
}
