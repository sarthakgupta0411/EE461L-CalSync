package com.example.nikhilgaur.calendars;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseAccess {
    public static ArrayList<String> getAllCalendarNames(String username, Connection conn){
        ArrayList<String> nameList = new ArrayList<String>();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement("select calendarname from usertable where usertable.username = ? ");
            preparedStatement.setString(1,username);
            ResultSet resultset = preparedStatement.executeQuery();

            while(resultset.next()) {
                try {
                    nameList.add(resultset.getString("calendarname"));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return nameList;



    }
    public static ArrayList<String> getAllEventsForCalendar(String username, String calendarName, Connection conn){
        ArrayList<String> eventList = new ArrayList<String>();
        PreparedStatement preparedStatement;
        ResultSet resultset = null;
        try{
            //System.out.println("Is connection null?: " + conn == null);
            preparedStatement = conn.prepareStatement("selec assignments from canvasevent where canvasevent.username = ? and canvasevent.canvascalendarname = ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,calendarName);
            resultset = preparedStatement.executeQuery();

        } catch(SQLException e){
            e.printStackTrace();
        }
        try {
            while (resultset.next()) {

                eventList.add(resultset.getString("assignments"));

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return eventList;
    }
    public static void insertEvents (String calendarName, String username, String eventType, String assignmentName, String duedDate, String dueTime, String description, Connection conn){
        PreparedStatement preparedStatement;
        eventType = eventType.toLowerCase();
        calendarName = "canvascal1";
        try {
            switch (eventType) {
                case "canvas":
                    System.out.println("inserting canvas event");
                    preparedStatement = conn.prepareStatement("insert into canvasevent values(?,?,?,?,?,?)");
                    System.out.println("preparing statement");
                    preparedStatement.setString(1,"canvascal1");
                    System.out.println("statement arg 1 done");

                    preparedStatement.setString(2,"ng9366");
                    System.out.println("statement arg 2 done");

                    preparedStatement.setString(3,"robot car");
                    System.out.println("statement arg 3 done");

                    preparedStatement.setString(4,"05/03/2018");
                    System.out.println("statement arg 4 done");

                    preparedStatement.setString(5,"10:45:00");
                    System.out.println("statement arg 5 done");

                    preparedStatement.setString(6,"Cool project in EE 445M end of semester");
                    System.out.println("statement arg 6 done");


                    preparedStatement.executeUpdate();
                    System.out.println("Done update process");
                    break;
            }
        }catch(SQLException sql){
            System.out.println("THREW SQL ERROR");
            sql.printStackTrace();
        }

    }


}
