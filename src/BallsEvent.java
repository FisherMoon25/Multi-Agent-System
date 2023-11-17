import gui.GUISimulator;

/**
 * Represents an event in an event-driven system for managing the motion of balls in a GUI.
 */
public class BallsEvent extends Event {
    private final Balls balls;
    private final EventManager eventManager;
    private final GUISimulator gui;

    /**
     * Constructs a BallsEvent with the specified parameters.
     *
     * @param date          The scheduled date of the event.
     * @param balls         The Balls object to be manipulated.
     * @param eventManager  The EventManager to handle event scheduling.
     * @param gui           The GUISimulator where the balls are displayed.
     */
    public BallsEvent(long date, Balls balls, EventManager eventManager, GUISimulator gui) {
        super(date);
        this.balls = balls;
        this.eventManager = eventManager;
        this.gui = gui;
    }

    /**
     * Executes the event: moves the balls and schedules the next move.
     * This method translates the balls in the GUI and then schedules a new BallsEvent
     * for the next time step.
     */
    @Override
    public void execute() {
        // Get current dimensions of the GUI window
        int windowWidth = gui.getWidth();
        int windowHeight = gui.getHeight();

        // Translate the balls within the window boundaries
        balls.translate(30, 30, windowWidth, windowHeight);

        // Schedule the next event for continuous movement
        eventManager.addEvent(new BallsEvent(this.getDate() + 1, balls, eventManager, gui));
    }
}
