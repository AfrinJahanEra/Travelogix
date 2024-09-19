package Admin.AdminFunctionalities.ManageAccount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteAccount {
    private void deleteAccount() {
        File loginFile = new File("login.txt");
        List<String> updatedLoginInfo = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loginInfo = line.split(",");
                if (!loginInfo[0].equalsIgnoreCase(currentEmail)) {
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
        logout();
    }
}
