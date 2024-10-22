package bookingsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Use java.sql.Statement
import java.sql.ResultSet; // Use java.sql.ResultSet
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
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
        try {

            System.out.println("\n\n\n********TEST********");

            System.out.println("READ USER");

            //System.out.println("USERINFO" + " 421, 'user', 'us120', 908765476, '00000' ");
            //appendToField("USERINFO", "421, 'user', 'us120', 908765476, '00000', false ");
//            userInfo user = new userInfo();
//            user = returnUserInfo(19);
//            System.out.println(user.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }

        return userMap;
    }

    public boolean returnisBooked(String field, float time) {
        return false;
    }

    public boolean returnisBooked(float time) {
        return false;
    }
}
