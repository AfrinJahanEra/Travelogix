import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransportAgency agency = new TransportAgency();
        TransportAgencyFunctionalities functionalities = new TransportAgencyFunctionalities();


        Scanner sc = new Scanner(System.in);
        User user = new User();
        user.inputUserDetails();


        TransportAgencyDashboard dashboard = new TransportAgencyDashboard();
        dashboard.dashboard(agency, functionalities, user);
    }
}
