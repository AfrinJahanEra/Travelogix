package Admin;

import Utilities.FileManager.AllLoginFile;
import java.io.File;

public class ViewAllLogins {

    public void showAllLogins() {

        AdminDashBoard adminDashboard = new AdminDashBoard();
        
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");

        AllLoginFile allLoginFile = new AllLoginFile();
        allLoginFile.readAllLogins(loginFile);  

        adminDashboard.displayAdminMenu();

    }
}
