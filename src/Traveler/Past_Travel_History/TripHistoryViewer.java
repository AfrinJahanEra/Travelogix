package Traveler.Past_Travel_History;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TripHistoryViewer {
    private static final String TRIP_FILE = "src/TXT_Files/trips.txt";
    private static final String BUDGET_FILE_PATH = "src/TXT_Files/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void viewPastTrips() {
        LocalDate today = LocalDate.now();
        List<String[]> pastTrips = new ArrayList<>();
        Map<String, Integer> totalBudgets = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            reader.lines()
                  .map(line -> line.split(", "))
                  .filter(trip -> isPastTrip(trip[1], today))
                  .forEach(trip -> {
                      pastTrips.add(trip);
                      String budgetFileName = "budget_" + trip[0] + "_" + trip[1].replace(" ", "_").replace(":", "-") + ".txt";
                      totalBudgets.put(trip[0] + trip[1], calculateTotalBudget(budgetFileName));
                  });
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        printTable("Past Travel History", pastTrips, totalBudgets);
    }

    public static void viewYearWiseTrips() {
        Map<Integer, List<String[]>> tripsByYear = new TreeMap<>(Collections.reverseOrder());
        Map<String, Integer> totalBudgets = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            reader.lines()
                  .map(line -> line.split(", "))
                  .forEach(trip -> {
                      tripsByYear.computeIfAbsent(getYear(trip[1]), k -> new ArrayList<>()).add(trip);
                      String budgetFileName = "budget_" + trip[0] + "_" + trip[1].replace(" ", "_").replace(":", "-") + ".txt";
                      totalBudgets.put(trip[0] + trip[1], calculateTotalBudget(budgetFileName));
                  });
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        tripsByYear.forEach((year, trips) -> printTable("Year: " + year, trips, totalBudgets));
    }

    private static int calculateTotalBudget(String budgetFileName) {
        int totalBudget = 0;
        File budgetFile = new File(BUDGET_FILE_PATH + budgetFileName);
        if (budgetFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(budgetFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        totalBudget += Integer.parseInt(parts[1].trim());
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading budget file " + budgetFileName + ": " + e.getMessage());
            }
        }
        return totalBudget;
    }

    private static boolean isPastTrip(String startDate, LocalDate today) {
        return LocalDate.parse(startDate.split(" ")[0]).isBefore(today);
    }

    private static int getYear(String startDate) {
        return LocalDate.parse(startDate.split(" ")[0]).getYear();
    }

    private static void printTable(String title, List<String[]> trips, Map<String, Integer> totalBudgets) {
        System.out.println("\n" + title);
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("║ No. ║ Destination        ║ Start Date & Time       ║ End Date & Time         ║ Total Budget     ║");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");

        if (trips.isEmpty()) {
            System.out.println("║ No records found.                                                                 ║");
        } else {
            int index = 1;
            for (String[] trip : trips) {
                String key = trip[0] + trip[1];
                int totalBudget = totalBudgets.getOrDefault(key, 0);
                System.out.printf("║ %-3d ║ %-18s ║ %-23s ║ %-23s ║ %-15d ║%n", index++, trip[0], trip[1], trip[2], totalBudget);
            }
        }

        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
    }
}
