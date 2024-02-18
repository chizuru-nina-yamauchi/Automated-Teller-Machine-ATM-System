package file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.User;

public class FileManagerTest {

    private FileManager fileManager;

    // This method will be executed before each test method
    @BeforeEach
    public void setUp() {
        fileManager = FileManager.getInstance();
    }

    // This method will be executed after each test method
    @AfterEach
    public void tearDown() {
        // Clean up or reset any resources if needed
    }

    @Test
    public void testAuthenticateUser() {
        // Assuming there is a user "testUser" with password "testPassword" and balance 100000.0
        User loginUser = fileManager.authenticateUser("testUser", "testPassword");
        assertEquals("testUser", loginUser.getAccountUsername());
        assertEquals("testPassword", loginUser.getAccountPassword());
        assertEquals(100000.0, loginUser.getAccountBalance());
    }

    @Test
    public void testAuthenticateUserInvalidCredentials() {
        // Assuming there is no user with these credentials
        User authenticatedUser = fileManager.authenticateUser("nonexistentUser", "wrongPassword");
        assertNull(authenticatedUser);
    }

    @Test
    public void testGetBalance() {
        // Assuming there is a user "testUser" with balance 100000.0
        double balance = fileManager.getBalance("testUser");
        assertEquals(100000.0, balance);
    }

    @Test
    public void testGetBalanceUserNotFound() {
        // Assuming there is no user with this username
        double balance = fileManager.getBalance("nonexistentUser");
        assertEquals(-1, balance, 0.001);
    }
    /*
    @Test
    public void testUpdateBalance() {
        // Assuming there is a user "testUser" with balance 100000.0
        fileManager.updateBalance("testUser", 25000.0);
        double updatedBalance = fileManager.getBalance("testUser");
        assertEquals(25000.0, updatedBalance);
    }
    */



    @Test
    public void testGetUserByUsername() {
        // Assuming there is a user "testUser" with balance 100000.0
        User user = fileManager.getUserByUsername("testUser");
        assertEquals("testUser", user.getAccountUsername());
        assertEquals("testPassword", user.getAccountPassword());
        assertEquals(100000.0, user.getAccountBalance());
    }

    @Test
    public void testGetUserByUsernameUserNotFound() {
        // Assuming there is no user with this username
        User user = fileManager.getUserByUsername("nonexistentUser");
        assertNull(user);
    }
    /*
    @Test
    public void testUpdatePassword() {
        // Assuming there is a user "testUser" with password "testPassword"
        fileManager.updatePassword("testUser", "newPassword");
        User updatedUser = fileManager.authenticateUser("testUser", "newPassword");
        assertEquals("testUser", updatedUser.getAccountUsername());
        assertEquals("newPassword", updatedUser.getAccountPassword());
    }
    */



}