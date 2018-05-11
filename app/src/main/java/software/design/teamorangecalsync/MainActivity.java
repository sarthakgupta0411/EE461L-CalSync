package software.design.teamorangecalsync;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private CompactCalendarView compactCalendarView;
    private Scheduler scheduler;

    public static boolean canvas;

    protected final static String[] MONTH_NAMES =
            {"January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addActionBarReference();
        compactCalendarView = findViewById(R.id.compactcalendar_view);
        scheduler = Scheduler.getInstance();
        passCalendarReferenceToScheduler();
        //addCalendarsToScrollView();
        addEventsToCalendarView();

        addCalendarViewListeners();
    }


    private void addCalendarsToScrollView() {

        List<FlexibleCalendar> calendars = MainCalendar.getCalendars();

        // Find the Linear layout in the ScrollView
        LinearLayout sv = findViewById(R.id.linearLayout_main);

        // Add each calendar as a checkbox object to the linear layout
        for(FlexibleCalendar cal : calendars) {
            CheckBox checkBox = new CheckBox(this);

            checkBox.setText(cal.getName());        // Add text
            //checkBox.setId(cal.getId());            // Set id
            //checkBox.setTextColor(cal.getColor());  // Set color
            //checkBox.setChecked(cal.isVisible());    // Set checkbox checked

            sv.addView(checkBox);                   // Add to scroll view
        }

    }
    private void addEventsToCalendarView() {
        List<FlexibleCalendar> calendars = MainCalendar.getCalendars();
        System.out.println("------------------------------------------------------------");
        //The events are stored in a hashmap, where the key is the date of the event, and the value is an arraylist of all the events that day.
        //To get reference to the events for a day ArrayList<Event> = calendar.getEvents().get(new Date(year, month, day));
        //You can run through the arraylist like normal. Look through MainCalendar for more
        for (FlexibleCalendar cal : calendars){ //for each calendar in the list
            if(cal.isVisible()) {// check if the calendar is visible
                Collection<List<Event>> events = cal.getEvents().values();//add all events to calendar
                for(List<Event> day : events) {
                    for(Event event : day) {
                        scheduler.addEvent(event);
                    }
                }
            }
        }
    }

    private void passCalendarReferenceToScheduler() {
        scheduler.setCalendarReference(compactCalendarView);
    }

    private void addActionBarReference() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(MONTH_NAMES[Calendar.getInstance().get(Calendar.MONTH)]
                + " " + Calendar.getInstance().get(Calendar.YEAR));
    }

    private void addCalendarViewListeners() {
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                DayViewActivity.eventsPassedToDayView = MainCalendar.getEventsFor(dateClicked);
                gotoDayViewActivity();
                System.out.println(DayViewActivity.eventsPassedToDayView);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle( "" + MONTH_NAMES[firstDayOfNewMonth.getMonth()]
                        + " " + currentYear(firstDayOfNewMonth));
            }

            private int currentYear(Date deprecated) {
                return 1900 + deprecated.getYear();
            }

        });
    }


    //Button onclick calls
    public void gotoAddEvent(View view) {
        gotoActivity(AddEvent.class);
    }
    public void gotoAddFlyerEvent(View view) {
        gotoActivity(AddFlyerEvent.class);
    }
    public void gotoCanvasLogin(View view) {
        gotoActivity(CanvasLogin.class);
    }
    public void gotoDayViewActivity() {
        gotoActivity(DayViewActivity.class);
    }
    public void gotoEditCalendars(View view) {
        gotoActivity(EditCalendars.class);
    }
    private void gotoActivity(Class activity) {
        Intent activityIntent = new Intent(this, activity);
        startActivity(activityIntent);
    }

}
