package software.design.teamorangecalsync;

import java.util.ArrayList;

public abstract class Calendar {

    private static ArrayList<Calendar> calendars;

    protected String name;
    protected ArrayList<Event> events;

    protected Calendar() {
        if (calendars == null) {
            //will initialize if null
            fetchCalendars();
        }
        calendars.add(this);
    }
    protected Calendar(String _name) {
        this();
        name = _name;
        events = new ArrayList<Event>();
    }

    protected void addEvent(Event newEvent) {
        events.add(newEvent);
    }
    protected void deleteEvent(Event event) {
        events.remove(event);
    }
    protected void storeToDatabase() {
        //TODO: push the code to the database
    }

    protected static ArrayList<Calendar> getCalendars() {
        //get from database
        if (calendars == null) {
            fetchCalendars();
        }
        return calendars;
    }

    private static void fetchCalendars() {
        if (calendars == null) {
            synchronized (Calendar.class) {   //thread safe synchronization
                if (calendars == null) {
                    //TODO: Replace with pull from database if arraylist is null. This would take
                    //TODO: care of the updating we need
                    calendars = new ArrayList<>();
                    //add 10 calendars
                    for (int i = 0; i < 10; i++) {
                        calendars.add(new CalSyncCalendar("CalSyncCalendar" + i));
                    }
                }
            }
        }
    }
}
