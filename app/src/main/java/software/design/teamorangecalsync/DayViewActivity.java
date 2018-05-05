package software.design.teamorangecalsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class DayViewActivity extends AppCompatActivity {

    protected static List<Event> eventsPassedToDayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        System.out.println("DayViewActivity: ");
        System.out.println(eventsPassedToDayView);
    }
}
