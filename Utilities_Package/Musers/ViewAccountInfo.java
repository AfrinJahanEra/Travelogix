package Utilities_Package.Musers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewAccountInfo {
        private static void viewLoginInfo() {
        File loginFile = new File("login.txt");

        System.out.println("Stored login info:");
        try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Email: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
