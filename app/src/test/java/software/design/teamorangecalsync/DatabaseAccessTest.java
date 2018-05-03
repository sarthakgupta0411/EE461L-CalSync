package com.example.nikhilgaur.calendars;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


import static org.junit.Assert.*;

public class DatabaseAccessTest {
    private static boolean setUpDone = false;
    private Connection connection;
    @Before
    public void setUp(){
       if(setUpDone) {
         return;
        }
       // setUpDone = true;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Driver loaded");
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/calendarinfo?autoReconnect=true&useSSL=false" , "scott", "tiger");
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Database connected");

    }
   /* private ArrayList<String> createAnswer1(){
        ArrayList<String> answerCalendars = new ArrayList<String>();

        answerCalendars.add("canvascal1");
        answerCalendars.add("canvascal2");
        answerCalendars.add("canvascal3");
        answerCalendars.add("manual1");
        answerCalendars.add("manual1");
        answerCalendars.add("manual2");
        answerCalendars.add("manual3");

        return answerCalendars;
    }*/

   /* @Test
    public void getAllCalendarNames() {
        String username = "ng9366";

       // ArrayList <String> answerCalendars = createAnswer1();
        ArrayList<String> actualCalendars = DatabaseAccess.getAllCalendarNames(username,connection);

        assertTrue("answer size: " + answerCalendars.size() + " actual size: " + actualCalendars.size(),answerCalendars.size() == actualCalendars.size());

        for(int i = 0; i < actualCalendars.size(); i++){
            assertTrue("actual: " + actualCalendars.get(i) +" answer: " + answerCalendars.get(i),actualCalendars.get(i).equals(answerCalendars.get(i)));

        }
    }*/
    @Test
    public void testSizeAllEventsForCalendar(){
        String username = "ng9366";
        String calName = "canvascal1";

        ArrayList<String> ans = new ArrayList<String>();
        ans.add("final proj");
        ans.add("data science final proj");


        ArrayList<String> actual = DatabaseAccess.getAllEventsForCalendar(username,calName,connection);

        checkSize(actual.size(),ans.size());

       /* try {
            connection.close();
        }
        catch(SQLException sql){
        }*/
    }
    @Test
    public void testCorrectnessAllEventsForCalendar(){
        String username = "ng9366";
        String calName = "canvascal1";

        ArrayList<String> ans = createAnswerAllEventsForCalendar();
        ArrayList<String> actual = DatabaseAccess.getAllEventsForCalendar(username,calName,connection);

        checkCorrectness(actual,ans);

      /*  try {
            connection.close();
        }
            catch(SQLException sql){
        }*/
    }
    @Test
    public void testInsertEvents(){

        //String calendarName, String username, String eventName, String eventType, String assignmentName, String duedDate, String dueTime, String description
       // DatabaseAccess.insertEvents("canvascal1","", "", "","","","",connection);
        DatabaseAccess.insertEvents("canvascal1","ng9366","canvas","robot car","05/03/2018","10:45:00","Cool project in EE 445M end of semester",connection);
        /*try {
            connection.close();
        }
        catch(SQLException sql){
        }*/
    }
    private ArrayList<String> createAnswerAllEventsForCalendar(){
        ArrayList <String> ans = new ArrayList<String>();
        ans.add("final proj");
        ans.add("data science final proj");
        return ans;
    }


    private void checkSize(int actual, int ans){
        assertEquals("actual size: " + actual + "ans size: " + ans,ans,actual);
    }
    private void checkCorrectness(ArrayList<String> actual, ArrayList<String> ans){

        for(int i = 0; i < ans.size(); i++){
            assertTrue("actual: " + actual.get(i) +" answer: " + ans.get(i),actual.get(i).equals(ans.get(i)));
            System.out.println("Actual answer: " + actual.get(i) + " Expected answer: " + ans.get(i));
        }
    }

}