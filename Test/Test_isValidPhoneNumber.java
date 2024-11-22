import org.junit.jupiter.api.Test;


import Authentication.SignUp;
import static org.junit.jupiter.api.Assertions.*;


class Test_isValidPhoneNumber{
    
    @Test
    public void test1(){
        SignUp a= new SignUp();
        assertTrue(a.isValidPhoneNumber("01841748183"));

    }

    @Test
    public void test2(){
        SignUp a= new SignUp();
        assertTrue(a.isValidPhoneNumber("013467647"));

    }


    @Test
    public void test3(){
        SignUp a= new SignUp();
        assertTrue(a.isValidPhoneNumber("11841748183"));

    }
}
