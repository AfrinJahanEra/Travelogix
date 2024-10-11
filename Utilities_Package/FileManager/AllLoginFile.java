package Utilities_Package.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AllLoginFile {

    public void readAllLogins(File loginFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
            String line;
            System.out.println("List of all logins:");
            while ((line = reader.readLine()) != null) {
                String[] loginInfo = line.split(",");

                if (loginInfo.length >= 2) {
                    System.out.println("Email: " + loginInfo[0] + ", Password: " + loginInfo[1]);
                } else {
                    System.out.println("Malformed login entry: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The login file was not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
