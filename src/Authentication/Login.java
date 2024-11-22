package Authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login  {
    private static final String USERS_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\users.txt";
    private SignUp signUpInstance = new SignUp();
    private AuthenticationDashboard authDashboard = new AuthenticationDashboard();
    private Authentication auth = new Authentication();


    public void logIn() throws NoSuchAlgorithmException, IOException {

        System.out.println(" ________________________________");
        System.out.println("|            LOG IN              |");
        System.out.println("|________________________________|");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter email: ");
        String email = scanner.nextLine().trim();

        if (isEmailRegistered(email)) {
            System.out.println("Enter password: ");
            String password = auth.getPasswordInput();
            String encryptedPass = auth.encryptPassword(password);

            if (auth.isValidUser(email, encryptedPass)) {
                System.out.println("Login successful!");

                // Retrieve user role and direct to appropriate dashboard
                String role = getUserRole(email);
                if (role != null) {
                    authDashboard.displayDashboard(role);
                } else {
                    System.out.println("Role not found or is invalid.");
                }

            } else {
                System.out.println("Invalid email or password. Try again.");
            }
        } else {
            System.out.println("You are not signed up with this email. Sign up first.");
            signUpInstance.signUp();
        }

        scanner.close();
    }

    // Check if email is registered in the system
    public boolean isEmailRegistered(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking the email: " + e.getMessage());
        }
        return false;  
    }

    // Get the role of the user based on the email from the file
    private String getUserRole(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return userDetails[0].trim(); // Assuming role is the first item in the array
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving the user role: " + e.getMessage());
        }
        return null; // Return null if role not found or error occurs
    }
}
