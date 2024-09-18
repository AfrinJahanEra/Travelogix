package Authentication;

import java.io.*;
import java.util.*;

public class AuthService {
    private static final String USERS_FILE = "data/users.txt";
    private static final String AGENCIES_FILE = "data/transport_agencies.txt";
    private static final String ADMINS_FILE = "data/admins.txt";

    public static boolean signUp(String email, String password, String contactNumber, String role) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getFile(role), true));
            writer.write(email + "," + password + "," + contactNumber);
            writer.newLine();
            writer.close();
            System.out.println("Sign up successful!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String login(String email, String password, String role) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFile(role)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(email) && userData[1].equals(password)) {
                    reader.close();
                    return email;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFile(String role) {
        switch (role) {
            case "User":
                return USERS_FILE;
            case "TransportAgency":
                return AGENCIES_FILE;
            case "Admin":
                return ADMINS_FILE;
            default:
                throw new IllegalArgumentException("Unknown role");
        }
    }
}
