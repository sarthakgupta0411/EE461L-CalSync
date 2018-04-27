package software.design.teamorangecalsync;

import java.util.HashMap;

public class Event {

    private HashMap<String, Object> properties;  //properties as a hashmap

    private String eventName; //name of the event
    private String calendarName;  // name of the calendar for this event
    private int color;

    public Event(String _eventName) {
        eventName = _eventName;
    }

    //pass it the name of the property and the object (Date, int, String) associated with it
    public void setProperty(String proterty, Object value) {
        properties.put(proterty, value);
    }

    @Override
    public String toString() {
        return eventName;
    }

}
