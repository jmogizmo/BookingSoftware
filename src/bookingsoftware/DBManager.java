package bookingsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Use java.sql.Statement
import java.sql.ResultSet; // Use java.sql.ResultSet
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 *
 * @author dante
 */
public class DBManager {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:derby:BookingSoftwareDB";
    public static Connection conn;

    public static void main(String[] args) {
        DBManager DB = new DBManager();
        System.out.println(DB.getConnection());
        try {
        Statement statement = conn.createStatement();
        statement.executeUpdate("SET SCHEMA ROOT");

        System.out.println("\n\n\n********DEBUGGING********");

        System.out.println("ADD USER");
        
            //System.out.println("USERINFO" + " 421, 'user', 'us120', 908765476, '00000' ");
            appendToField("USERINFO", " 421, 'user', 'us120', 908765476, '00000' ");
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
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
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

    public static void appendToField(String table, String data) throws SQLException {
        // data e.g: " 421, 'user', 'us120', 908765476, '00000' "
        //String insertSQL = "INSERT INTO USERINFO"+ " VALUES (?,?,?,?,?)";
        String insertSQL = "SELECT * FROM "+table;
        
        try (PreparedStatement PS = conn.prepareStatement(insertSQL)) {
            
            String[] values = data.split(", ");
            for (int i = 0; i < values.length; i++) {
                PS.setString(i + 1, values[i].replace("'", ""));
                
            }
            
            PS.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        // You can implement your command execution logic here.
    }

    public userInfo returnUserInfo(String field, String target) throws SQLException {
        // Return generic type data from USERINFO
        // E.G: FIELD = studentID TARGET = martin

        ResultSet rs = null;
        userInfo result = null;
        String command = "SELECT * FROM USERINFO WHERE" + field + "=" + target;
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command);
            while (rs.next()) {

                int STUDENT_ID = rs.getInt("STUDENT_ID");
                String FIRST_NAME = rs.getString("FIRST_NAME");
                String EMAIL = rs.getString("EMAIL");
                long PHONE = rs.getLong("PHONE");
                String RSPASSWORD = rs.getString("PASSWORD");

                if (target.equals(FIRST_NAME) || target.equals(EMAIL) || target.equals(RSPASSWORD)) {
                    result.setStudentID(STUDENT_ID);
                    result.setName(FIRST_NAME);
                    result.setEmail(EMAIL);
                    result.setPhone(PHONE);
                    result.setPassword(RSPASSWORD);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        rs.close();
        statement.close();
        conn.close();
        return result;
    }

    public userInfo returnUserInfo(String field, int target) throws SQLException {
        // Return generic type data from USERINFO
        // E.G: FIELD = studentID TARGET = 14

        ResultSet rs = null;
        userInfo result = null;
        String command = "SELECT * FROM USERINFO WHERE" + field + "=" + target;
        Statement statement = conn.createStatement();

        try {
            rs = statement.executeQuery(command);
            while (rs.next()) {

                int STUDENT_ID = rs.getInt("STUDENT_ID");
                String FIRST_NAME = rs.getString("FIRST_NAME");
                String EMAIL = rs.getString("EMAIL");
                long PHONE = rs.getLong("PHONE");
                String PASSWORD = rs.getString("PASSWORD");

                if (target == STUDENT_ID) {
                    result.setStudentID(STUDENT_ID);
                    result.setName(FIRST_NAME);
                    result.setEmail(EMAIL);
                    result.setPhone(PHONE);
                    result.setPassword(PASSWORD);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        rs.close();
        statement.close();
        conn.close();
        return result;
    }
}
