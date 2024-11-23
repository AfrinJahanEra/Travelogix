import org.junit.jupiter.api.Test;

import Authentication.Authentication;
import Authentication.Login;
import static org.junit.jupiter.api.Assertions.*;


class Test_isEmailRegistered {

    @Test
    public void testEmailExistsInRegisteredList() {
        Login login = new Login();
        assertTrue(login.isEmailRegistered("ramisa@gmail.com"));
    }

    @Test
    public void testEmailDoesNotExistInRegisteredList() {
        Login login = new Login();
        assertFalse(login.isEmailRegistered("unregistered@gmail.com"));
    }

    @Test
    public void testEmailIsEmpty() {
        Login login = new Login();
        assertFalse("Empty email should not be registered", login.isEmailRegistered(""));
    }

}
