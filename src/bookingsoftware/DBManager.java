package bookingsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Use java.sql.Statement
import java.sql.ResultSet; // Use java.sql.ResultSet

/**
 *
 * @author dante
 */
public class DBManager {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:derby:jdbc:derby:BookingSoftwareDB";
    public static Connection conn;

    public static void main(String[] args) {
        DBManager DB = new DBManager();
        System.out.println(DB.getConnection());
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

    public void appendToField(String table, String data) throws SQLException {
        // data e.g: " 421, 'user', 'us120', 908765476, '00000' "
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate("INSERT INTO " + table + " VALUES (" + data + ")");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        // You can implement your command execution logic here.
    }

    public userInfo returnFieldFromUserInfo(String field, String target) throws SQLException {
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

                try {
                    int intTarget = Integer.parseInt(target);
                    // WIP
                } catch (NumberFormatException e) {

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
