package Admin.AdminFunctionalities.ManageAccount;

import Utilities_Package.Musers.Login;
import Utilities_Package.Musers.Logout;
import Utilities_Package.Musers.DeleteAccount;

public class DeleteAccountOfAdmin {
    void deleteAccount() {
        Login login = new Login();
        Logout logout = new Logout();
        DeleteAccount deleteAccount= new DeleteAccount();
        deleteAccount.deleteAccount(login);
        
    }
}
