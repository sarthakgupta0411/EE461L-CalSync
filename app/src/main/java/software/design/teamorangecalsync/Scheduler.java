package software.design.teamorangecalsync;

//Handles front end scheduling for new events, and scheduling database changes
public class Scheduler {

    private static final Scheduler ourInstance = new Scheduler();

    public static Scheduler getInstance() {
        return ourInstance;
    }

    private Scheduler() {
    }

    /**
     * Called from add event activities (where the event object should be created from the fields)
     * @param event
     */
    public void addEvent(Event event) {
        //front end
        //TODO: add the front end to add the dot to the calendar

        //back end
        Database.enqueueCommand(new AddEventDatabaseCommand(event));
    }

    /**
     * Called from any activity that delete
     * @param event
     */
    public void deleteEvent(Event event) {
        //front end
        //TODO: add the front end to delete the event form the calendarview

        //back end //TODO: @anyone this logic could be simplified
        DatabaseCommand previousChange = Database.findCommandWithEvent(event);
        if(previousChange != null) {
            if(previousChange instanceof AddEventDatabaseCommand) {
                //if previous change is a queued up add event command, just remove it
                Database.removeFromQueue(previousChange);
            }
            else {
                //else it's an edit, in which case remove it and add a delete command
                Database.removeFromQueue(previousChange);
                Database.enqueueCommand(new DeleteEventDatabaseCommand(event));
            }
        }
        else {
            //else it needs to edit based on an event that was in the database
            Database.enqueueCommand(new DeleteEventDatabaseCommand(event));
        }
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

        //backend
        DatabaseCommand previousChange = Database.findCommandWithEvent(oldEvent);
        if(previousChange != null) {
            DatabaseCommand newChange = previousChange;
            Database.removeFromQueue(previousChange);
            //if it's a queued up add or an edit, just change the event changes, and enqueue last
            newChange.setEvent(newEvent);
            Database.enqueueCommand(newChange);
        }
        else {
            //else it needs to edit based on an event that was in the database
            Database.enqueueCommand(new EditEventDatabaseCommand(oldEvent, newEvent));
        }
    }

}
