/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package bookingsoftware;

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
public class BookingControllerTest {
    
    public BookingControllerTest() {
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
    public void tearDown(){
    }
    
     /**
     * Test of cancelBooking method, of class BookingController.
     */
    @Test
    public void testCancelBooking() {
        //successfully cancelled booking
        //no booking selected (null)
        System.out.println("cancelBooking");
        BookingController instance = null;
        instance.cancelBooking();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testCreateBooking() {
        //booking not available error
        //successful booking
        System.out.println("createBooking");
        BookingController instance = null;
        instance.createBooking();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
