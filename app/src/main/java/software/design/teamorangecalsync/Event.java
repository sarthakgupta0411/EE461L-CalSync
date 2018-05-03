package software.design.teamorangecalsync;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Event {

    private String title;    //name of the event
    private Date startTime;  //start date and time within the date object
    protected Date endTime;    //end date and time

    private String location;   //location
    private String extraNotes; //description

    private String calendarOfOrigin; //calendar name
    private Date startDate;  //the day date of the event

    //full argument constructor (sorry for all the arguments. I thought it would make what we need clear)
    public Event(String _title, Date _startTime, Date _endTime, String _location, String _extraNotes, String _calendarOfOrigin) {
        assert(_startTime != null); //these definitely should not be nulls
        assert(_endTime != null);
        assert(_calendarOfOrigin != null);
        title = (_title == null) ? "(No title)" : _title;
        location = (_location == null) ? "" : _location;
        extraNotes = (_extraNotes == null) ? "" : _extraNotes;
        startTime = _startTime;
        endTime = _endTime;
        calendarOfOrigin = _calendarOfOrigin;
    }

    //getters
    public String getTitle() {
        return title;
    }
    public Date getStartTime() {
        return startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public String getLocation() {
        return location;
    }
    public String getExtraNotes() {
        return extraNotes;
    }
    public String getCalendarOfOrigin() {
        return calendarOfOrigin;
    }
    public Date getStartDate() {
        return startDate;
    }

    //setters
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    public void setStartTime(Date newStart) {
        startTime = newStart;
    }
    public void setEndTime(Date newEnd) {
        endTime = newEnd;
    }
    public void setLocation(String newLocation) {
        location = newLocation;
    }
    public void setExtraNotes(String newNotes) {
        extraNotes = newNotes;
    }
    public void setCalendarOfOrigin(String newCalendar) {
        calendarOfOrigin = newCalendar;
    }
    private void setStartDate(Date startTime) {
        Calendar cal = Calendar.getInstance();
        cal.set(startTime.getYear(), startTime.getMonth(), startTime.getDay());
        startDate = cal.getTime();
    }

    @Override
    public boolean equals(Object other) {
        Event that = (Event)other;
        return this.title.equals(that.title)
                && (this.calendarOfOrigin.equals(that.calendarOfOrigin))
                && (this.startTime.getTime() == that.startTime.getTime())
                && (this.endTime.getTime() == that.endTime.getTime())
                && this.location.equals(that.location)
                && this.extraNotes.equals(that.extraNotes);
    }

    @Override
    public String toString() {
        return "\"" + title + "\" UNDER: \"" + calendarOfOrigin + "\" FROM: \"" + startTime
                + "\" TO: \"" + endTime + "\" AT: \"" + location + "\" AND: \"" + extraNotes + "\"";
    }

}