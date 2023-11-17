import gui.GUISimulator;

/**
 * Represents an event in an event-driven simulation involving predators and prey.
 * This event manages the update of predator behaviors based on the state of the prey.
 */
public class PredatorEvent extends Event {
    private Prey preys;
    private Predator predators;
    private GUISimulator gui;
    private EventManager eventManager;

    /**
     * Constructs a PredatorEvent with the specified parameters.
     *
     * @param date         The scheduled date of the event.
     * @param preys        The prey objects involved in the simulation.
     * @param predators    The predator objects involved in the simulation.
     * @param gui          The GUISimulator for visualization.
     * @param eventManager The EventManager to handle event scheduling.
     */
    public PredatorEvent(long date, Prey preys, Predator predators, GUISimulator gui, EventManager eventManager) {
        super(date);
        this.preys = preys;
        this.predators = predators;
        this.gui = gui;
        this.eventManager = eventManager;
    }

    /**
     * Executes the event: updates predator behaviors based on the state of the prey.
     * The method updates the state of the predators in the simulation, typically involving
     * movement or other interactions with the prey.
     */
    @Override
    public void execute() {
        // Update the predators based on the current state of the prey and GUI dimensions
        this.predators.update(preys, gui.getPanelWidth(), gui.getPanelHeight());
    }
}
