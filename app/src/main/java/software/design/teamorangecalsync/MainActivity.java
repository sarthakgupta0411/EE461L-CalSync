package software.design.teamorangecalsync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

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

        /*
        //create a spinner to be able to select the hour
        Spinner hourSpinner = (Spinner) findViewById(R.id.hourSpinner);
        ArrayAdapter<CharSequence> adapterHour = ArrayAdapter.createFromResource(this,
                R.array.hours_array, android.R.layout.simple_spinner_item);
        adapterHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(adapterHour);

        //create a spinner to be able to select minute
        Spinner minuteSpinner = (Spinner) findViewById(R.id.minuteSpinner);
        ArrayAdapter<CharSequence> adapterMinute = ArrayAdapter.createFromResource(this,
                R.array.hours_array, android.R.layout.simple_spinner_item);
        adapterMinute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(adapterMinute);
        */
    }

    public void createNewEvent(View view){
        Intent intent = new Intent(this, AddEvent.class);
        startActivity(intent);
    }
}
