package com.example.nikhilgaur.myapplication;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseAccess {
    public static ArrayList<String> getAllCalendarNames(String username, Connection conn) {
        ArrayList<String> nameList = new ArrayList<String>();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement("select calendarname from usertable where usertable.username = ? ");
            preparedStatement.setString(1, username);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
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

    public static ArrayList<String> getAllEventsForCalendar(String username, String calendarName, Connection conn) {
        ArrayList<String> eventList = new ArrayList<String>();
        PreparedStatement preparedStatement;
        ResultSet resultset = null;
        try {
            //System.out.println("Is connection null?: " + conn == null);
            preparedStatement = conn.prepareStatement("selec assignments from canvasevent where canvasevent.username = ? and canvasevent.canvascalendarname = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, calendarName);
            resultset = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultset.next()) {

                eventList.add(resultset.getString("assignments"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return eventList;
    }

    public static void insertEvents(String calendarName, String username, String eventType, String assignmentName, String dueDate, String dueTime, String description, Connection conn) {
        PreparedStatement preparedStatement;
        eventType = eventType.toLowerCase();
        try {
            switch (eventType) {
                case "canvas":
                    System.out.println("inserting canvas event");
                    preparedStatement = conn.prepareStatement("insert into canvasevent values(?,?,?,?,?,?)");
                    System.out.println("preparing statement");
                    preparedStatement.setString(1, calendarName);
                    System.out.println("statement arg 1 done");

                    preparedStatement.setString(2, username);
                    System.out.println("statement arg 2 done");

                    preparedStatement.setString(3, assignmentName);
                    System.out.println("statement arg 3 done");

                    preparedStatement.setString(4, dueDate);
                    System.out.println("statement arg 4 done");

                    preparedStatement.setString(5, dueTime);
                    System.out.println("statement arg 5 done");

                    preparedStatement.setString(6, description);
                    System.out.println("statement arg 6 done");


                    preparedStatement.executeUpdate();
                    System.out.println("Done update process");
                    break;
            }
        } catch (SQLException sql) {
            System.out.println("INSERT EVENT: SQL ERROR THROWN");
            sql.printStackTrace();
        }

    }

    public static void deleteEvents(String eventType, String calendarName, String username, String assignmentName, String dueDate, String dueTime,
                                    String description, Connection conn) {


        PreparedStatement preparedStatement;
        eventType = eventType.toLowerCase();
        try {
            switch (eventType) {
                case "canvas":
                    System.out.println("deleting canvas event");
                    preparedStatement = conn.prepareStatement("delete from canvasevent where canvascalendarname = ? and username = ? and assignments = ?");
                    System.out.println("preparing statement");

                    preparedStatement.setString(1, calendarName);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, assignmentName);


                    preparedStatement.executeUpdate();
                    System.out.println("Done update process");
                    break;
            }


        } catch (SQLException sql) {
            System.out.println("DELETE EVENT: SQL ERROR THROWN");

        }

    }
    public static void updateEvents(String calendarName,
                                    String username,
                                    String newassignmentName,
                                    String dueDate,
                                    String dueTime,
                                    String description,Connection conn){
        PreparedStatement preparedStatement;
        String eventType = "canvas";
        eventType = eventType.toLowerCase();

        try {
            switch (eventType) {
                case "canvas":
                    System.out.println("updating canvas event");

                    preparedStatement = conn.prepareStatement("update canvasevent set assignments = ? where canvascalendarname = ? and username = ? and assignments = ?");
                    System.out.println("preparing statement");

                    preparedStatement.setString(1, "ee 379k sucks");//new assignment name
                    preparedStatement.setString(2, "canvascal1");
                    preparedStatement.setString(3, "ng9366");
                    preparedStatement.setString(4,"ds");//old assignment name


                    preparedStatement.executeUpdate();
                    System.out.println("Done update process");
                    break;
            }


        } catch (SQLException sql) {
            System.out.println("UPDATE EVENT: SQL ERROR THROWN");

        }
    }
    public static void simpleInsert(){
        PreparedStatement preparedStatement;
        System.out.println("Inside SIMPLE INSERT");
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("NO CLASS DRIVER");
            e.printStackTrace();
        }
        System.out.println("Driver loaded");
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/calendarinfo?autoReconnect=true&useSSL=false" , "scott", "tiger");
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("insert into canvasevent values('canvascal3','t','y','g','d','g')");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("SIMPLE INSERT: SQL ERROR THROWN");

            e.printStackTrace();
        }



        System.out.println("Database connected");
    }
}
