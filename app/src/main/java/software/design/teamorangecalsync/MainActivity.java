package software.design.teamorangecalsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Calendar> calendars = Calendar.getCalendars();

        // Find the Linear layout in the ScrollView
        LinearLayout sv = findViewById(R.id.linearLayout);

        // Add each calendar as a checkbox object to the linear layout
        for(Calendar cal : calendars) {
            CheckBox cb = new CheckBox(this);
            // Add text
            cb.setText(cal.name);
            // Set color
            cb.setTextColor(getResources().getColor(R.color.calsync_theme_foreground));
            // Add to view
            sv.addView(cb);
        }
    }

    public void debuggingButton(View view) {
        //prints the first day of the week
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getFirstDayOfWeek());
        //prints information about the calendar view
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getMinDate());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getMaxDate());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getX());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getTop());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getWidth());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getHeight());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getChildCount());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getChildAt(0));
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).getChildAt(0).getClass());

        Date date = new Date(((CalendarView)findViewById(R.id.calendarView)).getDate());
        System.out.println(date);
        System.out.println(date.getDay());
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.);



        System.out.println(((CalendarView)findViewById(R.id.calendarView)).findFocus());
        System.out.println(((CalendarView)findViewById(R.id.calendarView)).isDrawingCacheEnabled());

    }

}
