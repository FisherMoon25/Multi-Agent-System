import gui.GUISimulator;

public class BallsEvent extends Event {
    private final Balls balls;

    private final EventManager eventManager;

    private final GUISimulator gui;

    public BallsEvent(long date,Balls balls,EventManager eventManager, GUISimulator gui) {
         super(date);
         this.balls = balls;
         this.eventManager = eventManager;
         this.gui = gui;
    }
    public void execute() {
        int windowWidth = gui.getWidth();
        int windowHeight = gui.getHeight();
        balls.translate(30,30, windowWidth, windowHeight);
        eventManager.addEvent(new BallsEvent(this.getDate() + 1, balls, eventManager, gui));
    }
}
