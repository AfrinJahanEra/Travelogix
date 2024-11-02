import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;


public class Authentication extends  PasswordField{
    private static final String USERS_FILE = "users.txt";
    private static final int MIN_PASSWORD_LENGTH = 8;

    public boolean isEmailUnique(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
        }
        return true;
    }

    // Method to save user information
    public void saveUserInfo(String role, String name, String phoneNumber, String email, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            if (role.equals("1")) role = "Admin";
            else role = "Traveler";
            writer.write(role + ", " + name + ", " + phoneNumber + ", " + email + ", " + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e + " occurred while saving user information.");
        }
    }

    // Method to check if the user credentials are valid
    public boolean isValidUser(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 1 && userDetails[3].equalsIgnoreCase(email) && userDetails[4].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid email or password.");
        }
        return false;
    }

    // Method to get password input with masking
    public String getPasswordInput() {

       
        String password = PasswordField.readPassword("");        
        return password;
    }


    public String encryptPassword(String password) throws NoSuchAlgorithmException{
        
        MessageDigest md= MessageDigest.getInstance("MD5");
        byte[] messageDigest= md.digest(password.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);

        return bigInt.toString(16);
         
        
    }
}
