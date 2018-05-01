package software.design.teamorangecalsync;

//singleton for database management
public class Database {

    private static final Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {
    }

    //TODO: @Nikhil. Add your methods for database management here. Download, upload, update, etc.
}
