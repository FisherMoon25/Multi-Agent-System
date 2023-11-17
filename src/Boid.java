
import java.awt.geom.Point2D;


public class Boid {

    protected Point2D.Float[] position;
    protected Point2D.Float[] init_position;
    protected Point2D.Float[] velocity;
    protected Point2D.Float[] init_velocity;
    protected Point2D.Float[] acceleration;

    protected float fieldOfView;
    protected float distSeparation;
    protected float distNeighbor;

    protected int diameter;

    protected float vlim;
    protected float maxForce;

    protected long step;

    /**
     * Constructs a Boid with specified parameters.
     *
     * @param position    Initial position of the boid.
     * @param velocity    Initial velocity of the boid.
     * @param vlim     Maximum velocity limit .
     * @param maxForce Maximum force limit
     * @param fieldOfView Field of view of the boid, determining its visible area.
     * @param step        Simulation step size.
     */
    public Boid(Point2D.Float[] position, Point2D.Float[] velocity,float vlim,float maxForce, float fieldOfView,long step,int diameter) {

        this.position = position;
        this.velocity = velocity;
        int length =position.length;
        this.init_position=new Point2D.Float[length];
        this.init_velocity=new Point2D.Float[length];
        this.acceleration = new Point2D.Float[length];
        for (int i =0;i<length;i++){
            this.init_position[i] = new Point2D.Float(position[i].x,position[i].y);
            this.init_velocity[i] = new Point2D.Float(velocity[i].x,velocity[i].y);
            this.acceleration[i]=new Point2D.Float(0,0);
        }
        this.fieldOfView = fieldOfView;
        this.distSeparation =60;
        this.distNeighbor = 40;
        this.diameter = diameter;
        this.vlim=vlim;
        this.maxForce=maxForce;
        this.step= step;
        System.out.println(this.diameter);


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



    public int getDiam() {
        return this.diameter;
    }
    public long getStep(){
        return this.step;
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

    /**
     * Performs cohesion behavior for a single boid, moving it towards the center of mass of its neighbors.
     *
     * @param i Index of the boid to perform cohesion on.
     */
    public void cohesion(int i) {

            int numBoids = this.getPosition().length;
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

                c.setLocation((c.getX() - this.getPosition()[i].getX()), (c.getY() - this.getPosition()[i].y));

                float normC=(float)Math.sqrt(c.x*c.x + c.y*c.y);
                c.setLocation(c.getX()*vlim / normC, c.getY()*vlim / normC);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                limitForce(acceleration);

                this.acceleration[i].x += acceleration.x;
                this.acceleration[i].y += acceleration.y;
            }

    }


    /**
     * Performs separation behavior for a single boid, moving it away from its neighbors to avoid crowding.
     *
     * @param i Index of the boid to perform separation on.
     */
    public void separation(int i) {


            int numBoids = this.getPosition().length;

            Point2D.Float c = new Point2D.Float(0, 0);
            int count =0;
            for (int j = 0 ;j<numBoids;j++) {
                float distX=this.getPosition()[j].x-this.getPosition()[i].x;
                float distY = this.getPosition()[j].y-this.getPosition()[i].y;
                float distance=(float)Math.sqrt(distX*distX+distY*distY);
                if (distance>0 && distance<distSeparation) {
                    Point2D.Float diff = new Point2D.Float(distX/(distance*distance),distY/(distance*distance));
                    c.setLocation(c.getX() - diff.x, c.getY() - diff.y);
                    count++;
                }

            }



            if (count>0){
                c.setLocation(c.x/count,c.y/count);
                float normC=(float )Math.sqrt(c.x*c.x + c.y*c.y);
                if (normC>0){
                    c.setLocation(c.x*vlim/normC,c.y*vlim/normC);

                }
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                limitForce(acceleration);

                this.acceleration[i].x += acceleration.x;
                this.acceleration[i].y += acceleration.y;
            }


    }

    /**
     * Performs alignment behavior for a single boid, adjusting its velocity to match the average velocity of its neighbors.
     *
     * @param i Index of the boid to perform alignment on.
     */
    public void alignement(int i) {

            int numBoids = this.getPosition().length;
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
                float normC=(float )Math.sqrt(c.x*c.x + c.y*c.y);
                c.setLocation(c.getX()*vlim / normC, c.getY()*vlim / normC);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                limitForce(acceleration);
                this.acceleration[i].x += acceleration.x;
                this.acceleration[i].y += acceleration.y;
            }


    }
    /**
     * Updates the state of the boid, applying cohesion, separation, and alignment behaviors.
     *
     * @param width  Width of the simulation area.
     * @param height Height of the simulation area.
     */
    public void update(int width, int height) {



        int numBoids =this.getPosition().length;

        for(int i=0;i<numBoids;i++){
            this.cohesion(i);
            this.separation(i);
            this.alignement(i);

            this.getVelocity()[i].x +=this.acceleration[i].x;
            this.getVelocity()[i].y +=this.acceleration[i].y;

            limitVelocity(this.getVelocity()[i]);

            this.getPosition()[i].x+=this.getVelocity()[i].x;
            this.getPosition()[i].y+=this.getVelocity()[i].y;

            this.acceleration[i].setLocation(0,0);
            this.boundedPosition(getPosition()[i],getVelocity()[i],width,height);
        }

    }

