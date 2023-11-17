import java.util.PriorityQueue;

/**
 * Manages a queue of events in an event-driven system.
 * Events are prioritized and executed based on their scheduled dates.
 */
public class EventManager {
        private long currentDate = 0;
        private final PriorityQueue<Event> events;

        /**
         * Constructs an EventManager with an empty queue of events.
         */
        public EventManager() {
                this.events = new PriorityQueue<>();
        }

        /**
         * Checks if all events have been processed.
         *
         * @return true if there are no more events to process, false otherwise.
         */
        public boolean isFinished() {
                return events.isEmpty();
        }

        /**
         * Adds an event to the queue.
         *
         * @param e The event to be added.
         */
        public void addEvent(Event e) {
                this.events.add(e);
        }

        /**
         * Processes the next event if it is scheduled for the current date or earlier.
         * Increments the current date after processing.
         */
        public void next() {
                if (!events.isEmpty() && events.peek().getDate() <= currentDate) {
                        Event e = events.poll();
                    assert e != null;
                    e.execute();
                }
                currentDate++;
        }

        /**
         * Resets the event manager to its initial state.
         * This method clears all scheduled events and resets the current date.
         */
        public void restart() {
                this.currentDate = 0;
                this.events.clear();
        }

        /**
         * Gets the current date in the event manager.
         *
         * @return The current date.
         */
        public long getCurrentDate() {
                return this.currentDate;
        }
}
