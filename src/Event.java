/**
 * Abstract base class for events in an event-driven system.
 * Each event has a scheduled date, and events can be compared based on these dates.
 * Subclasses must implement the execute method to define the event's behavior.
 */
public abstract class Event implements Comparable<Event> {
    private long date;

    /**
     * Constructs an Event with the specified date.
     *
     * @param date The scheduled date of the event.
     */
    public Event(long date) {
        this.date = date;
    }

    /**
     * Returns the scheduled date of the event.
     *
     * @return The date of the event.
     */
    public long getDate() {
        return this.date;
    }

    /**
     * Compares this event with another event for order based on the event date.
     *
     * @param e The event to be compared.
     * @return A negative integer, zero, or a positive integer as this event is less than,
     *         equal to, or greater than the specified event.
     */
    @Override
    public int compareTo(Event e) {
        return Long.compare(this.date, e.getDate());
    }

    /**
     * Executes the specific action of the event.
     * This method must be implemented by subclasses to define the event's behavior.
     */
    public abstract void execute();
}
