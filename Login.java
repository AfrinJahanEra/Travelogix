import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LogIn extends Authentication{
    public void logIn() throws NoSuchAlgorithmException  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Log In");
        System.out.println("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.println("Enter password: ");
        String password = getPasswordInput();
        String encrypted_pass =encryptPassword(password);
    
        if (isValidUser(email, encrypted_pass)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }
    
        scanner.close();
    }
}
