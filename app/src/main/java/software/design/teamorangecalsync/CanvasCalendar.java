package software.design.teamorangecalsync;

import android.os.AsyncTask;
import android.os.Build;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CanvasCalendar extends FlexibleCalendar {

    public static List<Event> calendars = new ArrayList<>();

    public CanvasCalendar(String name) {
        super(name);
    }

    public static List<Event> fetchEvents() {
        System.out.println("fetchEvents");
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

        //return mapToListOfEvents(calendars);
        return calendars;
    }

//    private static List<Event> mapToListOfEvents(HashMap<String, List<Event>> map) {
//        List<Event> allEvents = new LinkedList<>();
//
//        Collection<List<Event>> allLists = map.values();
//        for(List<Event> list : allLists) {
//            allEvents.addAll(list);
//        }
//
//        return allEvents;
//    }

}
