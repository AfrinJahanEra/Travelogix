package Transport_Agency;

import java.io.*;
import java.util.Scanner;

public class Edit_Seat_Availability {

    public void edit_seat_availability(String busNameToSearch) {
        File inputFile = new File("bus_data.txt");
        File tempFile = new File("temp_bus_data.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean busFound = false;
            String[][] seats = new String[4][5];
            Scanner scanner = new Scanner(System.in);


            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    char column = (char) ('A' + i);
                    seats[i][j] = column + "" + (j + 1);
                }
            }


            String searchLowerCase = busNameToSearch.toLowerCase();

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("bus name: " + searchLowerCase)) {
                    busFound = true;
                    writer.write(line + "\n");


                    while ((line = reader.readLine()) != null && !line.equals("Seat Availability:")) {
                        writer.write(line + "\n");
                    }

                    writer.write("Seat Availability:\n");


                    for (int i = 0; i < 4; i++) {
                        line = reader.readLine();
                        String[] seatRow = line.trim().split("\\s+");


                        for (int j = 0; j < 5; j++) {
                            seats[i][j] = seatRow[j];
                        }
                    }


                    System.out.print("How many seats do you want to book? ");
                    int seatCount = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline


                    for (int k = 0; k < seatCount; k++) {
                        boolean seatBooked = false;

                        while (!seatBooked) {
                            System.out.print("Enter seat index to book (e.g., A1, B2): ");
                            String seatToBook = scanner.nextLine().toUpperCase();


                            for (int i = 0; i < 4 && !seatBooked; i++) {
                                for (int j = 0; j < 5 && !seatBooked; j++) {
                                    if (seats[i][j].equals(seatToBook)) {
                                        seats[i][j] = "X";  // Mark seat as booked
                                        seatBooked = true;
                                        System.out.println("Seat " + seatToBook + " booked successfully.");
                                    }
                                }
                            }

                            if (!seatBooked) {
                                System.out.println("Invalid seat index: " + seatToBook + ". Please try again.");
                            }
                        }
                    }


                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 5; j++) {
                            writer.write(seats[i][j] + "  ");
                        }
                        writer.write("\n");
                    }


                    writer.write("---------------------------\n");
                } else {
                    // Write other buses or lines to the file
                    writer.write(line + "\n");
                }
            }

            if (!busFound) {
                System.out.println("No bus found with the name: " + busNameToSearch);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while editing seat availability.");
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }
}

