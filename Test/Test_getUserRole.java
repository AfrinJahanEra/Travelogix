import org.junit.jupiter.api.Test;

import Authentication.DeleteAccount;

import static org.junit.jupiter.api.Assertions.*;


class Test_getUserRole {
    
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


}