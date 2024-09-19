package Utilities_Package.Musers;

public class Logout {
    private void logout() {
        currentEmail = null;
        System.out.println("Logged out. Returning to login page...");
        login();
    }
}
