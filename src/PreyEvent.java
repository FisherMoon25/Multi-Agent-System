import gui.GUISimulator;

/**
 * Represents an event in an event-driven simulation involving prey and predators.
 * This event manages the update of prey behaviors based on the presence and state of predators.
 */
public class PreyEvent extends Event {

    private final Prey preys;
    private final Predator predators;
    private final GUISimulator gui;
    private final EventManager eventManager;

    /**
     * Constructs a PreyEvent with the specified parameters.
     *
     * @param date         The scheduled date of the event.
     * @param preys        The prey objects involved in the simulation.
     * @param predators    The predator objects involved in the simulation.
     * @param gui          The GUISimulator for visualization.
     * @param eventManager The EventManager to handle event scheduling.
     */
    public PreyEvent(long date, Prey preys, Predator predators, GUISimulator gui, EventManager eventManager) {
        super(date);
        this.preys = preys;
        this.predators = predators;
        this.gui = gui;
        this.eventManager = eventManager;
    }

    /**
     * Executes the event: updates prey behaviors based on the presence of predators.
     * This method updates the state of the prey in the simulation, typically involving
     * movement or other avoidance strategies in response to predators.
     */
    @Override
    public void execute() {
        // Update the prey based on the current state of the predators and GUI dimensions
        this.preys.update(predators, gui.getPanelWidth(), gui.getPanelHeight());
    }
}
