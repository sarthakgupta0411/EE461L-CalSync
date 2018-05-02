package software.design.teamorangecalsync;

import java.util.Date;
import java.util.HashMap;

public class Event {

    private EventMetadata properties;
    public Event() {}

    @Override
    public String toString() {
        return properties.title;
    }

    //TODO: implement the .equals method

}

class EventMetadata {

    public String title;    //name of the event
    public Date startTime;  //start date and time within the date object
    public Date endTime;    //end date and time

    public String location;   //location for the
    public String extraNotes; //description

}