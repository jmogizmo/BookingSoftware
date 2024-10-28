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
import java.util.Random;

/**
 *
 * @author jmone
 */
public class UserManagerTest {

    Random rand = new Random();

    public UserManagerTest() {
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

    /**
     * Test of addUser method, of class UserManager.
     */
    @Test
    public void testAddUser() {
        UserManager instance = new UserManager();
        instance.loadUsers();
        //incomplete fields error
        int result1 = instance.addUser(0, "", "", "email", 010101);
        int expResult1 = 0;
        assertEquals(expResult1, result1);
        //correct registration
        int randomID = 100000 + rand.nextInt(900000);
        
        int result2 = instance.addUser(randomID, "randomName", "randomPassword", "randomEmail@gmail.com", 88337475);
        int expResult2 = 1;
        assertEquals(expResult2,result2);
        
        //user already exists
        int result3 = instance.addUser(99, "admin", "admin123", "adminEmail@gmail.com", 1234567890);
        int expResult3 = -1;
        assertEquals(expResult3, result3);
    }

    /**
     * Test of authenticateUser method, of class UserManager.
     */
    @Test
    public void testAuthenticateUser() {
        UserManager instance = new UserManager();

        //incomplete fields error
        boolean result2 = instance.authenticateUser(0, "");
        assertEquals(false, result2);
        //wrong details
        boolean result3 = instance.authenticateUser(99, "wrongPassword121212");
        assertEquals(false, result3);
        //successful login
        boolean result4 = instance.authenticateUser(99, "admin123");
        assertEquals(true, result4);

        System.out.println("authenticateUser");
    }

    /**
     * Test of loadUsers method, of class UserManager.
     */
    @Test
    public void testLoadUsers() {
        System.out.println("loadUsers");
        UserManager instance = new UserManager();
        instance.loadUsers();
    }

}
