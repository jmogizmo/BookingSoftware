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
    public static Connection conn;

    public static void main(String[] args) {
        DBManager DB = new DBManager();
        System.out.println(DB.getConnection());
//        try {
//
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASS);
                System.out.println(URL + " Connection is successful.");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    public void appendToField(String table, String data) throws SQLException {

        String insertSQL = "INSERT INTO " + table + " VALUES (" + data + ")";

        System.out.println("#######################\n" + insertSQL + "\n#######################");
        try ( PreparedStatement PS = conn.prepareStatement(insertSQL)) {

            PS.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public userInfo returnUserInfo(String field, String target) throws SQLException {

        // E.G: FIELD = FIRST_NAME TARGET = martin
        ResultSet rs = null;
        userInfo result = null;
        String command = "SELECT * FROM USERINFO WHERE" + field + "=" + target;
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command);
            if (rs.next()) {

                result = new userInfo();

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
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public userInfo returnUserInfo(int target) throws SQLException {

        // E.G: FIELD = studentID TARGET = 14
        ResultSet rs = null;
        userInfo result = null;
        String command = "SELECT * FROM USERINFO WHERE STUDENT_ID = " + target;
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command);

            if (rs.next()) {

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

            } else {
                System.out.println("No user found with given STUDENT_ID: " + target);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
        }
        return result;
    }

    public Map returnAllUsers() throws SQLException {

        Map<Integer, userInfo> userMap = new HashMap<>();
        ResultSet rs = null;
        String command = "SELECT * FROM USERINFO";
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command);
            while (rs.next()) {
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

                userMap.put(STUDENT_ID, result);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
        }

        return userMap;
    }

    public int returnisBooked(String time, String building, int room, String date) throws SQLException {
        ResultSet rs = null;
        String command = "SELECT \"" + time + "\" FROM BOOKINGS WHERE "
                + "BUILDINGCODE = '" + building
                + "' AND ROOMCODE = " + room
                + " AND BOOKINGDATE = '" + date + "'";
        Statement statement = conn.createStatement();
        int booked = -1; // Error occurred:

        try {
            rs = statement.executeQuery(command);
            if (rs.next()) {

                boolean isBooked = rs.getBoolean(time);

                if (isBooked) {
                    booked = 1; // TimeSlot is Booked
                } else if (!isBooked) {
                    booked = 0; // TimeSlot is NOT Booked
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
        }
        return booked;
    }

    public void removeFromField(String table, String data) {
        String deleteSQL = "DELETE FROM " + table + " WHERE " + data;

        try ( PreparedStatement PS = conn.prepareStatement(deleteSQL)) {

            PS.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean createBooking(userInfo user, String buildingCode, String roomCode, String time, String date) throws SQLException {

        String booking = user.getStudentID() + ", '" + user.getName()
                + "', '" + buildingCode + "', " + roomCode + ", '" + date + "', '" + time + "'";

        String createSQL = "INSERT INTO BOOKEDROOMS (STUDENT_ID, FIRST_NAME, BUILDINGCODE, ROOMCODE, DATES, TIMES) VALUES "
                + "(" + booking + ")";

        System.out.println("#######################\n" + booking + "\n#######################");
        try ( PreparedStatement PS = conn.prepareStatement(createSQL)) {

            PS.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public int cancelBooking(int BOOKINGID) {
        String deleteSQL = "DELETE FROM BOOKEDROOMS WHERE BOOKING_ID = "+BOOKINGID;
        
        try ( PreparedStatement PS = conn.prepareStatement(deleteSQL)) {

            PS.executeUpdate();
            return 0;// Deleted

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return -1;// error
    }

    public ArrayList<String> returnUserBookings(int studentID) throws SQLException {

        ResultSet rs = null;
        ArrayList<String> result = new ArrayList<String>();
        String command = "SELECT * FROM BOOKEDROOMS WHERE STUDENT_ID = " + studentID;
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command);

            //System.out.println("BOOKINGS FOR : " + studentID);

            while (rs.next()) {

                int BOOKING_ID = rs.getInt("BOOKING_ID");
                String FIRST_NAME = rs.getString("FIRST_NAME");
                String BUILDINGCODE = rs.getString("BUILDINGCODE");
                String ROOMCODE = rs.getString("ROOMCODE");
                String DATES = rs.getString("DATES");
                String TIMES = rs.getString("TIMES");

                String data = BOOKING_ID + " " + FIRST_NAME + " " + BUILDINGCODE + " " + ROOMCODE + " " + DATES + " " + TIMES;

                result.add(data);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }
}
