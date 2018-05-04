package software.design.teamorangecalsync;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

//Handles front end scheduling for new events
public class Scheduler {

    private static final Scheduler ourInstance = new Scheduler();

    private CompactCalendarView calendarView;

    private Scheduler() {
    }

    public static Scheduler getInstance() {
        return ourInstance;
    }

    /**
     * Called from add event activities (where the event object should be created from the fields)
     * @param event
     */
    public void addEvent(Event event) {
        //front end
        //TODO: add the front end to add the dot to the calendar


    }

    /**
     * Called from any activity that delete
     * @param event
     */
    public void deleteEvent(Event event) {
        //front end
        //TODO: add the front end to delete the event form the calendarview


    }
    /**
     * Called from the edit event activity. A new event needs to be created with the edits
     * These cases, I assume, never happens: delete+edit, editing without an existing event
     * @param oldEvent
     * @param newEvent
     */
    public void editEvent(Event oldEvent, Event newEvent) {
        //front end
        //TODO: add front end changes to edi the event in the calendar view

    }

    public void setCalendarReference(CompactCalendarView ccv) {
        calendarView = ccv;
    }

}
