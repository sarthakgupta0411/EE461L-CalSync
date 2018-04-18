package software.design.teamorangecalsync;

import java.util.ArrayList;

public class Calendar {

    protected static ArrayList<Calendar> calendars;
    String name;

    public Calendar() {
    }
    public Calendar(String _name) {
        name = _name;
    }

    public static ArrayList<Calendar> returnCalendars() {
        //get from database
        if (calendars == null) {
            //TODO: replace with pull from database if arraylist is null. This would take care of the updating we need
            calendars = new ArrayList<>();
            //add 10 calendars
            for(int i = 0; i < 10; i++) {
                calendars.add(new Calendar("Calendar" + i));
            }
        }
        return calendars;
    }
    public void uploadToDatabase() {
        //push the code to the database
    }
}
