package software.design.teamorangecalsync;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainCalendar {

    //Private MainCalendar class fields
    private static List<FlexibleCalendar> calendars;  //Holds all the calendars in the project

    private final static boolean frontEndDebugging = false;

    //Public class methods
    public static List<FlexibleCalendar> getCalendars() {
        //TODO: remove debugging lines
        if(frontEndDebugging) {
            setupTestCalendarList();
        }
        else {
            if (calendars == null) {
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
    public static List<Event> getEventsFor(Date day) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        List<Event> eventsForDay = new LinkedList<>();
        for(FlexibleCalendar cal : calendars) {
            try {
                eventsForDay.addAll(cal.getEvents().get(sdf.format(day)));
            }
            catch(NullPointerException calendarDoesntHaveEvents) {
                //System.out.println(day);
                //nothing
            }
        }

        return eventsForDay;
    }



    //Private class helper methods
    private static void fetchCalendars() {
        System.out.println("fetchCalendars");
        if (calendars == null) {
            synchronized (MainCalendar.class) {   //thread safe synchronization
                if (calendars == null) {
                    calendars = fetchCalendarsFromAllSources();
               }
            }
        }
    }
    private static List<FlexibleCalendar> fetchCalendarsFromAllSources() {
        System.out.println("fetchCalendarsFromAllSources");
        //compiles the calendars and
        List<FlexibleCalendar> calendars = new LinkedList<>();
        //HashMap<Date, List<Event>> allEvents = new HashMap<>();

        //TODO: uncomment each of these as we add them in full
        //calendars.addAll(organizeEventsIntoCalendars(Database.fetchEventsFromDatabase(), "software.design.teamorangecalsync.CalSyncCalendar"));
        calendars.addAll(organizeEventsIntoCalendars(CanvasCalendar.fetchEvents(), "software.design.teamorangecalsync.CanvasCalendar"));
        //calendars.addAll(organizeEventsIntoCalendars(GoogleCalendar.fetchEvents(), "software.design.teamorangecalsync.GoogleCalendar"));

        //addUniqueEventsToMap(allEvents, Database.fetchEventsFromDatabase());
        //calendars.add( organizeEventsIntoCalendars( mapToListOfEvents(allEvents), "CalSyncCalendar");
        //addUniqueEventsToMap(allEvents, mapToListOfEvents(CanvasCalendar.fetchEvents()));
        //addUniqueEventsToMap(allEvents, GoogleCalendar.fetchEvents());

        return calendars;
    }
    private static void addUniqueEventsToMap(HashMap<String, List<Event>> events, List<Event> list) {
        if(list == null) {
            return;
        }
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
    private static List<FlexibleCalendar> organizeEventsIntoCalendars(List<Event> eventsToOrganize, String subclass) {
        if(eventsToOrganize == null) {
            return null;
        }

        ArrayList<FlexibleCalendar> calendarList = new ArrayList<>();
        ArrayList<String> calendarsAdded = new ArrayList<>();

        //Get the events. Assuming the event contains the name of the calendar of origin,
        for(Event event : eventsToOrganize) {
            String calendarOfOrigin = event.getCalendarOfOrigin();
            if(!calendarsAdded.contains(calendarOfOrigin)) {
                calendarsAdded.add(calendarOfOrigin);
                try {
                    calendarList.add((FlexibleCalendar)(Class.forName(subclass).getConstructor(String.class).newInstance(calendarOfOrigin)));
                }
                catch(Exception exception) {
                    //should not happen
                    exception.printStackTrace();
                }
            }
            calendarList.get(calendarsAdded.indexOf(calendarOfOrigin)).addEvent(event);
        }

        return calendarList;
    }
    private static List<Event> mapToListOfEvents(HashMap<? extends Object, List<Event>> map) {
        if(map == null) {
            return null;
        }
        List<Event> list = new LinkedList<>();
        Collection<List<Event>> allDays = map.values();
        for(List<Event> day : allDays) {
            list.addAll(day);
        }
        return list;
    }

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
//                cSCal.addEvent(start, new Event("RandomEvent" + j, start, end, null, null, cSCal.getDisplayName()));
            }
        }

    }
}
