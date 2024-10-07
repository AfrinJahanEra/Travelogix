package Itinary_Managment.Calander;

import java.time.YearMonth;
import java.util.Scanner;

public class Calendar {

    public void calendar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a month:");
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < 12; i++) {
            System.out.println((i + 1) + ". " + months[i]);
        }

        int selectedMonth = scanner.nextInt();
        while (selectedMonth < 1 || selectedMonth > 12) {
            System.out.println("Invalid month. Please select a valid month:");
            selectedMonth = scanner.nextInt();
        }

        int year = java.time.Year.now().getValue();
        YearMonth yearMonth = YearMonth.of(year, selectedMonth);
        int daysInMonth = yearMonth.lengthOfMonth();

        displayCalendar(daysInMonth);

        System.out.println("Enter a date (1-" + daysInMonth + "):");
        int selectedDate = scanner.nextInt();
        while (selectedDate < 1 || selectedDate > daysInMonth) {
            System.out.println("Invalid date. Please select a valid date:");
            selectedDate = scanner.nextInt();
        }


        System.out.println("Enter time in HH:MM format:");
        String time = scanner.next();

        System.out.println("\nYou have selected:");
        System.out.println("Month: " + months[selectedMonth - 1]);
        System.out.println("Date: " + selectedDate);
        System.out.println("Time: " + time);

        displayCalendarWithCross(daysInMonth, selectedDate);
    }

    private void displayCalendar(int daysInMonth) {
        System.out.println("\nCalendar:");
        for (int i = 1; i <= daysInMonth; i++) {
            System.out.printf("%3d ", i);
            if (i % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    private void displayCalendarWithCross(int daysInMonth, int selectedDate) {
        System.out.println("\nUpdated Calendar:");
        for (int i = 1; i <= daysInMonth; i++) {
            if (i == selectedDate) {
                System.out.printf("  X ");
            } else {
                System.out.printf("%3d ", i);
            }
            if (i % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }
}
