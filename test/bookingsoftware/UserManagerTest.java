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
        System.out.println("authenticateUser");
        int id = 0;
        String password = "";
        UserManager instance = new UserManager();
        boolean expResult = false;
        boolean result = instance.authenticateUser(id, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadUsers method, of class UserManager.
     */
    @Test
    public void testLoadUsers() {
        System.out.println("loadUsers");
        UserManager instance = new UserManager();
        instance.loadUsers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
