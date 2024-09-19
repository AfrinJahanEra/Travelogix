package Utilities_Package.Musers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteAccount {

    Logout logout = new Logout();

    public DeleteAccount(Login login) {
        //TODO Auto-generated constructor stub
    }

    public void deleteAccount(Login login) {
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");
        List<String> updatedLoginInfo = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loginInfo = line.split(",");
           
                if (!loginInfo[0].equalsIgnoreCase(login.getCurrentEmail())) {
                    updatedLoginInfo.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(loginFile))) {
            for (String loginEntry : updatedLoginInfo) {
                writer.write(loginEntry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Account deleted successfully.");
        
        logout.logout();
    }
}
