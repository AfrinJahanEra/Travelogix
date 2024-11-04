// package Authentication;

// import Admin.AdminDashboard;
// import Transport.TransportDashboard;
// import Traveler.TravelerDashboard;
// import java.io.IOException;

// public class AuthenticationDashboard {

//     // Display dashboard based on user role
//     public void displayDashboard(String role) throws IOException {
//         switch (role) {
//             case "Admin":
//                 AdminDashboard adminDashboard = new AdminDashboard();
//                 adminDashboard.displayAdminMenu();
//                 break;
//             case "Traveler":
//                 TravelerDashboard travelerDashboard = new TravelerDashboard();
//                 travelerDashboard.showDashboard();
//                 break;
//             case "Transport":
//                 TransportDashboard transportDashboard = new TransportDashboard();
//                 transportDashboard.dashboard();
//                 break;
//             default:
//                 System.out.println("Invalid role. Please contact support.");
//                 break;
//         }
//     }
// }
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

    // Constructor initializes dashboard instances
    public AuthenticationDashboard() {
        adminDashboard = new AdminDashboard();
        travelerDashboard = new TravelerDashboard();
        transportDashboard = new TransportDashboard();
    }

    // Display dashboard based on user role
    public void displayDashboard(String role) throws IOException, NoSuchAlgorithmException {
        switch (role) {
            case "Admin" -> adminDashboard.displayAdminMenu();
            case "Traveler" -> travelerDashboard.showDashboard();
            case "Transport" -> transportDashboard.dashboard();
            default -> System.out.println("Invalid role. Please contact support.");
        }
    }
}
