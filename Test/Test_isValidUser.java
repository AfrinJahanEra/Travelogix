import org.junit.jupiter.api.Test;

import Authentication.Authentication;

import static org.junit.jupiter.api.Assertions.*;


class Test_isValidUser {
    
    @Test
    public void test1(){
        Authentication a= new Authentication();
        assertTrue(a.isValidUser("ramisa@gmail.com"));

    }

    @Test
    public void test2(){
        Authentication a= new Authentication();
        assertTrue(a.isValidUser("rina@gmail.com"));

    }


}
