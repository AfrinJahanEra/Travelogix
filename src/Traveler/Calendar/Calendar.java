package Itinerary_Management.Calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class Calendar {

    private static final String RED_COLOR = "\u001B[31m"; // ANSI escape code for red color
    private static final String RESET_COLOR = "\u001B[0m"; // Reset color to default

    public void displayTripsOnCalendar(String tripFile) {
        Set<LocalDate> tripDates = getTripStartDates(tripFile); // Get only the starting dates

        int year = LocalDate.now().getYear();
        for (int month = 1; month <= 12; month++) {
            if (hasTripsInMonth(year, month, tripDates)) { // Only display months with marked dates
                displayMonthCalendar(YearMonth.of(year, month), tripDates);
            }
        }
    }

    // Get only the starting dates of the trips
    private Set<LocalDate> getTripStartDates(String tripFile) {
        Set<LocalDate> tripDates = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tripFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                LocalDate startDate = LocalDate.parse(tripData[1].split(" ")[0]); // Parse only the start date
                tripDates.add(startDate);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tripDates;
    }

    // Check if the month contains any marked trip dates
    private boolean hasTripsInMonth(int year, int month, Set<LocalDate> tripDates) {
        for (LocalDate date : tripDates) {
            if (date.getYear() == year && date.getMonthValue() == month) {
                return true;
            }
        }
        return false;
    }

    // Display the calendar for the month with marked dates
    private void displayMonthCalendar(YearMonth yearMonth, Set<LocalDate> tripDates) {
        System.out.println("\n" + yearMonth.getMonth() + " " + yearMonth.getYear());
        System.out.println("Su Mo Tu We Th Fr Sa");

        int firstDayOfMonth = yearMonth.atDay(1).getDayOfWeek().getValue();
        int daysInMonth = yearMonth.lengthOfMonth();

        // Adjust the first line for the first day of the month
        for (int i = 1; i < firstDayOfMonth; i++) {
            System.out.print("   ");
        }

        // Display each day, marking the trips' starting dates in red
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = yearMonth.atDay(day);
            if (tripDates.contains(date)) {
                System.out.printf(RED_COLOR + "%2d " + RESET_COLOR, day); // Color marked date in red
            } else {
                System.out.printf("%2d ", day);
            }
            if ((day + firstDayOfMonth - 1) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }
}
