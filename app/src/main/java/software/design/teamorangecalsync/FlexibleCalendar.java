package software.design.teamorangecalsync;

import android.util.Base64OutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public abstract class FlexibleCalendar {// MainCalendar Object attributes

    //calendar properties
    private String calendarName;
    private HashMap<Date, ArrayList<Event>> events = new HashMap<>();

    private int postfix;
    private boolean locked = false;
    private boolean visible = true;
    private int color;
    private int id;
    
    //Calendar context and name
    public FlexibleCalendar(String name, Class calendarSubclass) {
        this(name);
        MainCalendar.mapCalendarToClass(name, calendarSubclass);
    }

    //Calendar name constructor
    public FlexibleCalendar(String name) {
        if(name == null || name.replaceAll(" ", "").length() == 0) {
            name = "Unnamed";
        }
        buildUniqueName(name);
    }

    public void addEvent(Date date, Event event) {
        if (!events.containsKey(date)) {
            events.put(date, new ArrayList<Event>());
        }
        events.get(date).add(event);
    }
    public void addEvent(Event event) {
        //lazy add event function
        addEvent(event.getStartTime(), event);
    }

    public abstract void syncCalendar(); //pull new events from their respective storage

    //Getters
    public int getColor() {
        return color;
    }
    public String getDisplayName() {
        return calendarName + "-" + postfix;
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
    public int getPostfix() {
        return postfix;
    }
    public boolean isVisible() {
        return visible;
    }
    public boolean isLocked() {
        return locked;
    }

    //Setters
    public void setColor(int color) {
        this.color = color;
    }
    public void setName(String name) {
        calendarName = name;
    }
    public void setVisible(boolean value) {
        visible = value;
    }
    public boolean setLocked(boolean value) {
        locked = value;
        return true;
    }   //returns success in setting}

    //private helper functions
    private void buildUniqueName(String name) {
        //TODO: search through the list of calendars and check for a similar name
        List<FlexibleCalendar> calendars = MainCalendar.getCalendars(); //get list
        for(FlexibleCalendar cal : calendars) {
            if(cal.getName().equals(name)) {
                name += "-" + (cal.getPostfix() + 1);
                break;
            }
            else if(cal.getDisplayName().equals(name)) {
                name += "-1";
                break;
            }
        }
        buildNameAndPostfix(name);
    }
    private void buildNameAndPostfix(String displayName) {
        String[] nameSplit = displayName.split("-");
        try {
            postfix = Integer.parseInt(nameSplit[nameSplit.length - 1]);
            calendarName = displayName.substring(0, displayName.lastIndexOf('-'));
        }
        catch(Exception e) {
            postfix = 0;
            calendarName = displayName;
        }
    }
}