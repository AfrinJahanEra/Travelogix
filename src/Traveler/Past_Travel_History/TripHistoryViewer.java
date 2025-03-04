package Traveler.Past_Travel_History;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TripHistoryViewer {
    private static final String TRIP_FILE = "src/TXT_Files/trips.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void viewPastTrips() {
        LocalDate today = LocalDate.now();
        List<String[]> pastTrips = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            reader.lines()
                  .map(line -> line.split(", "))
                  .filter(trip -> isPastTrip(trip[1], today))
                  .forEach(pastTrips::add);
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        printTable("Past Travel History", pastTrips);
    }

    public static void viewYearWiseTrips() {
        Map<Integer, List<String[]>> tripsByYear = new TreeMap<>(Collections.reverseOrder());

        try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            reader.lines()
                  .map(line -> line.split(", "))
                  .forEach(trip -> tripsByYear.computeIfAbsent(getYear(trip[1]), k -> new ArrayList<>()).add(trip));
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        tripsByYear.forEach((year, trips) -> {
           
            printTable("Year: " + year, trips);
        });
    }

    private static boolean isPastTrip(String startDate, LocalDate today) {
        return LocalDate.parse(startDate.split(" ")[0]).isBefore(today);
    }

    private static int getYear(String startDate) {
        return LocalDate.parse(startDate.split(" ")[0]).getYear();
    }

    private static void printTable(String title, List<String[]> trips) {
        System.out.println("\n");
        System.out.println("                                  "+ title + "");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        System.out.println("║ No. ║ Destination        ║ Start Date & Time       ║ End Date & Time         ║");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");

        if (trips.isEmpty()) {
            System.out.println("║ No records found.                                           ║");
        } else {
            int index = 1;
            for (String[] trip : trips) {
                System.out.printf("║ %-3d ║ %-18s ║ %-23s ║ %-23s ║%n", index++, trip[0], trip[1], trip[2]);
            }
        }

        System.out.println("════════════════════════════════════════════════════════════════════════════════");
    }
}
