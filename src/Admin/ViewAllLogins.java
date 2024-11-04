package Admin;

import Utilities.FileManager.AllLoginFile;
import java.io.File;

public class ViewAllLogins {

    public void showAllLogins() {

        AdminDashboard adminDashboard = new AdminDashboard();
        
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\users.txt");

        AllLoginFile allLoginFile = new AllLoginFile();
        allLoginFile.readAllLogins(loginFile);  

        adminDashboard.displayAdminMenu();

    }
}
