package software.design.teamorangecalsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class MainCalendar {

    // Private MainCalendar class fields
    private static ArrayList<MainCalendar> calendars;  //Holds all the calendars in the project
    private static HashMap<String, Class> calendarNames;  //keeps track of the type of calendar by calendar name


    // MainCalendar Object attributes
    private String calendarName;
    private ArrayList<Event> events;

    private boolean active;
    private int color;
    private int id;

    protected boolean locked;


    // Constructors
    private MainCalendar() {

        if (calendars == null) {
            fetchCalendars();
        }
        if (calendarNames == null) {
            //TODO: get calendarNames from local storage
            calendarNames = new HashMap<>();
        }
        calendars.add(this);  //adds newly created subclass calendars onto calendar list

    }
    protected MainCalendar(String name) {

        calendarName = name;
        if ( !calendarNames.containsKey(name) ) {
            calendarNames.put(name, this.getClass());
        }

        events = new ArrayList<Event>();
        id = name.hashCode();

    }


    // public getters
    public int getColor() {
        return color;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return calendarName;
    }
    public boolean isActive() {
        return active;
    }
    public boolean isLocked() { return locked; }

    //public setters
    public void setColor(int color) {
        this.color = color;
    }
    public void setName(String name) {
        calendarName = name;
    }
    public void setActive(boolean value) {
        active = value;
    }
    public boolean setLocked(boolean value) {
        locked = value;
        return true;
    }   //returns success in setting


    protected void uploadToDatabase() {
        //push the code to the database
    }



    //public class methods
    public static ArrayList<MainCalendar> getCalendars() {
        //get from database
        if (calendars == null) {
            fetchCalendars();
        }
        return calendars;
    }
    public static MainCalendar getCalendarByName(String name) {

        for(MainCalendar cal : calendars) {
            if (cal.getName().equals(name)) {
                return cal;
            }
        }
        return null;    //returns null if not found

    }



    //private class helper methods
    private static void fetchCalendars() {
        if (calendars == null) {
            synchronized (MainCalendar.class) {   //thread safe synchronization
                if (calendars == null) {
                    calendars = new ArrayList<>();
                    //TODO: Replace with pull from database if arraylist is null. This would take
                    calendars.addAll(fetchCalendarsFromDatabase());
                }
            }
        }
    }

    //optionally implement this differently, or implement fetchEvents from database
    private static List<MainCalendar> fetchCalendarsFromDatabase() {
        return organizeEventsIntoCalendars( fetchEventsFromDatabase() );
    }
    //implement
    private static List<Event> fetchEventsFromDatabase() {
        List<Event> eventsFromDatabase = new ArrayList<>(); //doesn't have to be arraylist, just a list

        //TODO: Add code for fetching the events and add them to the list of elements
        //TODO: Create events by passing it the properties and

        return eventsFromDatabase;
    }

    //implement where assignments extend events, and this is returned
    private static List<Event> fetchEventsFromCanvas() {
        return null;
    }


    private static List<MainCalendar> organizeEventsIntoCalendars(List<Event> evnetsToOrganize) {
        ArrayList<MainCalendar> calendarList = new ArrayList<>();
        //TODO: Get the event. Assuming the event contains the name of the calendar of origin,
        //TODO:   get the name of the name of the calendar and search in the knownCalendars for the
        //TODO:   class associated with that calendar. Use this class to get instances of the
        //TODO:   calendars using reflection


        return calendarList;
    }
}
