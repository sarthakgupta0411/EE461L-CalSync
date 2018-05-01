package software.design.teamorangecalsync;

public abstract class DatabaseCommand {

    Database database;  // database singleton reference
    Event event;  // general purpose event reference. Could be used for delete or add, etc

    DatabaseCommand() {
        //every subclass calls this automatically; gets the database singleton. It's just a pointer
        database = Database.getInstance();
    }

    protected abstract void execute();

    //I use this to manage these cases: edit+edit, add+edit, add+delete, edit+delete
    protected Event getEvent() {
        //For edit, returns the new event. I use this for comparison purposes
        return event;
    }
    protected void setEvent(Event changedEvent) {
        event = changedEvent;
    }

}

/** @Nikhil. Use your methods from the database class using either the database reference, or
  * static calls to the Database class. You decide however you want to do it. Implement the executes
  * for each command.
  */
class AddEventDatabaseCommand extends DatabaseCommand {

    public AddEventDatabaseCommand(Event eventToAdd) {
        event = eventToAdd;
    }

    @Override
    protected void execute() {
        //TODO:
    }

}

class DeleteEventDatabaseCommand extends DatabaseCommand {

    public DeleteEventDatabaseCommand(Event eventToDelete) {
        event = eventToDelete;
    }

    @Override
    protected void execute() {
        //TODO:
    }
}

class EditEventDatabaseCommand extends DatabaseCommand {

    private Event oldEvent;

    public EditEventDatabaseCommand(Event oldEvent, Event newEvent) {
        this.oldEvent = oldEvent;
        event = newEvent;
    }

    @Override
    protected void execute() {
        //TODO:
    }

}