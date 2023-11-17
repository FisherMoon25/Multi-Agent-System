import gui.GUISimulator;
import gui.Triangle;
import gui.Simulable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

/**
 * MultipleBoidSimulation class for simulating the behavior of multiple types of boids.
 * Each type of boid can have different behaviors or characteristics. This class handles
 * the visualization and updating of these boids in a GUI environment.
 */
public class MultipleBoidSimulation implements Simulable {
    private final Boid boids; // The boids to be simulated
    private final GUISimulator gui; // GUI simulator for visualization
    private final EventManager eventManager; // Manages scheduling of events
    private final int numTypes; // The number of different boid types in the simulation

    /**
     * Constructs a MultipleBoidSimulation with the specified parameters.
     *
     * @param position Initial positions of the boids.
     * @param velocity Initial velocities of the boids.
     * @param maxSpeed Maximum speed of the boids.
     * @param maxForce Maximum force that can be applied to the boids.
     * @param fieldOfView Field of view angle for the boids.
     * @param step Simulation step size.
     * @param gui The GUI simulator for visualization.
     * @param diameter Diameter of each boid for visualization.
     * @param cohesionCoeff Coefficient for cohesion behavior.
     * @param alignmentCoeff Coefficient for alignment behavior.
     * @param seperationCoeff Coefficient for separation behavior.
     * @param type List of integers representing the type of each boid.
     * @param numTypes Number of different boid types.
     */
    public MultipleBoidSimulation(Point2D.Float[] position, Point2D.Float[] velocity, float maxSpeed, float maxForce, float fieldOfView, int step, GUISimulator gui,
                                  int diameter, float cohesionCoeff, float alignmentCoeff, float seperationCoeff, List<Integer> type, int numTypes){

        this.boids = new Boid(position, velocity, maxSpeed, maxForce, fieldOfView, step,
                diameter, cohesionCoeff, alignmentCoeff, seperationCoeff, type);
        this.gui = gui;
        this.numTypes = numTypes;
        this.eventManager = new EventManager();
        this.draw();
    }

    /**
     * Advances the simulation to the next step.
     * Processes the next event in the event manager and updates the GUI.
     */
    @Override
    public void next() {
        this.eventManager.next();
        this.eventManager.addEvent(new BoidEvent(this.eventManager.getCurrentDate() + boids.getStep(), boids, gui, eventManager));
        this.gui.reset();
        this.draw();
    }

    /**
     * Restarts the simulation.
     * Resets the boids to their initial states and redraws them.
     */
    @Override
    public void restart() {
        boids.reInit();
        this.eventManager.restart();
        this.eventManager.addEvent(new BoidEvent(this.eventManager.getCurrentDate() + boids.getStep(), boids, gui, eventManager));
        this.draw();
    }

    /**
     * Converts an integer representing a boid type to a corresponding color.
     *
     * @param i The integer representing the boid type.
     * @return The color corresponding to the boid type.
     */
    private Color intToColor(int i) {
        float hue = (float) i / this.numTypes;
        return Color.getHSBColor(hue, 1.0f, 1.0f);
    }

    /**
     * Draws the boids on the GUI.
     * Each boid is represented as a triangle, with color indicating its type.
     */
    public void draw() {
        gui.reset();
        int numBoids = this.boids.getPositions().length;
        for (int i = 0; i < numBoids; i++) {
            int x = (int) this.boids.getPositions()[i].x;
            int y = (int) this.boids.getPositions()[i].y;
            Triangle triangle = new Triangle(x, y, Color.BLACK, intToColor(this.boids.getType().get(i)), 12, 16, this.boids.getVelocities()[i]);
            gui.addGraphicalElement(triangle);
        }
    }
}
