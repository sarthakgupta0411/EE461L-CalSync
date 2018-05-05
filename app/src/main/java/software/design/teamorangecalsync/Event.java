package software.design.teamorangecalsync;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Event {

    private String title;    //name of the event
    private Date startTime;  //start date and time within the date object
    protected Date endTime;    //end date and time

    private String location;   //location
    private String extraNotes; //description

    private String calendarOfOrigin; //calendar name
    private String startDate;  //the day date of the event

    //constructor with single date
    public Event(String _title, Date _startTime, String _location, String _extraNotes, String _calendarOfOrigin) {
        this(_title, _startTime, _startTime, _location, _extraNotes, _calendarOfOrigin);
        endTime.setTime(endTime.getTime() + 900000);   //adding 15 minutes to endTime
    }

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
        setStartDate(startTime);
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
    public String getStartDate() {
        return startDate;
    }

    //setters
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    public void setStartTime(Date newStart) {
        startTime = newStart;
        setStartDate(startTime);
    }
    public void setEndTime(Date newEnd) {
        endTime = newEnd;
    } //TODO: make sure that end time stays ahead of start time
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        startDate = sdf.format(startTime.getTime());
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