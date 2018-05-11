package software.design.teamorangecalsync;

import java.util.ArrayList;
import java.util.List;

public class GoogleCalendar extends FlexibleCalendar {

    private static List<Event> events = new ArrayList<>();

    public GoogleCalendar(String name) {
        super(name);
    }

    public static void addStaticEvent(Event event) {
        events.add(event);
    }
    public static List<Event> fetchEvents() {
        return events;
    }

}
