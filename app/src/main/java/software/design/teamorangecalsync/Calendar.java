package software.design.teamorangecalsync;

import java.util.ArrayList;

public class Calendar {

    protected static ArrayList<Calendar> calendars;
    String name;

    Calendar() {
    }
    Calendar(String _name) {
        name = _name;
    }

    public static ArrayList<Calendar> returnCalendars() {
        //get from database
        if (calendars == null) {
            //TODO: replace with pull from database if arraylist is null
            calendars = new ArrayList<>();
            calendars.add(new Calendar1());
        }
        return calendars;
    }
    public void uploadToDatabase() {
        //push the code to the database
    }
}
