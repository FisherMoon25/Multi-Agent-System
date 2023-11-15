import java.util.PriorityQueue;

public class EventManager {
        private long currentDate = 0;

        private PriorityQueue<Event> events;

        public EventManager(){
                this.events = new PriorityQueue<Event>();
        }
        public boolean isFinished(){
                return events.isEmpty();
        }
        public void addEvent(Event e){
                this.events.add(e);
        }
        public void next(){
              if (!events.isEmpty() && events.peek().getDate()<=currentDate){
                      Event e =events.poll();
                      e.execute();
              }
              currentDate++;
        }

        public void restart(){
                // restart means starting all over again ?????
                this.currentDate = 0;
                this.events.clear();
        }
        public long getCurrentDate(){
                return this.currentDate;
        }

}
