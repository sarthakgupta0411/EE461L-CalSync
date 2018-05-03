package software.design.teamorangecalsync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainCalendar {

    //Private MainCalendar class fields
    private static ArrayList<FlexibleCalendar> calendars;  //Holds all the calendars in the project
    private static HashMap<String, Class> calendarNames;  //keeps track of the type of calendar by calendar name

    private final static boolean frontEndDebugging = true;

    //Public class methods
    public static ArrayList<FlexibleCalendar> getCalendars() {
        //TODO: remove debugging lines
        if(frontEndDebugging) {
            setupTestCalendarList();
        }
        else {
            if (calendars == null) {
                calendars = new ArrayList<>();
                fetchCalendars();   //add calendars to the arraylist
            }
        }
        return calendars;
    }
    public static FlexibleCalendar getCalendarByName(String name) {
        for(FlexibleCalendar cal : calendars) {
            if (cal.getDisplayName().equals(name)) {
                return cal;
            }
        }
        return null;    //returns null if not found
    }
    public static void mapCalendarToClass(String name, Class calendar) {
        calendarNames.put(name, calendar);
    }



    //Private class helper methods
    private static void fetchCalendars() {
        if (calendars == null) {
            synchronized (MainCalendar.class) {   //thread safe synchronization
                if (calendars == null) {
                    calendars = new ArrayList<>();
                    calendars.addAll(fetchCalendarsFromAllSources());
                }
            }
        }
    }
    private static List<FlexibleCalendar> fetchCalendarsFromAllSources() {
        //compiles the calendars and
        return organizeEventsIntoCalendars(fetchUniqueEventsFromAllSources());
    }
    private static List<Event> fetchUniqueEventsFromAllSources() {
        HashMap<Date, List<Event>> allEvents = new HashMap<>();

        addUniqueEventsToMap(allEvents, Database.fetchEventsFromDatabase());
        //addUniqueEventsToMap(allEvents, CanvasCalendar.fetchEvents());
        //addUniqueEventsToMap(allEvents, GoogleCalendar.fetchEvents());

        return mapToListOfEvents(allEvents);
    }
    private static void addUniqueEventsToMap(HashMap<Date, List<Event>> events, List<Event> list) {
        for(Event event : list) {
            if(!events.containsKey(event.getStartDate())) {
                events.put(event.getStartDate(), new LinkedList<Event>());
            }
            List<Event> day = events.get(event.getStartDate());
            boolean found = false;
            for(Event inThatDay : day) {
                if(inThatDay.equals(event)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                day.add(event);
            }
        }
    }
    private static List<FlexibleCalendar> organizeEventsIntoCalendars(List<Event> evnetsToOrganize) {
        ArrayList<FlexibleCalendar> calendarList = new ArrayList<>();
        //TODO: Get the event. Assuming the event contains the name of the calendar of origin,
        //TODO:   get the name of the name of the calendar and search in the knownCalendars for the
        //TODO:   class associated with that calendar. Use this class to get instances of the
        //TODO:   calendars using reflection


        return calendarList;
    }
    private static List<Event> mapToListOfEvents(HashMap<Date, List<Event>> map) {
        List<Event> list = new LinkedList<>();
        Collection<List<Event>> allDays = map.values();
        for(List<Event> day : allDays) {
            list.addAll(day);
        }
        return list;
    }

    //TODO: add methods to add all the events from google and canvas calendar by using the scheduler
    //TODO: implement where assignments extend events, and this is returned
    //TODO: this may not be the best implementation. We could use the ones at the end of the file
    private static List<Event> fetchEventsFromCanvas() {
        return null;
    }

    //TODO: For whoever adds the the google calendars api
    private static List<Event> fetchEventsFromGoogle() { return null; }

    //Makes test calendar list for implementing the front end stuff
    private static void setupTestCalendarList() {
        calendars = new ArrayList<FlexibleCalendar>();

        for(int i = 0; i < 3; i++) {
            //create 3 calendars
            CalSyncCalendar cSCal = new CalSyncCalendar("TestCalendar" + i);
            for(int j = i; j < 3; j++) {
                //add 3 events on the first, 2 on the second, 1 on the third
                Date start = new Date(2018, 4, 27 + i, 11, 00);
                Date end = new Date(2018, 4, 27 + i, 11, 30);
                cSCal.addEvent(start, new Event("RandomEvent" + j, start, end, null, null, cSCal.getDisplayName()));
            }
        }

    }

}
