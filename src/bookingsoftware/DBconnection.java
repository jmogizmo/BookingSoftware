/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.derby.impl.sql.compile.DB2LengthOperatorNode;
/**
 *
 * @author dante
 */
public class DBconnection {
    
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:derby:BookingSoftwareDB";
    
    Connection conn;
    
    public static void main(String[] args) {
        DBconnection DB = new DBconnection();
        System.out.println(DB.getConnection());
    }
    
    public DBconnection() {
        establishConnection();
    }
    public Connection getConnection() {
        return this.conn;
    }
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL+ " Connection is successful.");
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
}
