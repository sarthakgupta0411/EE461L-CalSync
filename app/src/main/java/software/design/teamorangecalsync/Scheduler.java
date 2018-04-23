package software.design.teamorangecalsync;

import android.widget.CalendarView;

public class Scheduler {

    private static final Scheduler ourInstance = new Scheduler();

    private static CalendarView mainCalendar;

    public static Scheduler getInstance() {
        return ourInstance;
    }

    private Scheduler() {
    }

    public void scheduleEvent(Event toSchedule) {
        //TODO:
    }

    //gets a reference to the calendar view in the main activity
    public static void setCalendarView(CalendarView _mainCalendar) {
        mainCalendar = _mainCalendar;   
    }
}
