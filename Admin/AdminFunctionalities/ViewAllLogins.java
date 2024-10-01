package Admin.AdminFunctionalities;

import Utilities_Package.FileManager.AllLoginFile;
import Admin.AdminDashboard;
import java.io.File;

public class ViewAllLogins {
    public void showAllLogins() {
        AdminDashboard adminDashboard = new AdminDashboard();
        
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");

        AllLoginFile allLoginFile = new AllLoginFile();
        allLoginFile.readAllLogins(loginFile);  

        adminDashboard.displayAdminMenu();
    }
}
