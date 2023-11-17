import gui.GUISimulator;

/**
 * Represents an event in a simulation involving boids. This event is responsible
 * for updating the state of a single boid and scheduling the next update.
 * Inherits from the Event class.
 */
public class BoidEvent extends Event {

    private final Boid boids; // The boid to be updated
    private final GUISimulator gui; // GUI simulator for visualization
    private final EventManager eventManager; // Manages scheduling of events

    /**
     * Constructs a BoidEvent with the specified parameters.
     *
     * @param date The scheduled date (time step) for the event.
     * @param boids The boid that will be updated by this event.
     * @param gui  The GUISimulator object used for visualization.
     * @param eventManager The EventManager responsible for managing events.
     */
    public BoidEvent(long date, Boid boids, GUISimulator gui, EventManager eventManager) {
        super(date); // Initialize the parent Event class with the date
        this.boids = boids; // Assign the boid to be updated
        this.gui = gui; // Set the GUI simulator for visualization
        this.eventManager = eventManager; // Set the event manager for event handling
    }

    /**
     * Executes the event's action. This method updates the state of the boid
     * and schedules the next update event.
     * This method is called automatically when the event is processed by the event manager.
     */
    @Override
    public void execute() {
        // Update the boid's position and velocity based on the current state
        this.boids.update(gui.getPanelWidth(), gui.getPanelHeight());

    }
}