    /**
     * Reinitializes the position and velocity of each boid to its initial state. This can be used
     * to reset the simulation.
     */
    public void reInit(){
        for (int i =0;i <this.position.length;i++){
            this.position[i].setLocation(this.init_position[i]);
            this.velocity[i].setLocation(this.init_velocity[i]);
        }

    }
    /**
     * Limits the velocity of a boid to the maximum velocity.
     *
     * @param vel The velocity vector of the boid to be limited.
     */
    public void limitVelocity(Point2D.Float vel) {
        float speed = (float) Math.sqrt(vel.x * vel.x + vel.y * vel.y);
        if (speed >vlim) {
            vel.x = (vel.x / speed) * vlim;
            vel.y = (vel.y / speed) * vlim;
        }
    }

    /**
     * Limits the force applied to a boid to the maximum force.
     *
     * @param force The force vector to be limited.
     */
    public void limitForce(Point2D.Float force){
        float m = (float) Math.sqrt(force.x*force.x + force.y*force.y);
        if(m >= maxForce){
            force.x = (force.x*maxForce/m);
            force.y =  (force.y*maxForce/m);
        }

    }
    /**
     * Ensures that a boid remains within the bounds of the simulation area, adjusting its position
     * and velocity if it goes beyond the boundaries.
     *
     * @param pos   The position of the boid.
     * @param vel   The velocity of the boid.
     * @param width  Width of the simulation area.
     * @param height Height of the simulation area.
     */
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
    /**
     * Calculates the angle between two vectors. This is used to determine if another boid
     * falls within the current boid's field of view.
     *
     * @param point1 The first vector.
     * @param point2 The second vector.
     * @return The angle between the two vectors in radians.
     */
    public double angleBetween(Point2D.Float point1, Point2D.Float point2){
        double scalarProduct = point1.getX()*point2.getX() + point1.getY()*point2.getY();
        double point1Norm =Math.sqrt(Math.pow(point1.getX(),2)+Math.pow(point1.getY(),2));
        double point2Norm =Math.sqrt(Math.pow(point2.getX(),2)+Math.pow(point2.getY(),2));
        return Math.acos(scalarProduct/(point1Norm*point2Norm));

    }

    /**
     * Determines if another boid is considered a neighbor based on distance and field of view.
     *
     * @param j Index of the potential neighbor boid.
     * @param i Index of the current boid.
     * @return True if the boid is a neighbor, false otherwise.
     */
    public boolean isNeighbor(int j ,int i) {

        Point2D.Float p =new Point2D.Float(this.getPosition()[j].x - this.getPosition()[i].x, this.getPosition()[j].y - this.getPosition()[i].y);
        double d = Math.sqrt(p.x*p.x+p.y*p.y);
        if (d>0 &&  d < distNeighbor) { // Check distance
            double angle = angleBetween(this.getVelocity()[i], p);
            return angle < Math.toRadians(this.fieldOfView);
        }
        return false;
    }
    /**
     * Provides a string representation of the Boid object.
     * This representation includes details about each boid's position and velocity.

     * The string starts with a header "Boid:"
     * Each boid's details are listed in a formatted manner.
     *
     * @return A string representation of the Boid object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Boid: ");

        for (int i = 0; i < this.position.length; i++) {
            builder.append("\n  Boid ").append(i).append(": ")
                    .append("Position=(").append(position[i].x).append(", ").append(position[i].y).append("), ")
                    .append("Velocity=(").append(velocity[i].x).append(", ").append(velocity[i].y).append("), ");
        }

        return builder.toString();
    }









}