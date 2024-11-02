<<<<<<< HEAD


import Admin.AdminDashBoard;

public class Main {

    public static void main(String[] args) {

        AdminDashBoard adminDashboard = new AdminDashBoard();
        adminDashboard.displayAdminMenu();
    }
}

=======
import Itinerary_Management.*;
import Itinerary_Management.Alarm.AlertSystem;
import Trip_Management.*;


public class Main{

public static void main(String[] args) {
        TripManager t = new TripManager();
        // t.addTrip();
             
        t.viewTripsOnCalendar();

        // t.removeTrip();

        // AlertSystem a = new AlertSystem();
        // a.alertSystem();

        
    }
}
>>>>>>> 12dd30300b9e09dff749723bc4b0f2a76b54c638
