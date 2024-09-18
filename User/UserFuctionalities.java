package User;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Admin.FileManager;

public class UserFuctionalities {
    Scanner scanner = new Scanner(System.in);
    FileManager fileManager = new FileManager();
    User user = new User(null, null, null, null);
    public void selectDateAndTime() {
        System.out.println("Enter your preferred travel date (YYYY-MM-DD): ");
        String date = scanner.next();
        System.out.println("Enter preferred time (HH:MM): ");
        String time = scanner.next();

        LocalDateTime selectedDateTime = LocalDateTime.parse(date + "T" + time);

        System.out.println("You selected: " + selectedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        
        // Now show locations
        System.out.println("Available Locations:");
        String[] locations = fileManager.readFileLines("locations.txt");
        if (locations == null || locations.length == 0) {
            System.out.println("No locations available.");
            return;
        }

        for (int i = 0; i < locations.length; i++) {
            System.out.println((i + 1) + ". " + locations[i]);
        }

        System.out.println("Select a location:");
        int locationChoice = scanner.nextInt();
        if (locationChoice < 1 || locationChoice > locations.length) {
            System.out.println("Invalid location choice.");
            return;
        }

        String selectedLocation = locations[locationChoice - 1];
        showVisitingPlaces(selectedLocation, selectedDateTime);
    }

    // Show visiting places and proceed to book
    public void showVisitingPlaces(String location, LocalDateTime dateTime) {
        System.out.println("Visiting places in " + location + ":");
        String[] places = fileManager.readFileLines(location + "_places.txt");
        if (places == null || places.length == 0) {
            System.out.println("No visiting places available for this location.");
            return;
        }

        for (int i = 0; i < places.length; i++) {
            System.out.println((i + 1) + ". " + places[i]);
        }

        System.out.println("Select a visiting place:");
        int placeChoice = scanner.nextInt();
        if (placeChoice < 1 || placeChoice > places.length) {
            System.out.println("Invalid place choice.");
            return;
        }

        // String selectedPlace = places[placeChoice - 1];
        // visitedPlaces.add(selectedPlace); // Add to user's visited places
        // System.out.println("You selected: " + selectedPlace);

        // // Proceed to book a bus seat for the selected location and date
        // transportAgency.bookSeatForDate(dateTime, location, selectedPlace);
    }

    // Manage account (show visited places, suggestions, etc.)
    public void manageAccount() {
        System.out.println("Account Information:");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());

        // System.out.println("Visited Places:");
        // for (String place : visitedPlaces) {
        //     System.out.println("- " + place);
        // }

        // System.out.println("Do you want to change your password? (yes/no)");
        // String changePassword = scanner.next();
        // if (changePassword.equalsIgnoreCase("yes")) {
        //     changePassword();
        // }
    }

    // Change password
    // private void changePassword() {
    //     System.out.println("Enter new password:");
    //     String newPassword = scanner.next();
    //     this.password = newPassword;
    //     System.out.println("Password changed successfully.");
    // }

    // User can give suggestions for new places
    public void giveSuggestions() {
        System.out.println("Enter a suggestion for a new place you visited:");
        scanner.nextLine(); // Consume leftover newline
        String suggestion = scanner.nextLine();

        fileManager.writeToFile("suggestions.txt", "User: " + user.getEmail() + " Suggested: " + suggestion);
        System.out.println("Thank you for your suggestion!");
    }
}
