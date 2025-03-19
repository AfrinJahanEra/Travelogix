package Test;

import org.junit.jupiter.api.Test;
import Authentication.Login;
import static org.junit.jupiter.api.Assertions.*;


class Test_isEmailRegistered {
    
    @Test
    public void test1(){
        Login a= new Login();
        assertFalse(a.isEmailRegistered("era@gmail.com"));

    }

    @Test
    public void test2(){
        Login a= new Login();
        assertTrue(a.isEmailRegistered("traveler@gmail.com"));

    }


}