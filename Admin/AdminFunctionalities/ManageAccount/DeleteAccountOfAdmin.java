package Admin.AdminFunctionalities.ManageAccount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
