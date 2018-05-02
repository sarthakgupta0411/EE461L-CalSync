package software.design.teamorangecalsync;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FlexibleCalendar {// MainCalendar Object attributes

    public String calendarName;
    public HashMap<Date, ArrayList<Event>> events;
    public boolean active;
    public int color;
    public int id;
    public boolean locked;

    public FlexibleCalendar() {
    }//public functions

    public boolean addEvent(Date date, Event event) {
        if (!events.containsKey(date)) {
            events.put(date, new ArrayList<Event>());
        }
        events.get(date).add(event);
        return true;
    }

    public void uploadToDatabase() {
        //push the code to the database
    }// public getters

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

    public boolean isLocked() {
        return locked;
    }//public setters

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
    }   //returns success in setting}
}