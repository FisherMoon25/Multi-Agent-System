import java.awt.*;
import java.awt.geom.Point2D;
import java.io.PipedInputStream;
import java.util.List;

public class Boid {


    protected Point2D.Float[] position;

    protected Point2D.Float[] velocity;
    protected Point2D.Float[] acceleration;


    protected float fieldOfView;

    protected float distSeparation;

    protected float distNeighbor;

    protected int id;

    protected int diameter;

    protected float vlimX;
    protected float vlimY;

    protected long step;


    public Boid(Point2D.Float[] position, Point2D.Float[] velocity,float vlimX,float vlimY, float fieldOfView,long step) {
        this.step= step;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Point2D.Float[position.length];
        for (int i =0;i<position.length;i++){
            this.acceleration[i]=new Point2D.Float(0,0);
        }
        this.fieldOfView = fieldOfView;
        this.distSeparation = 10;
        this.distNeighbor = 20;
        this.id = 0;
        this.diameter = 10;
        this.vlimX =vlimX ;
        this.vlimY = vlimY;


    }




    public Point2D.Float[] getPosition() {
        return this.position;
    }


    public Point2D.Float[] getVelocity() {
        return this.velocity;
    }


    public float getFieldOfView() {
        return this.fieldOfView;
    }

    public int getId() {
        return this.id;
    }

