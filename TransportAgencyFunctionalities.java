import java.util.Scanner;

public class TransportAgencyFunctionalities {
    User user;
    int seatIndex;
    String[] buses = {"Bus 1", "Bus 2", "Bus 3"};
    int[] seatPrices = {300, 400, 500};
    char[][] seats;
    int[][] seatIndices;
    int rows = 3, cols = 4;
    int seatCounter = 1;

    public TransportAgencyFunctionalities() {
        seats = new char[rows][cols];
        seatIndices = new int[rows][cols];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = '.';
                seatIndices[i][j] = seatCounter++;
            }
        }

        seats[1][2] = 'x';
        seats[0][3] = 'x';
    }

    public void viewAccount(User user) {
        System.out.println("Your Account: ");
        user.showDetails();
    }

    public void bookSeat(TransportAgency agency, User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Book Seat");


        System.out.print("Enter Departure Spot: ");
        agency.setDepartSpot(sc.nextLine());

        System.out.print("Enter Arrival Spot: ");
        agency.setArriveSpot(sc.nextLine());


        System.out.println("Available buses: ");
        for (int i = 0; i < buses.length; i++) {
            System.out.println(i+1 + ". " + buses[i] + " | Price: " + seatPrices[i] + " | Departure Time: " + (8 + i) + ":00 AM");
        }


        System.out.print("Select which bus you want: ");
        agency.setSelectedBusIndex(sc.nextInt());


        showSeatMatrix();
        System.out.print("Select seat by entering the seat index: ");
        agency.setSelectedSeatIndex(sc.nextInt());


        if (bookSeatByIndex(agency.getSelectedSeatIndex())) {
            int price = seatPrices[agency.getSelectedBusIndex()];
            System.out.println("Total Price: " + price);
            System.out.println("Seat " + agency.getSelectedSeatIndex() + " booked successfully.");
            generateReceipt(user);
        }

    }



    public void showSeatMatrix() {
        System.out.println("Bus seat map ('x' means booked, '.' means available): ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(seats[i][j] + "  ");
            }
            System.out.println();
            for (int j = 0; j < cols; j++) {
                System.out.print(seatIndices[i][j] + "  ");
            }
            System.out.println();
        }
    }



    public boolean bookSeatByIndex(int seatIndex) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seatIndices[i][j] == seatIndex) {
                    if (seats[i][j] == 'x') {
                        System.out.println("Error: This seat is already booked.");
                        System.out.println("Choose again: ");
                    } else {
                        seats[i][j] = 'x';
                        return true;
                    }
                }
            }
        }
        System.out.println("Invalid seat index.");
        return false;
    }

    public void unbookSeat(int seatIndex) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seatIndices[i][j] == seatIndex) {
                    seats[i][j] = '.';
                    return;
                }
            }
        }
    }
    public int calculatePrice(int selectedBusIndex, int seatCount) {
        return seatPrices[selectedBusIndex] * seatCount;
    }

    public void editBooking(User user, TransportAgency agency) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to change your seat? (yes/no)");
        String decision = sc.nextLine();
        if (decision.equalsIgnoreCase("yes")) {
            unbookSeat(agency.getSelectedSeatIndex());
            System.out.println("Please select a new seat by entering the seat index: ");
            showSeatMatrix();

            int newSeatIndex = sc.nextInt();

            unbookSeat(agency.getSelectedSeatIndex());

            if (bookSeatByIndex(newSeatIndex)) {
                agency.setSelectedSeatIndex(newSeatIndex);
            }
        }
        generateReceipt(user);
    }
    public void generateReceipt(User user) {
        String userId = "ID-" + (int) (Math.random() * 100000)+user.name;
        System.out.println("Your booking is confirmed. Thank you for choosing us.");
        System.out.println("Booking Details: ");
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + user.name);
        System.out.println("Have a safe journey!");
    }


}
