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
    public void test2(){
        DeleteAccount a= new DeleteAccount();
        assertEquals("Admin",a.getUserRole("kadir@gmail.com"));

    }

    @Test
    public void test3(){
        DeleteAccount a= new DeleteAccount();
        assertEquals("Admin",a.getUserRole("sumaiya@gmail.com"));

    }


}