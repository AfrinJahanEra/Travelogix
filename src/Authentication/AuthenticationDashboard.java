
package Authentication;

import Admin.AdminDashboard;
import Transport.TransportDashboard;
import Traveler.TravelerDashboard;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AuthenticationDashboard {

    private final AdminDashboard adminDashboard;
    private final TravelerDashboard travelerDashboard;
    private final TransportDashboard transportDashboard;

    public AuthenticationDashboard() {
        adminDashboard = new AdminDashboard();
        travelerDashboard = new TravelerDashboard();
        transportDashboard = new TransportDashboard();
    }

    public void displayDashboard(String role) throws IOException, NoSuchAlgorithmException {
        switch (role) {
            case "Admin" -> adminDashboard.displayAdminMenu();
            case "Traveler" -> travelerDashboard.showDashboard();
            case "Transport" -> transportDashboard.dashboard();
            default -> System.out.println("Invalid role. Please contact support.");
        }
    }
}
