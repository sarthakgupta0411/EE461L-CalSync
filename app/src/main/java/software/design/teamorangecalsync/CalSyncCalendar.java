package software.design.teamorangecalsync;

public class CalSyncCalendar extends MainCalendar {

    public CalSyncCalendar(String name) {
        super(name);
        locked = true;
    }

    @Override
    public boolean setLocked(boolean value) { return false; }

}