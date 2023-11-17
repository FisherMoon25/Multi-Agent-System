import java.awt.geom.Point2D;
/**
 * The Prey class represents prey boids in a predator-prey simulation. It inherits basic boid behaviors
 * such as cohesion, separation, and alignment from the Boid class and adds a behavior to avoid predators.
 */
public class Prey extends Boid {
    /**
     * Constructs a Prey instance with specified parameters.
     * @param position Initial positions of the boids.
     * @param velocity Initial velocities of the boids.
     * @param maxSpeed Maximum speed of the boids.
     * @param maxForce Maximum force that can be applied to the boids.
     * @param fieldOfView Field of view angle for the boids.
     * @param step Simulation step size.
     * @param diameter Diameter of each boid for visualization.
     * @param cohesionCoeff Coefficient for cohesion behavior.
     * @param alignmentCoeff Coefficient for alignment behavior.
     * @param seperationCoeff Coefficient for separation behavior.
     */
    public Prey(Point2D.Float[] position, Point2D.Float[] velocity, float maxSpeed, float maxForce, float fieldOfView, long step,
                int diameter, float cohesionCoeff, float alignmentCoeff, float seperationCoeff){
        super(position, velocity, maxSpeed, maxForce, fieldOfView, step, diameter, cohesionCoeff, alignmentCoeff, seperationCoeff);
    }

    /**
     * Performs an avoidance behavior for a single prey boid to evade predators.
     *
     * @param i         Index of the prey boid to apply avoidance behavior.
     * @param predators The instance of the Predator class representing the predators in the simulation.
     */
    private void avoid(int i,Predator predators) {
            int numPredators = predators.getPositions().length;
            Point2D.Float c = new Point2D.Float(0, 0);
            int count = 0;
            for (int j = 0; j < numPredators; j++) {
                float distX = this.getPositions()[i].x - predators.getPositions()[j].x;
                float distY = this.getPositions()[i].y - predators.getPositions()[j].y;
                double distance = Math.sqrt(distX * distX + distY * distY);
                if (distance > 0 && distance < distSeparation + 20) {
                    c.setLocation(c.x + distX / distance, c.y + distY / distance); // Normalize and accumulate
                    count++;
                }
            }
            if (count > 0) {
                float normC = (float) Math.sqrt(c.x * c.x + c.y * c.y);
                if (normC > 0) {
                    c.setLocation(c.x / normC, c.y / normC); // Normalize the result
                }
                //Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                limitForce(c);
                this.acceleration[i].x += c.x;
                this.acceleration[i].y += c.y;
            }
    }
    /**
     * Removes a prey boid from the simulation. This is typically called when a prey is caught
     * by a predator. It updates the array of prey positions and velocities by removing the
     * specified prey.
     *
     * @param index The index of the prey boid to be removed from the simulation.
     */
    public void removePrey(int index) {
        if (index < 0 || index >= this.positions.length) {
            return; // Index out of bounds, do nothing
        }
        Point2D.Float[] newPositions = new Point2D.Float[this.positions.length - 1];
        Point2D.Float[] newVelocities = new Point2D.Float[this.positions.length - 1];
        // You might need to do the same for other properties like acceleration, etc.
        for (int i = 0, j = 0; i < this.positions.length; i++) {
            if (i != index) {
                newPositions[j] = this.positions[i];
                newVelocities[j] = this.velocities[i];
                j++;
            }
        }
        this.positions = newPositions;
        this.velocities = newVelocities;
    }


    /**
     * Updates the state of each prey boid in the simulation by applying the behavior forces,
     * updating the velocity and position, and ensuring the prey avoid predators.
     *
     * @param predators The instance of the Predator class representing the predators in the simulation.
     * @param width     Width of the simulation area.
     * @param height    Height of the simulation area.
     */
    public void update(Predator predators,int width,int height){


        int numBoids = this.getPositions().length;
        for(int i = 0; i < numBoids; i++) {
            this.cohesion(i);
            this.separation(i);
            this.alignement(i);
            this.avoid(i,predators);
            this.getVelocities()[i].x += this.acceleration[i].x;
            this.getVelocities()[i].y += this.acceleration[i].y;
            limitVelocity(this.getVelocities()[i]);
            this.getPositions()[i].x += this.getVelocities()[i].x;
            this.getPositions()[i].y += this.getVelocities()[i].y;
            this.acceleration[i].setLocation(0,0);
            this.boundedPosition(getPositions()[i],getVelocities()[i],width,height);
        }
    }
    /**
     * Provides a string representation of the Prey object.
     * This representation includes details about each prey boid's position and velocity.
     * The string starts with a header "Prey Boid:"
     * Each boid's details are listed in a formatted manner.
     *
     * @return A string representation of the Prey object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Prey Boid: \n");

        for (int i = 0; i < this.positions.length; i++) {
            builder.append("  Prey ").append(i).append(": ")
                    .append("Position=(").append(positions[i].x).append(", ").append(positions[i].y).append("), ")
                    .append("Velocity=(").append(velocities[i].x).append(", ").append(velocities[i].y).append("), ");

        }

        return builder.toString();
    }
}