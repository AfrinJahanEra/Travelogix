package Admin.AdminFunctionalities;
import Utilities_Package.FileManager.AllLoginFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Admin.AdminDashboard;

public class ViewAllLogins {
    public void showAllLogins() {
        AdminDashboard adminDashboard=new AdminDashboard();
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");

        AllLoginFile allLoginFile= new AllLoginFile();
        allLoginFile.AllLoginFile(loginFile);
        
        adminDashboard.displayAdminMenu();
    }
}
