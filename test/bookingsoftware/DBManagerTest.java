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
        
        
        int expResult;
        System.out.println("\nTEST CASE: CHECK IF BOOKED");
        // BOOKED : WG 403 19-08-2024 12:00             NOT BOOKED : WG 403 19-08-2024 17:00
        
        //room is booked
        System.out.println("\nTEST: ROOM IS BOOKED");
        
        String btime = "12:00";
        String bbuilding = "WG";
        String broom = "403";
        String bdate = "19-08-2024";
        expResult = 1;
        int result2 = DBManager.returnisBooked(btime, bbuilding, broom, bdate);
        assertEquals(expResult, result2);
        
        System.out.println(result2==expResult);
        
        //room is not booked
        System.out.println("\nTEST: ROOM IS NOT BOOKED");
        
        String vtime = "17:00";
        String vbuilding = "WG";
        String vroom = "403";
        String vdate = "19-08-2024";
        expResult = 0;
        int result = DBManager.returnisBooked(vtime, vbuilding, vroom, vdate);
        assertEquals(expResult, result);
        
        System.out.println(result==expResult);
        
        // INVALID PARAMETERS
        System.out.println("\nTEST: ROOM CHECK IS INVALID");
        String itime = "12:00";
        String ibuilding = "AB";
        String iroom = "701";
        String idate = "01-01-1998";
        expResult = -1;
        int result3 = DBManager.returnisBooked(itime, ibuilding, iroom, idate);
        assertEquals(expResult, result3);
        
        System.out.println(result3==expResult);
    }

    @Test
    public void testCreateBooking() throws Exception {
        
        DBManager.establishConnection();
        boolean expResult;
        
        userInfo user = new userInfo(9911,"testCreate", "t3$tcr3@t3", "testCreateEmail",321456987);
        user.toString();
        
        System.out.println("TEST CASE: CREATE BOOKINGS");
        
        //succesful create booking
        System.out.println("\nTEST: VACANT BOOKING");
        
        String vbuildingCode = "WG";
        String vroomCode = "403";
        String vtime = "17:00";
        String vdate = "19-08-2024";
        expResult = true;
        boolean result1 = DBManager.createBooking(user, vbuildingCode, vroomCode, vtime, vdate);
        assertEquals(expResult, result1);
        
        System.out.println(result1==expResult);
        
        //already booked error
        System.out.println("\nTEST: OCCUPIED BOOKING");
        String buildingCode = "WG";
        String roomCode = "403";
        String time = "12:00";
        String date = "19-08-2024";
        expResult = false;
        boolean result2 = DBManager.createBooking(user, buildingCode, roomCode, time, date);
        assertEquals(expResult, result2);
        
        System.out.println(result2==expResult);

    }

}
