package Admin.AdminFunctionalities;

import Utilities_Package.FileManager.AllLoginFile;
import Admin.AdminDashboard;
import java.io.File;

public class ViewAllLogins {
    public void showAllLogins() {
        AdminDashboard adminDashboard = new AdminDashboard();
        
        // Correct file path
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");

        // Correctly invoke the method that reads logins
        AllLoginFile allLoginFile = new AllLoginFile();
        allLoginFile.readAllLogins(loginFile);  // Changed from AllLoginFile() to readAllLogins()

        adminDashboard.displayAdminMenu();
    }
}
