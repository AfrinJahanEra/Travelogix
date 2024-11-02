package Admin;


import java.io.File;

import src.Utilities.FileManager.AllLoginFile;

public class ViewAllLogins {

    public void showAllLogins() {

        AdminDashboard adminDashboard=new AdminDashboard();
        
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");

        AllLoginFile allLoginFile = new AllLoginFile();
        allLoginFile.readAllLogins(loginFile);  

        adminDashboard.displayAdminMenu();

    }
}
