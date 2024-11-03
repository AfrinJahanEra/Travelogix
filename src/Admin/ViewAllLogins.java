package Admin;

import Admin.AdminDashboard;

import Utilities.FileManager.AllLoginFile;

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
