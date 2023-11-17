
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Boid {

    protected Point2D.Float[] positions;
    protected Point2D.Float[] initPositions;
    protected Point2D.Float[] velocities;
    protected Point2D.Float[] initVelocities;
    protected Point2D.Float[] acceleration;
    protected final float fieldOfView;
    protected final float distSeparation;
    protected final float distNeighbor;
    protected final int diameter;
    protected final float maxSpeed;
    protected final float maxForce;
    protected long step;
    private final int initLength;
    private final float cohesionCoeff;
    private final float alignmentCoeff;
    private final float seperationCoeff;
    private List<Integer> type;

    /**
     * Constructs a Boid with specified parameters.
     * @param positions Initial positions of the boids.
     * @param velocities Initial velocities of the boids.
     * @param maxSpeed Maximum speed of the boids.
     * @param maxForce Maximum force that can be applied to the boids.
     * @param fieldOfView Field of view angle for the boids.
     * @param step Simulation step size.
     * @param diameter Diameter of each boid for visualization.
     * @param cohesionCoeff Coefficient for cohesion behavior.
     * @param alignmentCoeff Coefficient for alignment behavior.
     * @param seperationCoeff Coefficient for separation behavior.
     */
    public Boid(Point2D.Float[] positions, Point2D.Float[] velocities, float maxSpeed, float maxForce,
                float fieldOfView, long step, int diameter, float cohesionCoeff, float alignmentCoeff, float seperationCoeff) {

        this.positions = positions;
        this.velocities = velocities;
        this.initLength = positions.length;
        this.initPositions = new Point2D.Float[initLength];
        this.initVelocities = new Point2D.Float[initLength];
        this.acceleration = new Point2D.Float[initLength];
        this.type = new ArrayList<>(initLength);
        for (int i = 0; i < initLength; i++) {
            this.initPositions[i] = new Point2D.Float(positions[i].x, positions[i].y);
            this.initVelocities[i] = new Point2D.Float(velocities[i].x, velocities[i].y);
            this.acceleration[i] = new Point2D.Float(0,0);
            this.type.add(0);
        }
        this.fieldOfView = fieldOfView;
        this.distSeparation =20;
        this.distNeighbor = 40;
        this.diameter = diameter;
        this.maxSpeed = maxSpeed;
        this.maxForce = maxForce;
        this.step = step;
        this.cohesionCoeff = cohesionCoeff;
        this.alignmentCoeff = alignmentCoeff;
        this.seperationCoeff = seperationCoeff;
    }
    public Boid(Point2D.Float[] position, Point2D.Float[] velocity, float maxSpeed, float maxForce,
                float fieldOfView, long step, int diameter, float cohesionCoeff, float alignmentCoeff, float seperationCoeff, List<Integer> type) {

        this(position,velocity,maxSpeed,maxForce, fieldOfView,step,diameter,cohesionCoeff,alignmentCoeff,seperationCoeff);
        this.type = type;

    }

    public Point2D.Float[] getPositions() {
        return this.positions;
    }

    public Point2D.Float[] getVelocities() {
        return this.velocities;
    }

    public long getStep(){
        return this.step;
    }

    public List<Integer> getType() {
        return this.type;
    }

    /**
     * Performs cohesion behavior for a single boid, moving it towards the center of mass of its neighbors.
     *
     * @param i Index of the boid to perform cohesion on.
     */
    public void cohesion(int i) {

            int numBoids = this.getPositions().length;
            Point2D.Float c = new Point2D.Float(0, 0);
            int count = 0;
            for (int j = 0 ; j < numBoids; j++) {
                if (this.isNeighbor(j,i) &&
                        this.getType().get(i).equals(this.getType().get(j))) {
                    c.setLocation(c.getX() + this.getPositions()[j].getX(), c.getY() + this.getPositions()[j].getY());
                    count++;
                }
            }
            if (count > 0) {
                c.setLocation(c.getX() / count, c.getY() / count);
                c.setLocation((c.getX() - this.getPositions()[i].getX()), (c.getY() - this.getPositions()[i].y));
                float normC = (float) Math.sqrt(c.x * c.x + c.y * c.y);
                c.setLocation(c.getX() * maxSpeed / normC, c.getY() * maxSpeed / normC);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocities()[i].x, c.y - this.getVelocities()[i].y);
                limitForce(acceleration);
                this.acceleration[i].x += cohesionCoeff * acceleration.x;
                this.acceleration[i].y += cohesionCoeff * acceleration.y;
            }

    }


    /**
     * Performs separation behavior for a single boid, moving it away from its neighbors to avoid crowding.
     *
     * @param i Index of the boid to perform separation on.
     */
    protected void separation(int i) {
            int numBoids = this.getPositions().length;

            Point2D.Float c = new Point2D.Float(0, 0);
            int count = 0;
            for (int j = 0 ; j < numBoids; j++) {
                float distX = this.getPositions()[j].x - this.getPositions()[i].x;
                float distY = this.getPositions()[j].y - this.getPositions()[i].y;
                float distance = (float) Math.sqrt(distX * distX + distY * distY);
                if (distance > 0 && distance < distSeparation) {
                    Point2D.Float diff = new Point2D.Float(distX / distance,
                            distY / distance);
                    c.setLocation(c.getX() - diff.x, c.getY() - diff.y);
                    count++;
                }

            }
            if (count > 0) {
                c.setLocation(c.x / count,c.y / count);
                float normC = (float)Math.sqrt(c.x * c.x + c.y * c.y);
                if (normC > 0) {
                    c.setLocation(c.x * maxSpeed / normC,
                            c.y * maxSpeed / normC);
                }
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocities()[i].x, c.y - this.getVelocities()[i].y);
                limitForce(acceleration);
                this.acceleration[i].x += seperationCoeff*acceleration.x;
                this.acceleration[i].y += seperationCoeff*acceleration.y;
            }
    }
    /**
     * Performs alignment behavior for a single boid, adjusting its velocity to match the average velocity of its neighbors.
     *
     * @param i Index of the boid to perform alignment on.
     */
    protected void alignement(int i) {

            int numBoids = this.getPositions().length;
            Point2D.Float c = new Point2D.Float(0, 0);
            int count = 0;
            for (int j = 0 ; j < numBoids; j++) {
                if (this.isNeighbor(j, i) &&
                        this.getType().get(i).equals(this.getType().get(j))) {
                    c.setLocation(c.getX() + this.getVelocities()[j].getX(),
                            c.getY() + this.getVelocities()[j].getY());
                    count++;
                }
            }
            if (count > 0) {
                c.setLocation(c.getX() / count, c.getY() / count);
                float normC = (float) Math.sqrt(c.x * c.x + c.y * c.y);
                c.setLocation(c.getX() * maxSpeed / normC, c.getY() * maxSpeed / normC);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocities()[i].x, c.y - this.getVelocities()[i].y);
                limitForce(acceleration);
                this.acceleration[i].x += alignmentCoeff * acceleration.x;
                this.acceleration[i].y += alignmentCoeff * acceleration.y;
            }
    }
    /**
     * Updates the state of the boid, applying cohesion, separation, and alignment behaviors.
     *
     * @param width  Width of the simulation area.
     * @param height Height of the simulation area.
     */
    protected void update(int width, int height) {
        int numBoids = this.getPositions().length;
        for(int i = 0; i < numBoids; i++) {
            this.cohesion(i);
            this.separation(i);
            this.alignement(i);
            this.getVelocities()[i].x += this.acceleration[i].x;
            this.getVelocities()[i].y += this.acceleration[i].y;
            limitVelocity(this.getVelocities()[i]);
            this.getPositions()[i].x+= this.getVelocities()[i].x;
            this.getPositions()[i].y+= this.getVelocities()[i].y;
            this.acceleration[i].setLocation(0,0);
            this.boundedPosition(getPositions()[i],getVelocities()[i],width,height);
        }
    }

    /**
     * Reinitializes the position and velocity of each boid to its initial state. This can be used
     * to reset the simulation.
     */
    public void reInit(){

        Point2D.Float[] newPosition = new Point2D.Float[this.initLength];
        Point2D.Float[] newVelocity = new Point2D.Float[this.initLength];
        for (int i = 0; i < this.initLength; i++) {
            newPosition[i] = new Point2D.Float(this.initPositions[i].x, this.initPositions[i].y);
            newVelocity[i] = new Point2D.Float(this.initVelocities[i].x, this.initVelocities[i].y);
        }
        this.positions = newPosition;
        this.velocities = newVelocity;
    }
    /**
     * Limits the velocity of a boid to the maximum velocity.
     *
     * @param vel The velocity vector of the boid to be limited.
     */
    protected void limitVelocity(Point2D.Float vel) {
        float speed = (float) Math.sqrt(vel.x * vel.x + vel.y * vel.y);
        if (speed > maxSpeed) {
            vel.x = (vel.x / speed) * maxSpeed;
            vel.y = (vel.y / speed) * maxSpeed;
        }
    }
    /**
     * Limits the force applied to a boid to the maximum force.
     *
     * @param force The force vector to be limited.
     */
    protected void limitForce(Point2D.Float force){
        float m = (float) Math.sqrt(force.x * force.x + force.y * force.y);
        if(m >= maxForce){
            force.x = force.x * maxForce / m;
            force.y = force.y * maxForce / m;
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
    protected void boundedPosition(Point2D.Float pos,Point2D.Float vel, int width, int height) {
        if (pos.getX() < 0) {
            double dist = pos.getX() % width;
            int direc = (vel.x > 0) ? -1 : 1;
            vel.setLocation(-direc * vel.getX(), vel.getY());
            pos.setLocation(pos.getX() - direc * 2 * dist, pos.getY());
        } else if (pos.getX() > width) {
            double dist = pos.getX() % width;
            int direc = (vel.getX() > 0) ? -1 : 1;
            vel.setLocation(direc * vel.getX(), vel.getY());
            pos.setLocation(pos.getX()+direc * 2 * dist, pos.getY());
        }
        if (pos.getY() > height) {
            double dist = pos.getY() % height;
            int direc = (vel.getY() > 0)? -1 : 1;
            vel.setLocation(vel.getX(), direc * vel.getY());
            pos.setLocation(pos.getX(), pos.getY() + direc * 2 * dist);
        } else if (pos.getY() < 0) {
            double dist = pos.getY() % height;
            int direc = (vel.getY() > 0) ? -1 : 1;
            vel.setLocation(vel.getX(), -direc * vel.getY());
            pos.setLocation(pos.getX(), pos.getY() - direc * 2 * dist);

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
    private double angleBetween(Point2D.Float point1, Point2D.Float point2){
        double scalarProduct = point1.getX() * point2.getX() + point1.getY() * point2.getY();
        double point1Norm = Math.sqrt(Math.pow(point1.getX(), 2) + Math.pow(point1.getY(), 2));
        double point2Norm = Math.sqrt(Math.pow(point2.getX(), 2) + Math.pow(point2.getY(), 2));
        return Math.acos(scalarProduct / (point1Norm * point2Norm));

    }

    /**
     * Determines if another boid is considered a neighbor based on distance and field of view.
     *
     * @param j Index of the potential neighbor boid.
     * @param i Index of the current boid.
     * @return True if the boid is a neighbor, false otherwise.
     */
    private boolean isNeighbor(int j ,int i) {

        Point2D.Float p = new Point2D.Float(this.getPositions()[j].x - this.getPositions()[i].x, this.getPositions()[j].y - this.getPositions()[i].y);
        double d = Math.sqrt(p.x * p.x + p.y * p.y);
        if (d > 0 &&  d < distNeighbor) { // Check distance
            double angle = angleBetween(this.getVelocities()[i], p);
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

        for (int i = 0; i < this.positions.length; i++) {
            builder.append("\n  Boid ").append(i).append(": ")
                    .append("Position=(").append(positions[i].x).append(", ").append(positions[i].y).append("), ")
                    .append("Velocity=(").append(velocities[i].x).append(", ").append(velocities[i].y).append("), ");
        }

        return builder.toString();
    }
}