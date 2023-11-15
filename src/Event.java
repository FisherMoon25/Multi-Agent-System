public abstract class Event implements Comparable<Event>{
    private long date;

    public Event(long date){
        this.date = date;
    }

    public long getDate(){
        return this.date;
    }
    public int compareTo(Event e){
        return Long.compare(this.date,e.getDate());
    }
    public abstract void execute();


}

