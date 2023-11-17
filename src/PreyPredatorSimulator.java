import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

/**
 * A simulator for a prey-predator model, implementing the Simulable interface.
 * This class manages the visualization and updating of the prey and predator entities
 * in a GUI using an event-driven approach.
 */
public class PreyPredatorSimulator implements Simulable {
    private Prey preys;
    private Predator predators;
    private GUISimulator gui;
    private EventManager eventManager;

    /**
     * Constructs a PreyPredatorSimulator with the specified prey, predators, and GUI.
     *
     * @param preys    The prey entities in the simulation.
     * @param predators The predator entities in the simulation.
     * @param gui      The GUISimulator for visualization.
     */
    public PreyPredatorSimulator(Prey preys, Predator predators, GUISimulator gui) {
        this.preys = preys;
        this.predators = predators;
        this.gui = gui;
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
        this.eventManager.addEvent(new PreyEvent(this.eventManager.getCurrentDate() + predators.getStep(), preys, predators, gui, eventManager));
        this.eventManager.addEvent(new PredatorEvent(this.eventManager.getCurrentDate() + predators.getStep(), preys, predators, gui, eventManager));
        this.gui.reset();
        this.draw();
    }

    /**
     * Restarts the simulation.
     */
    @Override
    public void restart() {
        this.preys.reInit();
        this.predators.reInit();
        this.eventManager.restart();
        this.eventManager.addEvent(new PreyEvent(this.eventManager.getCurrentDate() + predators.getStep(), preys, predators, gui, eventManager));
        this.eventManager.addEvent(new PredatorEvent(this.eventManager.getCurrentDate() + predators.getStep(), preys, predators, gui, eventManager));
        this.gui.reset();
        this.draw();
    }

    /**
     * Draws the prey and predator entities on the GUI.
     * Each entity is represented as a tirangle.
     */
    private void draw() {
        // Draw prey
        int numPreys = this.preys.getPosition().length;
        for (int i = 0; i < numPreys; i++) {
            int x = (int) this.preys.getPosition()[i].x;
            int y = (int) this.preys.getPosition()[i].y;
            gui.addGraphicalElement(new Oval(x, y, Color.BLUE, Color.BLUE, this.preys.getDiam()));
        }

        // Draw predators
        int numPredators = this.predators.getPosition().length;
        for (int j = 0; j < numPredators; j++) {
            int x = (int) this.predators.getPosition()[j].x;
            int y = (int) this.predators.getPosition()[j].y;
            gui.addGraphicalElement(new Oval(x, y, Color.GRAY, Color.GRAY, this.predators.getDiam()));
        }
    }
}
