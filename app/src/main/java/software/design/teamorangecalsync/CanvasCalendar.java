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

public class CanvasCalendar extends MainCalendar {

    public CanvasCalendar(String canvasCalendar) {
        super(canvasCalendar);
    }
    public CanvasCalendar(String canvasCalendar, ArrayList<Event> E) {

        super(canvasCalendar);
        this.events = E;

    }


}
