import java.awt.geom.Point2D;
/**
 * The Predator class represents predator boids in a predator-prey simulation.
 * It inherits basic boid behaviors such as cohesion, separation, and alignment from the Boid class
 * and adds a behavior to chase prey.
 */
public class Predator extends Boid {
    /**
     * Constructs a Predator instance with specified parameters.
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
    public Predator(Point2D.Float[] position, Point2D.Float[] velocity, float maxSpeed, float maxForce, float fieldOfView, long step,
                    int diameter, float cohesionCoeff, float alignmentCoeff, float seperationCoeff) {
        super (position, velocity, maxSpeed, maxForce, fieldOfView, step,
                diameter, cohesionCoeff, alignmentCoeff, seperationCoeff);
    }

    /**
     * Performs a chasing behavior for a single predator boid, targeting the closest prey.
     *
     * @param i     Index of the predator boid to apply chasing behavior.
     * @param preys The instance of the Prey class representing the prey in the simulation.
     */
    private void chase(int i ,Prey preys) {
        int numPreys = preys.getPositions().length;
        Point2D.Float c = new Point2D.Float(0, 0);
        float closestDist = Float.MAX_VALUE;
        for (int j = 0; j < numPreys; j++) {
            float distX = this.getPositions()[i].x - preys.getPositions()[j].x;
            float distY = this.getPositions()[i].y - preys.getPositions()[j].y;
            double distance = Math.sqrt(distX * distX + distY * distY);
            if (distance < closestDist) {
                closestDist = (float)distance;
                c.setLocation(-distX / distance, -distY / distance); // Direction to closest prey
            }
        }
        float normC = (float) Math.sqrt(c.x * c.x + c.y * c.y);
        if (normC > 0) {
                    c.setLocation(c.x / normC, c.y / normC); // Normalize the result
        }
        // We change the scale of c, by multiplying by a coefficient == 7
        Point2D.Float acceleration = new Point2D.Float((c.x * 7 - this.getVelocities()[i].x), (c.y * 7 - this.getVelocities()[i].y));
        limitForce(acceleration);
        this.acceleration[i].x += acceleration.x;
        this.acceleration[i].y += acceleration.y;
    }
    /**
     * Checks if any prey is caught by the predator and removes it from the simulation.
     * A prey is considered caught if it is within a certain distance from the predator.
     *
     * @param predatorIndex The index of the predator in the predator array.
     * @param preys         The instance of the Prey class representing all prey in the simulation.
     */
    private void checkAndRemoveCaughtPrey(int predatorIndex, Prey preys) {
        float catchingDistance = 5.0f; // Define a small distance for catching
        Point2D.Float predatorPosition = this.getPositions()[predatorIndex];

        for (int j = 0; j < preys.getPositions().length; j++) {
            Point2D.Float preyPosition = preys.getPositions()[j];
            float distance = (float) predatorPosition.distance(preyPosition);
            if (distance <= catchingDistance) {
                // Remove the caught prey from the simulation
                preys.removePrey(j);
                break; // Assuming one catch per update cycle
            }
        }
    }

    /**
     * Updates the state of each predator boid in the simulation by applying the behavior forces,
     * updating the velocity and position, and ensuring the predators chase prey.
     *
     * @param preys  The instance of the Prey class representing the prey in the simulation.
     * @param width  Width of the simulation area.
     * @param height Height of the simulation area.
     */
    public void update(Prey preys,int width,int height) {
        int numBoids =this.getPositions().length;
        for(int i=0; i < numBoids; i++){
            this.cohesion(i);
            this.separation(i);
            this.alignement(i);
            this.chase(i,preys);
            this.getVelocities()[i].x += this.acceleration[i].x;
            this.getVelocities()[i].y += this.acceleration[i].y;
            limitVelocity(this.getVelocities()[i]);
            this.getPositions()[i].x+= this.getVelocities()[i].x;
            this.getPositions()[i].y+= this.getVelocities()[i].y;
            this.acceleration[i].setLocation(0,0);
            this.boundedPosition(getPositions()[i],getVelocities()[i],width,height);

            checkAndRemoveCaughtPrey(i, preys);
        }
    }
    /**
     * Provides a string representation of the Boid object.
     * This representation includes details about each predator boid's position and velocity .

     * The string starts with a header "Boid:"
     * Each boid's details are listed in a formatted manner.
     *
     * @return A string representation of the Boid object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Predator Boid: ");

        for (int i = 0; i < this.positions.length; i++) {
            builder.append("\n  Predator ").append(i).append(": ")
                    .append("Position=(").append(positions[i].x).append(", ").append(positions[i].y).append("), ")
                    .append("Velocity=(").append(velocities[i].x).append(", ").append(velocities[i].y).append("), ");

        }

        return builder.toString();
    }


}
