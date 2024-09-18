package Draft;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class TransportAgency {

    private String email;
    private String password;
    private final String AGENCY_FILE = "agency.txt";
    private final String CONTACT_FILE = "contacts.txt";
    private final int TOTAL_SEATS = 60;
    private int emptySeats = TOTAL_SEATS;
    private boolean[] seats = new boolean[TOTAL_SEATS];
    private FileManager fileManager = new FileManager();
    private Scanner scanner = new Scanner(System.in);

    public TransportAgency(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void displayAgencyMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Transport Agency Menu");
            System.out.println("1. Show Districts and Visiting Places");
            System.out.println("2. Book a Seat");
            System.out.println("3. Change Seat");
            System.out.println("4. Cancel Seat");
            System.out.println("5. Show Account Activity");
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showDistricts();
                    break;
                case 2:
                    bookSeat();
                    break;
                case 3:
                    changeSeat();
                    break;
                case 4:
                    cancelSeat();
                    break;
                case 5:
                    showAccountActivity();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Show districts and visiting places
    private void showDistricts() {
        System.out.println("Available Districts in Bangladesh:");
        String[] districts = fileManager.readFileLines("districts.txt");
        if (districts == null || districts.length == 0) {
            System.out.println("No districts available.");
            return;
        }

        for (int i = 0; i < districts.length; i++) {
            System.out.println((i + 1) + ". " + districts[i]);
        }

        System.out.println("Select a district to see visiting places:");
        int districtChoice = scanner.nextInt();
        if (districtChoice < 1 || districtChoice > districts.length) {
            System.out.println("Invalid district choice.");
            return;
        }

        String selectedDistrict = districts[districtChoice - 1];
        showVisitingPlaces(selectedDistrict);
    }

    // Show visiting places for a district
    private void showVisitingPlaces(String district) {
        System.out.println("Visiting places in " + district + ":");
        String[] places = fileManager.readFileLines(district + "_places.txt");
        if (places == null || places.length == 0) {
            System.out.println("No visiting places available for this district.");
            return;
        }

        for (int i = 0; i < places.length; i++) {
            System.out.println((i + 1) + ". " + places[i]);
        }

        System.out.println("Select a place to proceed:");
        int placeChoice = scanner.nextInt();
        if (placeChoice < 1 || placeChoice > places.length) {
            System.out.println("Invalid place choice.");
            return;
        }

        String selectedPlace = places[placeChoice - 1];
        System.out.println("You selected: " + selectedPlace);
    }

    // Seat booking process
    private void bookSeat() {
        System.out.println("Available Seats:");
        displaySeats();

        if (emptySeats == 0) {
            System.out.println("No seats available.");
            return;
        }

        System.out.println("Enter seat number to book (1 - 60):");
        int seatNumber = scanner.nextInt();
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS || seats[seatNumber - 1]) {
            System.out.println("Invalid or already booked seat.");
            return;
        }

        seats[seatNumber - 1] = true;
        emptySeats--;

        String contact = getRandomContact();
        int cost = getRandomCost();
        
        System.out.println("Booking successful!");
        System.out.println("Your ticket info:");
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Transport Contact: " + contact);
        System.out.println("Ticket Cost: " + cost + " BDT");
    }

    // Change seat
    private void changeSeat() {
        System.out.println("Enter your current seat number:");
        int currentSeat = scanner.nextInt();
        if (currentSeat < 1 || currentSeat > TOTAL_SEATS || !seats[currentSeat - 1]) {
            System.out.println("Invalid seat number.");
            return;
        }

        seats[currentSeat - 1] = false;
        emptySeats++;

        System.out.println("Available Seats:");
        displaySeats();

        System.out.println("Enter new seat number to book:");
        int newSeat = scanner.nextInt();
        if (newSeat < 1 || newSeat > TOTAL_SEATS || seats[newSeat - 1]) {
            System.out.println("Invalid or already booked seat.");
            return;
        }

        seats[newSeat - 1] = true;
        emptySeats--;

        System.out.println("Seat changed successfully. Your new seat is: " + newSeat);
    }

    // Cancel seat
    private void cancelSeat() {
        System.out.println("Enter seat number to cancel:");
        int seatNumber = scanner.nextInt();
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS || !seats[seatNumber - 1]) {
            System.out.println("Invalid seat number.");
            return;
        }

        seats[seatNumber - 1] = false;
        emptySeats++;

        System.out.println("Seat canceled successfully. Confirmation sent.");
    }

    // Display seats as a matrix
    private void displaySeats() {
        for (int i = 0; i < TOTAL_SEATS; i++) {
            if (i % 5 == 0) System.out.println();
            if (seats[i]) {
                System.out.print("[X] ");
            } else {
                System.out.print("[" + (i + 1) + "] ");
            }
        }
        System.out.println();
    }

    // Booking process for a specific date and location
    public void bookSeatForDate(LocalDateTime dateTime, String location, String visitingPlace) {
        System.out.println("Available buses for " + location + " on " + dateTime.toLocalDate() + ":");
        displayBusSeats();

        if (emptySeats == 0) {
            System.out.println("No seats available.");
            return;
        }

        System.out.println("Enter seat number to book (1 - 60):");
        int seatNumber = scanner.nextInt();
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS || seats[seatNumber - 1]) {
            System.out.println("Invalid or already booked seat.");
            return;
        }

        seats[seatNumber - 1] = true;
        emptySeats--;

        String contact = getRandomContact();
        int cost = getRandomCost();

        System.out.println("Booking successful!");
        System.out.println("Your ticket info:");
        System.out.println("Location: " + location);
        System.out.println("Visiting Place: " + visitingPlace);
        System.out.println("Date: " + dateTime.toLocalDate());
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Transport Contact: " + contact);
        System.out.println("Ticket Cost: " + cost + " BDT");
    }

    // Display available seats in matrix form
    private void displayBusSeats() {
        System.out.println("Seat Matrix (X for booked, number for available):");

        for (int i = 0; i < TOTAL_SEATS; i++) {
            if (i % 5 == 0) System.out.println();
            if (seats[i]) {
                System.out.print("[X] ");
            } else {
                System.out.print("[" + (i + 1) + "] ");
            }
        }
        System.out.println();
    }

    // Random contact generation
    private String getRandomContact() {
        String[] contacts = fileManager.readFileLines("contacts.txt");
        if (contacts == null || contacts.length == 0) {
            return "No contacts available.";
        }

        Random random = new Random();
        return contacts[random.nextInt(contacts.length)];
    }

    // Get random cost between 500 and 1500
    private int getRandomCost() {
        Random random = new Random();
        return 500 + random.nextInt(1001);
    }

    // Get random contact from file
    // private String getRandomContact() {
    //     String[] contacts = fileManager.readFileLines(CONTACT_FILE);
    //     if (contacts == null || contacts.length == 0) {
    //         return "No contacts available.";
    //     }

    //     Random random = new Random();
    //     return contacts[random.nextInt(contacts.length)];
    // }

    // // Get random cost between 500 and 1500
    // private int getRandomCost() {
    //     Random random = new Random();
    //     return 500 + random.nextInt(1001); // random value between 500 and 1500
    // }

    // Show account activity
    private void showAccountActivity() {
        System.out.println("Account activity:");
        String[] accountData = fileManager.readFileLines(AGENCY_FILE);
        if (accountData == null || accountData.length == 0) {
            System.out.println("No account activity available.");
            return;
        }

        for (String data : accountData) {
            System.out.println(data);
        }

        System.out.println("Do you want to delete your account? (yes/no)");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            sendDeletionRequest();
        }
    }

    // Send deletion request to admin
    private void sendDeletionRequest() {
        System.out.println("Sending account deletion request to admin...");
        // Implement sending request to admin here
    }
    
}
    
    
