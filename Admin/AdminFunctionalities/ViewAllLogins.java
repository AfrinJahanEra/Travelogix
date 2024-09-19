package Admin.AdminFunctionalities;

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

        
        try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
            String line;
            System.out.println("List of all logins:");
            while ((line = reader.readLine()) != null) {
              
                String[] loginInfo = line.split(",");
                
                System.out.println("Email: " + loginInfo[0] + ", Password: " + loginInfo[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The login file was not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        adminDashboard.displayAdminMenu();
    }
}
