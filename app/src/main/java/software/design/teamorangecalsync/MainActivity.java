package software.design.teamorangecalsync;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Calendar> calendars = Calendar.returnCalendars();

        // Find the ScrollView
        LinearLayout sv = (LinearLayout) findViewById(R.id.linearLayout);

        // Create a LinearLayout element
        //LinearLayout ll = new LinearLayout(this);
        //ll.setOrientation(LinearLayout.VERTICAL);

        // Display the view
        for(Calendar cal : calendars) {
            String name = cal.name;
            // Add text
            TextView tv = new TextView(this);
            tv.setText(name);
            sv.addView(tv);
        }
        // Add the LinearLayout element to the ScrollView
        //sv.addView(ll);
    }
}
