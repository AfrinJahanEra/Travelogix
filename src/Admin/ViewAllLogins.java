package Admin;

import Utilities.FileManager.AllLoginFile;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ViewAllLogins {

    public void showAllLogins() throws IOException, NoSuchAlgorithmException {

        AdminDashboard adminDashboard = new AdminDashboard();
        
        File loginFile;
        loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\users.txt");

        AllLoginFile allLoginFile = new AllLoginFile();
        allLoginFile.readAllLogins(loginFile);  

        adminDashboard.displayAdminMenu();

    }
}
