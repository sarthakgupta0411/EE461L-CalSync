package software.design.teamorangecalsync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalSyncCalendar extends FlexibleCalendar {

    public CalSyncCalendar(String name) {
        super(name);
    }

    @Override
    public void addEvent(String date, Event event) {
        super.addEvent(date, event);
        Database.addEvent(event);
    }
    @Override
    public void addEvent(Event event) {
        //lazy add event function
        addEvent(event.getStartDate(), event);
    }
    @Override
    public void deleteEvent(Event event) {
        super.deleteEvent(event);
        Database.deleteEvent(event);
    }
    @Override
    public void editEvent(Event oldEvent, Event newEvent) {
        super.editEvent(oldEvent, newEvent);
        Database.editEvent(oldEvent, newEvent);
    }

}