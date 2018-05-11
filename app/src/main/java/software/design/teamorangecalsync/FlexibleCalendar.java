package software.design.teamorangecalsync;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public abstract class FlexibleCalendar {// MainCalendar Object attributes

    //Calendar properties
    private String calendarName;
    private HashMap<String, List<Event>> events = new HashMap<>();

    private int postfix;
    private boolean locked = false;
    private boolean visible = true;
    private int color;
    private int id;

    //Calendar name constructor. To be used by MainCalendar for fetching already mapped calendars
    public FlexibleCalendar(String name) {
        if(name == null || name.replaceAll(" ", "").length() == 0) {
            name = "Unnamed";
        }
        //buildUniqueName(name);
        calendarName = name;
    }

    public void addEvent(String date, Event event) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
//        String dateKey = sdf.format(date.getTime());
        if (!events.containsKey(date)) {
            events.put(date, new ArrayList<Event>());
        }
        events.get(date).add(event);

    }
    public void addEvent(Event event) {
        //lazy add event function
        addEvent(event.getStartDate(), event);
    }
    public void deleteEvent(Event event) {
        if(!events.containsKey(event.getStartDate())) {
            return;
        }
        events.get(event.getStartDate()).remove(event);
        Scheduler.getInstance().deleteEvent(event);
    }
    public void editEvent(Event oldEvent, Event newEvent) {
        if(!events.containsKey(oldEvent.getStartDate())) {
            return;
        }
        events.get(oldEvent.getStartDate()).remove(oldEvent);
        events.get(oldEvent.getStartDate()).add(newEvent);
        Scheduler.getInstance().editEvent(oldEvent, newEvent);
    }


    //Getters
    public int getColor() {
        return color;
    }
    public String getDisplayName() {
        return calendarName + "-" + postfix;
    }
    public HashMap<String, List<Event>> getEvents() {
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

    //Private helper functions
    private void buildUniqueName(String name) {
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