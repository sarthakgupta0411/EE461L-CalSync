package software.design.teamorangecalsync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalSyncCalendar extends FlexibleCalendar {

    public CalSyncCalendar(String name) {
        super(name);
    }

    @Override
    public void addEvent(Date date, Event event) {
        addEvent(date, event);
    }
    @Override
    public void addEvent(Event event) {
        //lazy add event function
        super.addEvent(event.getStartTime(), event);
        Database.addEvent(event);
    }

}