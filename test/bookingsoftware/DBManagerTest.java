/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package bookingsoftware;

import java.sql.Connection;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jmone
 */
public class DBManagerTest {

    public DBManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testReturnisBooked() throws Exception {
        //room is booked
        //room is not booked
        System.out.println("returnisBooked");
        String time = "";
        String building = "";
        String room = "";
        String date = "";
        int expResult = 0;
        int result = DBManager.returnisBooked(time, building, room, date);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateBooking() throws Exception {
        //succesful create booking
        userInfo user = new userInfo();
        String buildingCode = "WG";
        String roomCode = "";
        String time = "";
        String date = "";
        boolean expResult = false;
        boolean result = DBManager.createBooking(user, buildingCode, roomCode, time, date);
        assertEquals(expResult, result);
        //already booked error

    }

}
