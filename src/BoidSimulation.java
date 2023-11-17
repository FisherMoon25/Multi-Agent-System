import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;
import gui.Triangle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * BoidSimulation class for simulating the behavior of a flock of boids using an event-driven approach.
 * Implements the Simulable interface for integration with a GUI simulator.
 */

public class BoidSimulation implements Simulable {


    private final Boid boids;
    private final GUISimulator gui;
    private final EventManager eventManager;
    
    /**
     * Constructs a BoidSimulation with initial positions and velocities for the boids,
     * a GUI for visualization, and the diameter for each boid representation.
     *
     * @param position Initial positions of the boids.
     * @param velocity Initial velocities of the boids.
     * @param gui      The GUISimulator for visualization.
     * @param diameter Diameter of each boid for visualization.
     */
    public BoidSimulation(Point2D.Float[] position, Point2D.Float[] velocity, float maxSpeed, float maxForce, float fieldOfView,
                          int step, GUISimulator gui, int diameter, float cohesionCoeff, float alignmentCoeff, float seperationCoeff) {
        this.boids = new Boid(position, velocity, maxSpeed, maxForce, fieldOfView,
                step, diameter, cohesionCoeff, alignmentCoeff, seperationCoeff);
        this.gui = gui;
        this.eventManager = new EventManager();
        this.draw();
    }

    /**
     * Advances the simulation to the next step using an event-driven approach.
     * Processes the next event in the event manager, updates the GUI, and schedules the next update event.
     */
    @Override
    public void next() {
        this.eventManager.next();
        this.eventManager.addEvent(new BoidEvent(this.eventManager.getCurrentDate() + this.boids.getStep(), boids, gui, eventManager));
        this.gui.reset();
        this.draw();
    }
    /**
     * Restarts the simulation by resetting the boids to their initial positions and velocities.
     */
    @Override
    public void restart() {
        boids.reInit();
        this.eventManager.restart();
        this.eventManager.addEvent(new BoidEvent(this.eventManager.getCurrentDate() + boids.getStep(), boids, gui, eventManager));
        this.draw();

    }
    /**
     * Draws the boids on the GUI.
     * Each boid is represented as triangle .
     */
    public void draw() {
        gui.reset();
        int numPreys= this.boids.getPositions().length;
        for (int i = 0; i < numPreys; i++) {
            int x = (int) this.boids.getPositions()[i].x;
            int y = (int) this.boids.getPositions()[i].y;
            Triangle triangle = new Triangle(x, y, Color.BLACK, Color.BLUE,6,8, this.boids.getVelocities()[i]);
            gui.addGraphicalElement(triangle);
        }
    }
}