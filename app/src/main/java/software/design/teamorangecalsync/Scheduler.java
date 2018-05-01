package software.design.teamorangecalsync;

//Handles front end scheduling for events, and scheduling database changes
public class Scheduler {
    private static final Scheduler ourInstance = new Scheduler();

    public static Scheduler getInstance() {
        return ourInstance;
    }

    private Scheduler() {
    }
}
