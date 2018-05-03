package software.design.teamorangecalsync;

import java.util.HashMap;
import java.util.List;

public class CanvasCalendar extends FlexibleCalendar {

    public static List<FlexibleCalendar> calendars;

    public CanvasCalendar(String name) {
        super(name);
    }

    public static HashMap<String, List<Event>> fetchEvents() {
        //TODO: Fix up
//        AsyncTask<String,String,String> myTask = new AsyncTask<String,String,String>() {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//
//            @Override
//            protected String doInBackground(String... strings) {
//                System.out.println("here to get info");
//                FetchCanvasCourseAssignments f = new FetchCanvasCourseAssignments();
//                calendars = f.fetchCanvasAssignmentInfo();
//
//                System.out.print(calendars.size());
//                return null;
//            }
//
//        };// ... your AsyncTask code goes here
//        System.out.println("here to get info");
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB)
//            myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        else
//            myTask.execute();
//
//        System.out.println("here to get info2");
//        while(calendars.size() == 0){
//
//        }
//
//        return calendarListToMapOfNameAndEvents(calendars);
        return null;
    }

//    private static HashMap<String, List<Event>> calendarListToMapOfNameAndEvents(List<FlexibleCalendar> list) {
//        HashMap<String, List<Event>> map = new HashMap<>();
//
//        for(FlexibleCalendar cal : list) {
//            String name = cal.getName();
//            map.put(name, new LinkedList<Event>());
//
//        }
//    }

}
