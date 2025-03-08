package Traveler.Itinerary_Management.Calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calendar {
    private static final String BOLD_RED = "\u001B[1;31m"; // Bold + Red text
    private static final String RESET_COLOR = "\u001B[0m"; // Reset color to default
    private static final int COLUMN_WIDTH = 28; // Width for each month block
    private static final int SPACING = 3; // Space between months

    public void displayTripsOnCalendar(String tripFile) {
        Set<LocalDate> tripDates = getTripDates(tripFile);

        int minYear = tripDates.stream().mapToInt(LocalDate::getYear).min().orElse(LocalDate.now().getYear());
        int maxYear = tripDates.stream().mapToInt(LocalDate::getYear).max().orElse(LocalDate.now().getYear());

        for (int year = minYear; year <= maxYear; year++) {
            System.out.println("\nYear: " + year + "\n");

            List<String[]> months = new ArrayList<>();
            for (int month = 1; month <= 12; month++) {
                months.add(getMonthCalendar(YearMonth.of(year, month), tripDates));
            }

            // Print 3 months per row
            for (int row = 0; row < 4; row++) {
                printRowOfMonths(months, row);
            }
        }
    }

    private void printRowOfMonths(List<String[]> months, int row) {
        int startIndex = row * 3;
        int endIndex = Math.min(startIndex + 3, months.size());

        for (int line = 0; line < 9; line++) { // 9 lines per month block
            for (int i = startIndex; i < endIndex; i++) {
                System.out.print("| " + padRight(months.get(i)[line], COLUMN_WIDTH) + " ");
            }
            System.out.println("|");
        }
        System.out.println("+" + "-".repeat(COLUMN_WIDTH + 2).repeat(3) + "+");
    }

    private String[] getMonthCalendar(YearMonth yearMonth, Set<LocalDate> tripDates) {
        List<String> lines = new ArrayList<>();
        lines.add(padCenter(yearMonth.getMonth() + " " + yearMonth.getYear(), COLUMN_WIDTH));
        lines.add("Sun Mon Tue Wed Thu Fri Sat");

        int firstDayOfMonth = yearMonth.atDay(1).getDayOfWeek().getValue() % 7;
        int daysInMonth = yearMonth.lengthOfMonth();

        StringBuilder week = new StringBuilder("    ".repeat(firstDayOfMonth));
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = yearMonth.atDay(day);
            if (tripDates.contains(date)) {
                week.append(String.format(BOLD_RED + "%2d  " + RESET_COLOR, day));
            } else {
                week.append(String.format("%2d  ", day));
            }
            if ((day + firstDayOfMonth) % 7 == 0 || day == daysInMonth) {
                lines.add(week.toString());
                week.setLength(0);
            }
        }

        while (lines.size() < 9) lines.add(" ".repeat(COLUMN_WIDTH));
        return lines.toArray(new String[0]);
    }

    private Set<LocalDate> getTripDates(String tripFile) {
        Set<LocalDate> tripDates = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tripFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                LocalDate startDate = LocalDate.parse(tripData[1].split(" ")[0]);
                LocalDate endDate = LocalDate.parse(tripData[2].split(" ")[0]);

                LocalDate current = startDate;
                while (!current.isAfter(endDate)) {
                    tripDates.add(current);
                    current = current.plusDays(1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tripDates;
    }

    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }

    private String padCenter(String text, int length) {
        int padding = (length - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, length - text.length() - padding));
    }

}