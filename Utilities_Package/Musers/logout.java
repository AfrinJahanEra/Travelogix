package Utilities_Package.Musers;

public class Logout {
    public void logout() {
        Login login = new Login();
        
        login.setCurrentEmail(null);
        System.out.println("Logged out. Returning to login page...");
        
        login.login();
    }
}
