package software.design.teamorangecalsync;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public abstract class MainCalendar {

    protected static ArrayList<MainCalendar> calendars;

    protected String name;
    protected ArrayList<Event> events;


    protected MainCalendar() {
        if (calendars == null) {
            //will initialize if null
            fetchCalendars();
        }
        calendars.add(this);
    }
    protected MainCalendar(String _name) {
        this();
        name = _name;
        events = new ArrayList<Event>();
    }

    protected void addEvent(Event newEvent) {
        events.add(newEvent);
    }
    protected void deleteEvent(Event event) {
        events.remove(event);
    }
    protected void storeToDatabase() {
        //TODO: push the code to the database
    }

    protected static ArrayList<MainCalendar> getCalendars() {
        //get from database
        if (calendars == null) {
            fetchCalendars();
        }
        return calendars;
    }

    private static void fetchCalendars() {
        int flag = 0;

        if (calendars == null) {
            synchronized (MainCalendar.class) {//thread safe synchronization

                if (calendars == null) {
                    calendars = new ArrayList<>();
                    //TODO: Replace with pull from database if arraylist is null. This would take
                    //TODO: care of the updating we need
                    //TODO: pull the events from database, and make new calendars for each calendar
                    //TODO: of origin, and add the events. Then add the calendars to calendars
                    //TODO: call scheduleevent
                    AsyncTask<String,String,String> myTask = new AsyncTask<String,String,String>() {

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();

                        }

                        @Override
                        protected String doInBackground(String... strings) {
                            System.out.println("here to get info");
                            FetchCanvasCourseAssignments f = new FetchCanvasCourseAssignments();
                            calendars = f.fetchCanvasAssignmentInfo();

                            System.out.print(calendars.size());
                            return null;
                        }

                    };// ... your AsyncTask code goes here
                    System.out.println("here to get info");
                    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB)
                        myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    else
                        myTask.execute();

                    System.out.println("here to get info2");
                    while(calendars.size() == 0){

                    }

                    //add 10 calendars
                    //TODO: replace


                }
            }
        }
    }
}
