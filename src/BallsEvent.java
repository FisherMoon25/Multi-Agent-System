public class BallsEvent extends Event{
    private Balls balls;
    private EventManager eventManager;

    public BallsEvent(long date,Balls balls,EventManager eventManager){
         super(date);
         this.balls= balls;
         this.eventManager = eventManager;
    }
    public void execute(){
         balls.translate(30,30);
         // cest dans next§§§§§
         eventManager.addEvent(new BallsEvent(this.getDate()+1,balls,eventManager));
    }
}
