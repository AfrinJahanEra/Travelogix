package Traveler.Itinerary_Management.Alarm;

// import java.time.LocalDateTime;
// import java.util.Scanner;

// public class AlertSystem {

//     private static final String SOUND_FILE_PATH = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\Traveler\\Itinerary_Management\\Alarm\\sparcle.wav";

//     public void alertSystem(Scanner scanner) { // Accept scanner as a parameter
//         System.out.println("Enter alert date and time (yyyy-MM-dd HH:mm:ss): ");
//         String inputDateTime = scanner.nextLine();
//         System.out.println("Enter a message: ");
//         String message = scanner.nextLine();

//         LocalDateTime alertDate = DateUtils.parseDateTime(inputDateTime); // Change to LocalDateTime

//         if (alertDate != null) {
//             System.out.println("Alert set for: " + inputDateTime);
//             AlertUtils.waitForAlert(alertDate, message); // Ensure waitForAlert can handle LocalDateTime

//             // Exception handling for sound playback
//             try {
//                 SoundUtils.playSound(SOUND_FILE_PATH);
//             } catch (Exception e) {
//                 System.out.println("Failed to play sound. Returning to dashboard.");
//                 e.printStackTrace();
//             }
//         } else {
//             System.out.println("Invalid date and time format.");
//         }
//     }
// }
import java.util.Date;
import java.util.Scanner;

public class AlertSystem {

    
    private static final String SOUND_FILE_PATH = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\Traveler\\Itinerary_Management\\Alarm\\sparcle.wav";;

    public void alertSystem(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter alert date and time (yyyy-MM-dd HH:mm:ss): ");
        String inputDateTime = scanner.nextLine();
        
        Date alertDate = DateUtils.parseDateTime(inputDateTime);
        
        if (alertDate != null) {
            System.out.println("Alert set for: " + alertDate);
            AlertUtils.waitForAlert(alertDate);
            SoundUtils.playSound(SOUND_FILE_PATH);
        } else {
            System.out.println("Invalid date and time format.");
        }
        
        scanner.close();
    }
}


