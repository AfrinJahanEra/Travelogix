
import org.junit.jupiter.api.Test;

import Authentication.Authentication;

import static org.junit.jupiter.api.Assertions.*;


class Test_isEmailUnique {
    
    @Test
    public void test1(){
        Authentication a= new Authentication();
        assertFalse(a.isEmailUnique("ramisa@gmail.com"));

    }

    @Test
    public void test2(){
        Authentication a= new Authentication();
        assertTrue(a.isEmailUnique("rina@gmail.com"));

    }


}
