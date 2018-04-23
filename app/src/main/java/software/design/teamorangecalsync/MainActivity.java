package software.design.teamorangecalsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;

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
    }
}
