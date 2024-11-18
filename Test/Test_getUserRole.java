import org.junit.jupiter.api.Test;

import Authentication.DeleteAccount;

import static org.junit.jupiter.api.Assertions.*;


class Test_getUserRole {
    
    @Test
    public void test1(){
        DeleteAccount a= new DeleteAccount();
        assertEquals("Traveler",a.getUserRole("rahim@gmail.com"));

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