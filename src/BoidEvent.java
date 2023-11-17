import gui.GUISimulator;

public class BoidEvent extends Event {

    private Boid boid;
    private GUISimulator gui;
    private EventManager eventManager;


    public BoidEvent(long date, Boid boid, GUISimulator gui, EventManager eventManager) {
        super(date);
        this.boid = boid;
        this.gui = gui;
        this.eventManager = eventManager;
    }

    @Override
    public void execute() {
        this.boid.update(gui.getPanelWidth(), gui.getPanelHeight());
    }



}
