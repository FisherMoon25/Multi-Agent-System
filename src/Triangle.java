package gui;

import java.awt.*;
import java.awt.geom.Point2D;

public class Triangle extends CenteredGraphicalElement {
    private Color drawColor;
    private Color fillColor;
    private int base;
    private int height;
    private Point2D.Float orientation;


    public Triangle(int x, int y, Color drawColor, Color fillColor, int base, int height, Point2D.Float orientation) {
        super(x, y);
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.base = base;
        this.height = height;
        this.orientation = orientation;
    }

    public void paint(Graphics2D g) {
        Stroke originalStroke = g.getStroke();
        g.setStroke(new BasicStroke(2.0F));
        Color originalColor = g.getColor();

        double a = (this.orientation.getY())/(this.orientation.getX());

        double frac = Math.sqrt(1/(1+a*a));

        int coeff = (this.orientation.getX()>=0)?-1:1;


        double diffx = coeff*((float)this.height/2)*frac;
        double diffy = coeff*((float)this.height*a/2)*frac;

        Point2D.Float pt_top = new Point2D.Float((float)(this.getX()-diffx), (float)(this.getY()-diffy));
        Point2D.Float pt_bot = new Point2D.Float((float)(this.getX()+diffx), (float)(this.getY()+diffy));

        double other_a = -1/a;

        double ofrac = Math.sqrt(1/(1+other_a*other_a));
        double odiffx = coeff*((float)this.base/2)*ofrac;
        double odiffy = coeff*((float)this.base*other_a/2)*ofrac;

        Point2D.Float pt_1 = new Point2D.Float((float)(pt_bot.getX()-odiffx), (float)(pt_bot.getY()-odiffy));
        Point2D.Float pt_2 = new Point2D.Float((float)(pt_bot.getX()+odiffx), (float)(pt_bot.getY()+odiffy));


        int[] xPoints = {(int)pt_top.getX(), (int)pt_1.getX(), (int)pt_2.getX()};
        int[] yPoints = {(int)pt_top.getY(), (int)pt_1.getY(), (int)pt_2.getY()};

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

