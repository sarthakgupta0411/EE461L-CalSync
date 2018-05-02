package software.design.teamorangecalsync;

import java.util.Date;

public class Assignment extends Event {

    public Assignment(String assignment, Date dueDate, String description, String course) {
        super(assignment, dueDate, dueDate, null, description, course);
        endTime.setTime(endTime.getTime() + 900000);   //adding 15 minutes to endTime
    }

    //getters (should not need to use these if we're just treating this as an Event object)
    public String getTitle() {
        return super.getTitle();
    }
    public Date getDueDate() {
        return super.getStartTime();
    }
    public String getDescription() {
        return super.getExtraNotes();
    }
    public String getCourse() {
        return super.getCalendarOfOrigin();
    }

    //setters (makes sure assignments can't be editted, only created)
    @Override
    public void setTitle(String newTitle) { }
    @Override
    public void setStartTime(Date newStart) { }
    @Override
    public void setEndTime(Date newEnd) { }
    @Override
    public void setLocation(String newLocation) { }
    @Override
    public void setExtraNotes(String newNotes) { }
    @Override
    public void setCalendarOfOrigin(String newCalendar) { }

}
