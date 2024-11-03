// // import Itinerary_Management.*;
// // import Itinerary_Management.Alarm.AlertSystem;
// // import Trip_Management.*;


// public class Main{

// public static void main(String[] args) {
// //        TripManager t = new TripManager();
// //        // t.addTrip();
// //
// //        t.viewTripsOnCalendar();
// //
// //        // t.removeTrip();
// //
// //        // AlertSystem a = new AlertSystem();
// //        // a.alertSystem();

        
//     }
// }

import Authentication.Login;
import Authentication.SignUp;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        Login in= new Login();

        SignUp out= new SignUp();
        
        System.out.println("\n \n");
        System.out.println(".................................");
        System.out.println(".    Welcome to Travelogix      .");
        System.out.println(".................................");

        System.out.println("   ");
        System.out.println("   ");

        System.out.println("Select an option to use Travelogix");
        System.out.println("[1] Login");
        System.out.println("[2] Sign Up");
        System.out.println("[0] Exit");

        System.out.println("\n");
    
        while (true) {
            
            choice = scanner.nextInt();
            scanner.nextLine();  
            
            switch (choice) {
                case 1:
                    in.logIn();
                    break;
                case 2:
                    out.signUp();
                    break;
                case 0:
                    System.out.println("\nThank you for using Auth System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please enter 1, 2, or 0.");
                    break;
            }
        }
    }
}