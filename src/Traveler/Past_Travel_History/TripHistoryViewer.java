package Traveler.Past_Travel_History;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TripHistoryViewer {
    private static final String TRIP_FILE = "src\\TXT_Files\\trips.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void viewPastTrips() {
        LocalDate today = LocalDate.now();
        List<String[]> pastTrips = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                LocalDate startDate = LocalDate.parse(tripData[1].split(" ")[0]);
                if (startDate.isBefore(today)) {
                    pastTrips.add(tripData);
                }
            }
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("║ Past Travel History                                      ║");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("║ No. ║ Destination        ║ Start Date & Time           ║ End Date & Time             ║");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        if (pastTrips.isEmpty()) {
            System.out.println("║ No past trips found.                                     ║");
        } else {
            int index = 1;
            for (String[] trip : pastTrips) {
                System.out.printf("║ %-3d ║ %-18s ║ %-25s ║ %-25s ║\n", index++, trip[0], trip[1], trip[2]);
            }
        }
        
        System.out.println("═══════════════════════════════════════════════════════════");
    }

    public static void viewYearWiseTrips() {
        Map<Integer, List<String[]>> tripsByYear = new TreeMap<>(Collections.reverseOrder());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                int year = LocalDate.parse(tripData[1].split(" ")[0]).getYear();
                tripsByYear.putIfAbsent(year, new ArrayList<>());
                tripsByYear.get(year).add(tripData);
            }
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        
        System.out.println("═════════════════════════════════════════════");
        System.out.println("║ Year-wise Travel History                  ║");
        System.out.println("═════════════════════════════════════════════");
        
        if (tripsByYear.isEmpty()) {
            System.out.println("║ No trips found.                           ║");
        } else {
            for (var entry : tripsByYear.entrySet()) {
                System.out.println("═════════════════════════════════════════════");
                System.out.println(" Year: " + entry.getKey());
                System.out.println("═════════════════════════════════════════════");
                System.out.println("║ No. ║ Destination        ║ Start Date & Time           ║ End Date & Time             ║");
                System.out.println("═════════════════════════════════════════════");
                int index = 1;
                for (String[] trip : entry.getValue()) {
                    System.out.printf("║ %-3d ║ %-18s ║ %-25s ║ %-25s ║\n", index++, trip[0], trip[1], trip[2]);
                }
                System.out.println("═════════════════════════════════════════════");
            }
        }
    }
    
}
