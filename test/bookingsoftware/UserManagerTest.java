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
public class UserManagerTest {

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
        //non-numeric ID/phone error
        //incomplete fields error
        //correct registration
        //user already exists

        System.out.println("addUser");
        int id = 0;
        String name = "";
        String password = "";
        String email = "";
        long phone = 0L;
        UserManager instance = new UserManager();
        int expResult = 0;
        int result = instance.addUser(id, name, password, email, phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of authenticateUser method, of class UserManager.
     */
    @Test
    public void testAuthenticateUser() {
        UserManager instance = new UserManager();

        //incomplete fields error
        boolean result2 = instance.authenticateUser(0,"");
        assertEquals(false,result2);
        //wrong details
        boolean result3 = instance.authenticateUser(99,"wrongPassword121212");
        assertEquals(false,result3);
        //successful login
        boolean result4 = instance.authenticateUser(99, "admin123");
        assertEquals(true,result4);

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
