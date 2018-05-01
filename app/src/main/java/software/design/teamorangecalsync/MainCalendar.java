package software.design.teamorangecalsync;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public abstract class MainCalendar {

    // Private MainCalendar class fields
    private static ArrayList<MainCalendar> calendars;  //Holds all the calendars in the project
    private static HashMap<String, Class> calendarNames;  //keeps track of the type of calendar by calendar name


    // MainCalendar Object attributes
    private String calendarName;
    private HashMap<Date, ArrayList<Event>> events;

    private boolean active;
    private int color;
    private int id;

    protected boolean locked;


    // Constructors
    private MainCalendar() {

        if (calendars == null) {
            calendars = new ArrayList<>();
            fetchCalendars();   //add calendars to the arraylist
        }
        if (calendarNames == null) {
            //TODO: get calendarNames from local storage
            calendarNames = new HashMap<>();
        }
        calendars.add(this);  //adds newly created subclass calendars onto calendar list

    }
    protected MainCalendar(String name) {

        this();
        calendarName = name;
        if ( !calendarNames.containsKey(name) ) {
            calendarNames.put(name, this.getClass());
        }

        events = new HashMap<>();
        id = name.hashCode();

    }


    //public functions
    public boolean addEvent(Date date, Event event) {
        if(!events.containsKey(date)) {
            events.put(date, new ArrayList<Event>());
        }
        events.get(date).add(event);
        return true;
    }
    public void uploadToDatabase() {
        //push the code to the database
    }


    // public getters
    public int getColor() {
        return color;
    }
    public HashMap<Date, ArrayList<Event>> getEvents() {
        return events;
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



    //public class methods
    public static ArrayList<MainCalendar> getCalendars() {
        //get from database
        //TODO: comment out this test after proper implementation
        setupTestCalendarList();
        //TODO: uncomment these after proper implementation
        //if (calendars == null) {
        //    fetchCalendars();
        //}
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


    //Makes test calendar list for implementing the front end stuff
    private static void setupTestCalendarList() {
        calendars = new ArrayList<MainCalendar>();

        for(int i = 0; i < 3; i++) {
            //create 3 calendars
            CalSyncCalendar cSCal = new CalSyncCalendar("TestCalendar" + i);
            for(int j = i; j < 3; j++) {
                //add 3 events on the first, 2 on the second, 1 on the third
                Date date = new Date(2018, 4, 27 + i);
                cSCal.addEvent(date, new Event("RandomEvent" + j));
            }
        }

    }


    //private class helper methods
    private static void fetchCalendars() {
        if (calendars == null) {
            synchronized (MainCalendar.class) {   //thread safe synchronization
                if (calendars == null) {
                    calendars = new ArrayList<>();
                    calendars.addAll(fetchCalendarsFromDatabase());
                }
            }
        }
    }
    private static List<MainCalendar> fetchCalendarsFromDatabase() {
        return organizeEventsIntoCalendars( Database.fetchEventsFromDatabase() );
    }
    private static List<MainCalendar> organizeEventsIntoCalendars(List<Event> evnetsToOrganize) {
        ArrayList<MainCalendar> calendarList = new ArrayList<>();
        //TODO: Get the event. Assuming the event contains the name of the calendar of origin,
        //TODO:   get the name of the name of the calendar and search in the knownCalendars for the
        //TODO:   class associated with that calendar. Use this class to get instances of the
        //TODO:   calendars using reflection


        return calendarList;
    }

    //TODO: add methods to add all the events from google and canvas calendar by using the scheduler
    //TODO: implement where assignments extend events, and this is returned
    //TODO: this may not be the best implementation. We could use the ones at the end of the file
    private static List<Event> fetchEventsFromCanvas() {
        return null;
    }

    //TODO: For whoever adds the the google calendars api
    private static List<Event> fetchEventsFromGoogle() { return null; }

    //TODO: think about how to update the calendars with these abstract methods
    public abstract void update();
    public abstract List<Event> fetch();

}
