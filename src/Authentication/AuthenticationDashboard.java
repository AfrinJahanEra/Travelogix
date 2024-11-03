package Authentication;

import Admin.AdminDashboard;
import Transport.TransportDashboard;
import Traveler.TravelerDashboard;
import java.io.IOException;

public class AuthenticationDashboard {

    // Display dashboard based on user role
    public void displayDashboard(String role) throws IOException {
        switch (role) {
            case "Admin":
                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.displayAdminMenu();
                break;
            case "Traveler":
                TravelerDashboard travelerDashboard = new TravelerDashboard();
                travelerDashboard.showDashboard();
                break;
            case "Transport":
                TransportDashboard transportDashboard = new TransportDashboard();
                transportDashboard.dashboard();
                break;
            default:
                System.out.println("Invalid role. Please contact support.");
                break;
        }
    }
}
