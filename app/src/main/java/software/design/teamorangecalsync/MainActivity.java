package software.design.teamorangecalsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCalendarsToScrollView();
       // addEventsToCalendarView();
    }


    private void addCalendarsToScrollView() {

        ArrayList<MainCalendar> calendars = MainCalendar.getCalendars();

        // Find the Linear layout in the ScrollView
        LinearLayout sv = findViewById(R.id.linearLayout);

        // Add each calendar as a checkbox object to the linear layout
        for(MainCalendar cal : calendars) {
            CheckBox checkBox = new CheckBox(this);

            checkBox.setText(cal.getName());        // Add text
            //checkBox.setId(cal.getId());            // Set id
            //checkBox.setTextColor(cal.getColor());  // Set color
            //checkBox.setChecked(cal.isActive());    // Set checkbox checked

            sv.addView(checkBox);                   // Add to scroll view
        }

    }
    private void addEventsToCalendarView() {
        //TODO: @Sierra implement this using the compact calendar view component from the git API

        ArrayList<MainCalendar> calendars = MainCalendar.getCalendars();
        //The events are stored in a hashmap, where the key is the date of the event, and the value is an arraylist of all the events that day.
        //To get reference to the events for a day ArrayList<Event> = calendar.getEvents().get(new Date(year, month, day));
        //You can run through the arraylist like normal. Look through MainCalendar for more
        /*  for every calendar in the calendars
         *      check if the calendar is active
         *      for every day in the current month in the calendar view
         *          check if any event in the calendar
         *              say events added are 0 so far
         *              while there are events and events added less than 3 (or 5, you pick how many circles you want)
         *                  if event is not already there (check day with api)
         *                      add the event to the calendar view using the api
         */
    }

}
