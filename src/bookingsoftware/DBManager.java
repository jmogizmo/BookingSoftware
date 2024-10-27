package bookingsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Use java.sql.Statement
import java.sql.ResultSet; // Use java.sql.ResultSet
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dante
 */
public class DBManager {

    private static final String USER_NAME = "root";
    private static final String PASS = "root";
    private static final String URL = "jdbc:derby://localhost:1527/Database";
    private static Connection conn;

    public static void main(String[] args) throws SQLException {
        DBManager DB = new DBManager();
        System.out.println(DB.getConnection());
        establishConnection();
        // DATABASE TESTING EXECUTABLE
    }

    public DBManager() {

    }

    public Connection getConnection() {
        return this.conn; // RETURN CONNECTION
    }

    public static int establishConnection() {
        if (conn == null) {
            try { // ATTEMPT TO CONNECT TO DATABASE
                conn = DriverManager.getConnection(URL, USER_NAME, PASS);
                System.out.println(URL + " Connection is successful.");
                return 0;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return -1;
    }

    public static void closeConnections() {
        if (conn != null) { // CLOSE DATABASE
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    public static void appendToField(String table, String data) throws SQLException { // MULTI-USE SQL METHOD TO APPEND TO ANY TABLE

        String insertSQL = "INSERT INTO " + table + " VALUES (" + data + ")"; // FORMULATE STATEMENT

        System.out.println("#######################\n" + insertSQL + "\n#######################");
        try ( PreparedStatement PS = conn.prepareStatement(insertSQL)) { // TRY EXECUTE STATEMENT

            PS.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR IF DID NOT WORK
        }
    }

    public static userInfo returnUserInfo(String field, String target) throws SQLException { // RETURN SPECIFIED USER BASED OFF FIELD

        // E.G: FIELD = FIRST_NAME TARGET = martin
        ResultSet rs = null;
        userInfo result = null;
        String command = "SELECT * FROM USERINFO WHERE" + field + "=" + target; // FORMULATE SQL STATEMENT
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command); // TRY EXECUTE SQL COMMAND
            if (rs.next()) { // IF QUERY HAS A ROW

                result = new userInfo(); // CREATE TEMP USER

                int STUDENT_ID = rs.getInt("STUDENT_ID");
                String FIRST_NAME = rs.getString("FIRST_NAME");
                String EMAIL = rs.getString("EMAIL");
                long PHONE = rs.getLong("PHONE");
                String RSPASSWORD = rs.getString("PASSWORD");
                
                result.setStudentID(STUDENT_ID);
                result.setName(FIRST_NAME);
                result.setEmail(EMAIL);
                result.setPhone(PHONE);
                result.setPassword(RSPASSWORD);
                
                // CREATE USER FROM ROW OF DATA
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR MESSAGE
        }
        return result; // RETURN USER
    }

    public static userInfo returnUserInfo(int target) throws SQLException { // RETURN SPECIFIED USER BASED OFF STUDENT ID

        // E.G: FIELD = studentID TARGET = 14
        ResultSet rs = null;
        userInfo result = null;
        String command = "SELECT * FROM USERINFO WHERE STUDENT_ID = " + target; // FORMULATE SQL STATEMENT
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command); // TRY EXECUTE SQL COMMAND

            if (rs.next()) { // IF QUERY HAS ROW

                result = new userInfo();

                int STUDENT_ID = rs.getInt("STUDENT_ID");
                String FIRST_NAME = rs.getString("FIRST_NAME");
                String EMAIL = rs.getString("EMAIL");
                Long PHONE = rs.getLong("PHONE");
                String PASSWORD = rs.getString("PASSWORD");

                result.setStudentID(STUDENT_ID);
                result.setName(FIRST_NAME);
                result.setEmail(EMAIL);
                result.setPhone(PHONE);
                result.setPassword(PASSWORD);
                
                // CREATE USER FROM QUERY

            } else {
                System.out.println("No user found with given STUDENT_ID: " + target); // ERROR MESSAGE: NOT FOUND
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR
        }
        return result; // RETURN USER
    }

    public static Map<Integer, userInfo> returnAllUsers() throws SQLException { // RETURN HASHMAP OF ALL USERS

        Map<Integer, userInfo> userMap = new HashMap<>(); // CREATE HASHMAP
        ResultSet rs = null;
        String command = "SELECT * FROM USERINFO"; // CREATE SQL STATEMENT
        Statement statement = conn.createStatement();

        try {
            
            rs = statement.executeQuery(command); // EXECUTE SQL COMMAND
            while (rs.next()) { // ITERATE THROUGH ALL ROWS
                userInfo result = new userInfo();

                int STUDENT_ID = rs.getInt("STUDENT_ID");
                String FIRST_NAME = rs.getString("FIRST_NAME");
                String EMAIL = rs.getString("EMAIL");
                long PHONE = rs.getLong("PHONE");
                String PASSWORD = rs.getString("PASSWORD");

                result.setStudentID(STUDENT_ID);
                result.setName(FIRST_NAME);
                result.setEmail(EMAIL);
                result.setPhone(PHONE);
                result.setPassword(PASSWORD);
                
                // CREATE TEMP USER FROM ROW OF QUERY
                
                userMap.put(STUDENT_ID, result);
                // PLACE TEMP USER INTO HASHMAP
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR MESSAGE
        }

        return userMap; // RETURN USER HASHMAP
    }

    public static int returnisBooked(String time, String building, int room, String date) throws SQLException { // RETURN SPECIFIFED BOOKING AVAILABILITY
        ResultSet rs = null;
        String command = "SELECT \"" + time + "\" FROM BOOKINGS WHERE "
                + "BUILDINGCODE = '" + building
                + "' AND ROOMCODE = " + room
                + " AND BOOKINGDATE = '" + date + "'"; // CREATE SQL STATMENT
        Statement statement = conn.createStatement();
        int booked = -1; // Error occurred:

        try {
            rs = statement.executeQuery(command); // TRY EXECUTE SQL COMMAND
            if (rs.next()) { // IF QUERY EXISTS

                boolean isBooked = rs.getBoolean(time); // READ QUERY DATA

                if (isBooked) {
                    booked = 1; // TimeSlot is Booked
                } else if (!isBooked) {
                    booked = 0; // TimeSlot is NOT Booked
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR
        }
        return booked; // RETURN ERROR
    }

    public static void removeFromField(String table, String data) { // MULTI-USE METHOD TO REMOVE DATA FROM A TABLE
        String deleteSQL = "DELETE FROM " + table + " WHERE " + data; // CREATE STATEMENT

        try ( PreparedStatement PS = conn.prepareStatement(deleteSQL)) { // TRY EXECUTE COMMAND

            PS.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR MESSAGE
        }
    }

    public static boolean createBooking(userInfo user, String buildingCode, String roomCode, String time, String date) throws SQLException {
        // METHOD TO CREATE BOOKING
        String booking = user.getStudentID() + ", '" + user.getName()
                + "', '" + buildingCode + "', " + roomCode + ", '" + date + "', '" + time + "'"; // FORMULATE DATA INSERTION

        String createSQL = "INSERT INTO BOOKEDROOMS (STUDENT_ID, FIRST_NAME, BUILDINGCODE, ROOMCODE, DATES, TIMES) VALUES "
                + "(" + booking + ")"; // COMBINE INTO SQL STATEMENT

        System.out.println("#######################\n" + booking + "\n#######################");
        try ( PreparedStatement PS = conn.prepareStatement(createSQL)) { // TRY EXECUTE COMMAND

            PS.executeUpdate();
            return true; // RETURN TRUE IF SUCCESSFUL

        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR MESSAGE
        }
        return false; // RETURN FALSE IF UNSUCCESSFUL
    }

    public static int cancelBooking(int BOOKINGID) { // METHOD TO REMOVE A BOOKING
        String deleteSQL = "DELETE FROM BOOKEDROOMS WHERE BOOKING_ID = " + BOOKINGID; // FORMULATE SQL STATEMENT

        try ( PreparedStatement PS = conn.prepareStatement(deleteSQL)) { // TRY EXECUTE COMMAND

            PS.executeUpdate();
            return 0;// Deleted

        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR
        }
        return -1;// error
    }

    public static ArrayList<String> returnUserBookings(int studentID) throws SQLException { // RETURN ARRAYLIST OF SPECIFIFED USER'S BOOKINGS

        ResultSet rs = null;
        ArrayList<String> result = new ArrayList<String>(); // CREATE ARRAYLIST
        String command = "SELECT * FROM BOOKEDROOMS WHERE STUDENT_ID = " + studentID; // CREATE SQL QUERY STATEMENT
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command); // TRY CREATE QUERY

            while (rs.next()) { // ITERATE THROUGH ROWS

                int BOOKING_ID = rs.getInt("BOOKING_ID");
                String FIRST_NAME = rs.getString("FIRST_NAME");
                String BUILDINGCODE = rs.getString("BUILDINGCODE");
                String ROOMCODE = rs.getString("ROOMCODE");
                String DATES = rs.getString("DATES");
                String TIMES = rs.getString("TIMES");

                String data = BOOKING_ID + " " + FIRST_NAME + " " + BUILDINGCODE + " " + ROOMCODE + " " + DATES + " " + TIMES;
                
                // CREATE STRING OF ROW 
                
                result.add(data);
                // ADD STRING TO ARRAYLIST
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR MESSAGE
        }
        return result; // RETURN ARRAY LIST
    }

    public static ArrayList<String> returnSearch(String buildingCode, String roomCode, String date) throws SQLException {
        
        ResultSet rs = null;
        ArrayList<String> result = new ArrayList<>(); // CREATE ARRAY LIST

        String SQLtimes = "\"12:00\", \"12:30\", \"13:00\", \"13:30\", \"14:00\", \"14:30\", \"15:00\", \"15:30\",\"16:00\", "
                + "\"16:30\", \"17:00\", \"17:30\", \"18:00\", \"18:30\", \"19:00\", \"19:30\", \"20:00\""; // STRING FOR TABLE COLUMNS

        String filter = "BUILDINGCODE='" + buildingCode
                + "' AND ROOMCODE=" + roomCode
                + " AND BOOKINGDATE='" + date + "'"; // STRING FOR SPECIFIED BOOKING

        String command = "SELECT " + SQLtimes + " FROM BOOKINGS WHERE " + filter; // COMBINE STRINGS INTO SQL STATEMENT
        
        Statement statement = conn.createStatement();
        
        String[] timeslots = {"12:00", "12:30", "13:00", "13:30", "14:00",
            "14:30", "15:00", "15:30", "16:00", "16:30",
            "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"}; // STRING ARRAY TO ITERATE THROUGH
        
        try {
            rs = statement.executeQuery(command); // TRY EXECUTE COMMAND
            if (rs.next()) { // IF QUERY HAS DATA
                for (String timeslot : timeslots) { // ITERATE THROUGH TIMESLOTS ARRAY
                    result.add(rs.getString(timeslot)); // ADD COLUMN INFORMATION TO ARRAY
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // RETURN ERROR MESSAGE
        }
        return result; // RETURN ARRAY
    }
}
