import java.awt.geom.Point2D;
/**
 * The Prey class represents prey boids in a predator-prey simulation. It inherits basic boid behaviors
 * such as cohesion, separation, and alignment from the Boid class and adds a behavior to avoid predators.
 */
public class Prey extends Boid {
    /**
     * Constructs a Prey instance with specified parameters.
     *
     * @param position    The initial positions of the prey boids.
     * @param velocity    The initial velocities of the prey boids.
     * @param vlim        The maximum velocity limit for each prey boid.
     * @param maxForce    The maximum force limit for each prey boid.
     * @param fieldOfView The field of view angle for each prey boid.
     * @param step        The simulation step size.
     */
    public Prey(Point2D.Float[] position, Point2D.Float[] velocity,float vlim,float maxForce, float fieldOfView,long step,int diameter,float cohesionCoeff,float alignmentCoeff,float seperationCoeff){
        super( position, velocity,vlim,maxForce, fieldOfView,step,diameter,cohesionCoeff,alignmentCoeff,seperationCoeff);
    }

    /**
     * Performs an avoidance behavior for a single prey boid to evade predators.
     *
     * @param i         Index of the prey boid to apply avoidance behavior.
     * @param predators The instance of the Predator class representing the predators in the simulation.
     */
    private void avoid(int i,Predator predators) {
            int numPredators = predators.getPosition().length;

            Point2D.Float c = new Point2D.Float(0, 0);
            int count = 0;

            for (int j = 0; j < numPredators; j++) {
                float distX = this.getPosition()[i].x - predators.getPosition()[j].x;
                float distY = this.getPosition()[i].y - predators.getPosition()[j].y;
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
    public void removePrey(int index) {
        if (index < 0 || index >= this.position.length) {
            return; // Index out of bounds, do nothing
        }

        Point2D.Float[] newPosition = new Point2D.Float[this.position.length - 1];
        Point2D.Float[] newVelocity = new Point2D.Float[this.position.length - 1];
        // You might need to do the same for other properties like acceleration, etc.

        for (int i = 0, j = 0; i < this.position.length; i++) {
            if (i != index) {
                newPosition[j] = this.position[i];
                newVelocity[j] = this.velocity[i];
                j++;
            }
        }

        this.position = newPosition;
        this.velocity = newVelocity;
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


        int numBoids =this.getPosition().length;
        for(int i=0;i<numBoids;i++){
            this.cohesion(i);
            this.separation(i);
            this.alignement(i);
            this.avoid(i,predators);
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
     * Provides a string representation of the Prey object.
     * This representation includes details about each prey boid's position and velocity.
     *
     * The string starts with a header "Prey Boid:"
     * Each boid's details are listed in a formatted manner.
     *
     * @return A string representation of the Prey object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Prey Boid: \n");

        for (int i = 0; i < this.position.length; i++) {
            builder.append("  Prey ").append(i).append(": ")
                    .append("Position=(").append(position[i].x).append(", ").append(position[i].y).append("), ")
                    .append("Velocity=(").append(velocity[i].x).append(", ").append(velocity[i].y).append("), ");

        }

        return builder.toString();
    }
}