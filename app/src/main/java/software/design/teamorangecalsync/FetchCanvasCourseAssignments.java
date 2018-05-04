package software.design.teamorangecalsync;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vskun on 4/25/2018.
 */

public class FetchCanvasCourseAssignments {

    public  ArrayList<Event> fetchCanvasAssignmentInfo(){
        System.out.println("fetchCanvasAssignmentInfo");
        ArrayList<Event> che = new ArrayList<>();
        //System.out.println("here to get info111");
        ArrayList<FlexibleCalendar> mn = new ArrayList<>();     //TODO: I changed it to flexible calendar, make sure everything with it makes sense
        Map<String, ArrayList<Event>> Alist= new HashMap<String, ArrayList<Event>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        String Access_Token = "1017~iPDnl3GFOa88Pf7cklx9pJ5Sre7ua4mA6DifYmIBn9fvdmYlqvKtJrLxxQ1MkKSt";

        ExecutorService pool = Executors.newFixedThreadPool(10);
        ConcurrentHashMap<String, String> hs = new ConcurrentHashMap<String,String>();
        for(int i = 1; i < 10; i ++){
            GetCourses t = (new GetCourses(Access_Token,i,hs));
            pool.execute(t);
        }

        pool.shutdown();
        while(!pool.isTerminated()){

        }
        //System.out.println(hs.toString());


        Iterator<String> key = hs.keySet().iterator();

        while(key.hasNext()){
            String course = key.next();
            String courseid = hs.get(course);
            ExecutorService pool2 = Executors.newFixedThreadPool(10);
            ArrayList<Event> assignments = new ArrayList<Event>();
            CopyOnWriteArrayList<Event> hws = new CopyOnWriteArrayList<Event>();
            for(int i = 1; i < 4; i ++){


                GetAssignments t = (new GetAssignments(Integer.toString(i), courseid, Access_Token, hws, course));
                pool2.execute(t);
            }
            pool2.shutdown();
            while(!pool2.isTerminated()){

            }
            che.addAll(hws);

           // Alist.put(course, assignments );
        }

//        Iterator<String> k = Alist.keySet().iterator();
//        while(k.hasNext()){
//            String course = k.next();
//            System.out.println(course);
//            che = Alist.get(course);
////            mn.add(new CanvasCalendar(course,che)); //TODO: change to the appropriate initialization
//
//        }
        //System.out.println(che.toString());
        return che;
    }
}
