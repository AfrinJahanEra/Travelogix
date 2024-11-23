import org.junit.jupiter.api.Test;

import Authentication.DeleteAccount;

import static org.junit.jupiter.api.Assertions.*;


class Test_getUserRole {

    private static final String TEST_USERS_FILE = "test_users.txt";

    @Before
    public void setUp() throws IOException {
        // Create a temporary file with mock user data for testing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_USERS_FILE))) {
            writer.write("Admin, , , kadir@gmail.com\n");
            writer.write("Traveler, , , rahim@gmail.com\n");
            writer.write("Admin, , , sumaiya@gmail.com\n");
        }

        // Set the USERS_FILE in DeleteAccount to the test file
        DeleteAccount.USERS_FILE = TEST_USERS_FILE;
    }

    @After
    public void tearDown() {
        // Delete the temporary test file after tests
        new File(TEST_USERS_FILE).delete();
    }


    @Test
    public void testUserIsTraveler() {
        DeleteAccount deleteAccount = new DeleteAccount();
        assertEquals("Traveler", deleteAccount.getUserRole("rahim@gmail.com"));
    }

    @Test
    public void testUserIsAdmin() {
        DeleteAccount deleteAccount = new DeleteAccount();
        assertEquals("Admin", deleteAccount.getUserRole("kadir@gmail.com"));
    }

    @Test
    public void testAnotherAdminUser() {
        DeleteAccount deleteAccount = new DeleteAccount();
        assertEquals("Admin", deleteAccount.getUserRole("sumaiya@gmail.com"));
    }

    @Test
    public void testNonExistentUser() {
        DeleteAccount deleteAccount = new DeleteAccount();
        assertNull("null", deleteAccount.getUserRole("unknown@gmail.com"));
    }

    @Test
    public void testEmptyEmail() {
        DeleteAccount deleteAccount = new DeleteAccount();
        assertNull("null", deleteAccount.getUserRole(""));
    }


    @Test
    public void testNullEmail() {
        DeleteAccount deleteAccount = new DeleteAccount();
        assertNull("null", deleteAccount.getUserRole("null"));
    }


}