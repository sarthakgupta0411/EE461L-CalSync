package software.design.teamorangecalsync;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//singleton for database management
public class Database {

    private static final Database ourInstance = new Database();
    private static final LinkedList<DatabaseCommand> commandQueue = new LinkedList<>();

    private Database() {
        //TODO: any initialization for the database that you need. This is initialized at run time
    }

    public static Database getInstance() {
        return ourInstance;
    }

    /**
     * These are for me to manage command queueing and merging
     */
    public static void enqueueCommand(DatabaseCommand command) {
        commandQueue.addLast(command);
    }
    public static boolean dequeueCommand() {
        // Here's where we dequeue the commands to be executed. This is the invoker class
        if(commandQueue.getFirst() != null) {
            commandQueue.getFirst().execute();
            commandQueue.removeFirst();
            return true;  //command dequeued
        }
        return false;  //no commands
    }
    public static DatabaseCommand findCommandWithEvent(Event target) {
        for(DatabaseCommand command : commandQueue) {
            if(command.getEvent().equals(target)) {
                return command;
            }
        }
        return null;
    }
    public static void invokeCommands() {
        // Invokes all the commands in order until there are no more
        boolean commandsLeft = true;
        while(commandsLeft) {
            commandsLeft = dequeueCommand();
        }
    }
    public static void removeFromQueue(DatabaseCommand command) {
        commandQueue.remove(command);
    }

    /**
     * TODO: @Nikhil. Add your methods for database management here. Download, upload, update, etc.
     */

    //implement
    public static List<Event> fetchEventsFromDatabase() {
        List<Event> eventsFromDatabase = new ArrayList<>(); //doesn't have to be arraylist, just a list

        //TODO: Add code for fetching the events and add them to the list of elements
        //TODO: Create events by passing it the properties and

        return eventsFromDatabase;
    }

}
