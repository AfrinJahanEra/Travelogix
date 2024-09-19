package Admin.AdminFunctionalities.ManageAccount;
import Utilities_Package.Musers.Login;

public class ShowloginInfoOfAdmin {
    void showLoginInfo() {

        Login login = new Login();
        System.out.println("Logged in as: " + login.getCurrentEmail());
        System.out.println("Password: ******");
    }
}