    public int getDiam() {
        return this.diameter;
    }
    public long getStep(){
        return this.step;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setPosition(Point2D.Float[] position) {
        this.position=position;
    }


    public void setVelocity(Point2D.Float[] velocity) {
        this.velocity =velocity;
    }

    public void setFieldOfView(float fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public void setDiam(int diameter) {
        this.diameter = diameter;
    }
    public void setStep(){
        this.step = step;
    }
    public void cohesion() {

        int numBoids = this.getPosition().length;
        for (int i = 0 ;i<numBoids;i++){
            Point2D.Float c = new Point2D.Float(0, 0);

            int count = 0;
            for (int j = 0 ;j<numBoids;j++) {


                if (this.isNeighbor(j,i)) {

                    c.setLocation(c.getX() + this.getPosition()[j].getX(), c.getY() +this.getPosition()[j].getY());

                   count++;

                }

            }

            if (count != 0) {
                c.setLocation(c.getX() / count, c.getY() / count);

                //c.setLocation((c.getX() - this.getPosition()[i].getX()), (c.getY() - this.getPosition()[i].y));

                float normC=(float )Math.sqrt(c.x*c.x + c.y*c.y);
                c.setLocation(c.getX()*vlimY / normC, c.getY()*vlimY / normC);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                float m = (float) Math.sqrt(acceleration.x*acceleration.x + acceleration.y*acceleration.y);
                if(m > 3){
                    acceleration.x = (int) (acceleration.x*3/m);
                    acceleration.y = (int) (acceleration.y*3/m);
                }

                this.acceleration[i].x += acceleration.x;
                this.acceleration[i].y += acceleration.y;
            }
        }
    }



    public void separation() {


        int numBoids = this.getPosition().length;
        for (int i = 0 ;i<numBoids;i++){
            Point2D.Float c = new Point2D.Float(0, 0);
            int count =0;
            for (int j = 0 ;j<numBoids;j++) {
                float distX=this.getPosition()[j].x-this.getPosition()[i].x;
                float distY = this.getPosition()[j].y-this.getPosition()[i].y;
                float distance=(float)Math.sqrt(distX*distX+distY*distY);
                if (distance>0 && distance<distSeparation) {
                    c.setLocation(c.getX() - distX, c.getY() -distY);
                    float normC=(float)Math.sqrt(c.x*c.x+c.y*c.y);
                    c.setLocation(c.x/(normC*distance),c.y/(normC*distance));
                    count++;
                }

            }


            //c.setLocation((c.getX() - this.getPosition()[i].getX())/100, (c.getY() - this.getPosition()[i].y)/100);

            if (count>0){
                c.setLocation(c.x/count,c.y/count);
                float normC=(float )Math.sqrt(c.x*c.x + c.y*c.y);
                c.setLocation(c.x*vlimY/normC,c.y*vlimY/normC);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                float m = (float) Math.sqrt(acceleration.x*acceleration.x + acceleration.y*acceleration.y);
                if(m > 3){
                    acceleration.x = (int) (acceleration.x*3/m);
                    acceleration.y = (int) (acceleration.y*3/m);
                }


                this.acceleration[i].x += acceleration.x;
                this.acceleration[i].y += acceleration.y;
            }

        }
    }

    public void alignement() {

        int numBoids = this.getPosition().length;
        for (int i = 0 ;i<numBoids;i++){
            Point2D.Float c = new Point2D.Float(0, 0);

            int count = 0;
            for (int j = 0 ;j<numBoids;j++) {


                if (this.isNeighbor(j,i)) {

                    c.setLocation(c.getX() + this.getVelocity()[j].getX(), c.getY() +this.getVelocity()[j].getY());
                    count++;

                }

            }

            if (count != 0) {
                c.setLocation(c.getX() / count, c.getY() / count);
                //c.setLocation((c.getX() - this.getVelocity()[i].getX()) / 8, (c.getY() - this.getVelocity()[i].getY()) / 8);
                float normC=(float )Math.sqrt(c.x*c.x + c.y*c.y);
                c.setLocation(c.getX()*vlimY / normC, c.getY()*vlimY / normC);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                float m = (float) Math.sqrt(acceleration.x*acceleration.x + acceleration.y*acceleration.y);
                if(m > 3){
                    acceleration.x = (int) (acceleration.x*3/m);
                    acceleration.y = (int) (acceleration.y*3/m);
                }


                this.acceleration[i].x += acceleration.x;
                this.acceleration[i].y += acceleration.y;
            }
        }

    }

    public void update(int width, int height) {


        this.cohesion();
        this.separation();
        this.alignement();
        int numBoids =this.getPosition().length;

        for(int i=0;i<numBoids;i++){
            this.getVelocity()[i].x +=this.acceleration[i].x;
            this.getVelocity()[i].y +=this.acceleration[i].y;
            this.getPosition()[i].x+=this.getVelocity()[i].x;
            this.getPosition()[i].y+=this.getVelocity()[i].y;
            System.out.println(this.acceleration[i].x);
            System.out.println(this.acceleration[i].y);
            System.out.println(this.getPosition()[i].x);
            System.out.println(this.getPosition()[i].y);
            this.acceleration[i].setLocation(0,0);
            this.boundedPosition(getPosition()[i],getVelocity()[i],width,height);
        }

    }
    public void boundedPosition(Point2D.Float pos,Point2D.Float vel,int width,int height){
        if (pos.getX() < 0) {

            double dist = pos.getX() % width;
            int direc = (vel.x>0)?-1:1;
            vel.setLocation(-direc*vel.getX(), vel.getY());
            pos.setLocation(pos.getX()-direc*2*dist, pos.getY());


        } else if (pos.getX() > width) {

            double dist = pos.getX() % width;
            int direc = (vel.getX()>0)?-1:1;
            vel.setLocation(direc*vel.getX(), vel.getY());
            pos.setLocation(pos.getX()+direc*2*dist, pos.getY());

        }

        if (pos.getY() > height) {

            double dist = pos.getY() % height;
            int direc = (vel.getY()>0)?-1:1;
            vel.setLocation(vel.getX(), direc*vel.getY());
            pos.setLocation(pos.getX(), pos.getY()+direc*2*dist);


        } else if (pos.getY() < 0) {

            double dist = pos.getY() % height;
            int direc = (vel.getY()>0)?-1:1;
            vel.setLocation(vel.getX(), -direc*vel.getY());
            pos.setLocation(pos.getX(), pos.getY()-direc*2*dist);

        }






    }
    public double angleBetween(Point2D.Float point1, Point2D.Float point2){
        double scalarProduct = point1.getX()*point2.getX() + point1.getY()*point2.getY();
        double point1Norm =Math.sqrt(Math.pow(point1.getX(),2)+Math.pow(point1.getY(),2));
        double point2Norm =Math.sqrt(Math.pow(point2.getX(),2)+Math.pow(point2.getY(),2));
        return Math.acos(scalarProduct/(point1Norm*point2Norm));

    }
    public boolean isNeighbor(int i ,int j) {

        Point2D.Float p =new Point2D.Float(this.getPosition()[i].x - this.getPosition()[j].x, this.getPosition()[i].y - this.getPosition()[j].y);
        double d = Math.sqrt(p.x*p.x+p.y*p.y);
        if ( d < distNeighbor) { // Check distance
           Point2D.Float toOther =new  Point2D.Float((float) (this.getPosition()[j].x-this.getPosition()[i].x), (float)(this.getPosition()[j].getY()-this.getPosition()[j].getY()));
           double angle = angleBetween(this.getVelocity()[i], toOther);
           return angle < Math.toRadians(this.fieldOfView);
        }
        return false;
    }


    public boolean equals(Boid boid) {
        return this.id == boid.getId();
    }







}