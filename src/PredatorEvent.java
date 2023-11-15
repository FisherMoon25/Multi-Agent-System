import gui.GUISimulator;

public class PredatorEvent extends Event {
    private Prey preys;
    private Predator predators;
    private GUISimulator gui;
    private EventManager eventManager;

    public PredatorEvent(long date , Prey preys, Predator predators,GUISimulator gui, EventManager eventManager){
        super(date);
        this.preys = preys;
        this.predators=predators;
        this.gui=gui;
        this.eventManager = eventManager;
    }
    public  void execute(){
        this.predators.update(preys,gui.getPanelWidth(),gui.getPanelHeight());

    }
}
