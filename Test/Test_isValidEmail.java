import org.junit.jupiter.api.Test;


import Authentication.SignUp;
import static org.junit.jupiter.api.Assertions.*;


class Test_isValidEmail {
    
    @Test
    public void test1(){
        SignUp a= new SignUp();
        assertTrue(a.isValidEmail("ramisa@gmail.com"));

    }

    @Test
    public void test2(){
        SignUp a= new SignUp();
        assertTrue(a.isValidEmail("rina"));

    }


    @Test
    public void test3(){
        SignUp a= new SignUp();
        assertTrue(a.isValidEmail("Rina@yahoo.com"));

    }
}
