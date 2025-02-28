package Traveler.budget_tracker;

import Utilities.utils.AdvancedFileUtils;
import Utilities.utils.BasicFileUtils;
import Utilities.utils.BasicUtils;
import Traveler.Itinerary_Management.Alarm.SoundUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    public static String filename;

    public Budget(String filename){
        this.filename = filename;
    }

    public static void selectTripAndManageBudget() {
            String inputFile = "src/trips.txt";  // Adjust path if necessary
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate today = LocalDate.now();
            boolean foundFutureDate = false;
            System.out.println("════════════════════════════════════════════════════════════════════════");
            System.out.println("║ No. ║ Destination        ║ Start               ║ End                 ║");
            System.out.println("════════════════════════════════════════════════════════════════════════");

            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                int index=1;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\s*,\\s*"); // Handles spaces correctly

                    if (parts.length >= 3) {
                        try {
                            // Extract last date-time and trim any extra spaces
                            String lastDateTimeString = parts[2].trim();
                            LocalDateTime dateTime = LocalDateTime.parse(lastDateTimeString, formatter);
                            LocalDate date = dateTime.toLocalDate();
                            if (date.isAfter(today)) {System.out.printf("║ %-3d ║ %-18s ║ %-19s ║ %-19s ║\n",
                                    index, parts[0], parts[1], parts[2]);
                                index++;
                                foundFutureDate = true;
                            }


                        } catch (Exception e) {
                            System.err.println("Error parsing date in line: " + line);
                            e.printStackTrace();
                        }
                    }
                }
                if (!foundFutureDate) {
                    System.out.println("║ No trips found.  ");
                }
                System.out.println("════════════════════════════════════════════════════════════════════════");
            } catch (IOException e) {
                System.err.println("File not found or cannot be read.");
                e.printStackTrace();
            }


        
        // Let user select a trip
        String choiceStr = BasicUtils.takeStringInput("\nEnter the trip number to manage its budget: ");
        int choice;
        try {
            choice = Integer.parseInt(choiceStr);
            if (choice < 1 || choice > upcomingTrips.size()) {
                System.out.println("Invalid choice. Exiting...");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        // Extract selected trip details
        String selectedTrip = upcomingTrips.get(choice - 1);
        String[] tripDetails = selectedTrip.split(", ");
        String destination = tripDetails[0];
        String tripBudgetFile = "budget_" + destination.replace(" ", "_") + ".txt"; // Unique budget file

        // Show budget tracker
        Budget budget = new Budget(tripBudgetFile);
        budget.showBudgetOptions();
    }


    // Displays budget options after selecting a trip
    private void showBudgetOptions() {
        while (true) {
            System.out.println("\nBudget Tracker Options:");
            System.out.println("1. Set Categories and Limits");
            System.out.println("2. View Total Spending");
            System.out.println("3. Update Spending");
            System.out.println("4. View Spending Pie Chart");
            System.out.println("5. Go Back");

            String choiceStr = BasicUtils.takeStringInput("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    setCategoryAndLimit();
                    break;
                case 2:
                    viewTotalSpending();
                    break;
                case 3:
                    updateSpending();
                    break;
                case 4:
                    consolePieChart();
                    break;
                case 5:
                    return; // Exit budget tracker
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    public void setCategoryAndLimit() {
        String numOfCategories = BasicUtils.takeStringInput("How many categories you want to add? ");
        int num;
        try {
            num = Integer.parseInt(numOfCategories);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Try again.");
            return;
        }

        while (num > 0) {
            String category = BasicUtils.takeStringInput("Enter category name: ");
            String expenseLimit = BasicUtils.takeStringInput("Enter expected limit for this: ");
            int limit;
            try {
                limit = Integer.parseInt(expenseLimit);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Try again.");
                continue;
            }
            BasicFileUtils.write(filename, category + "," + limit + ",0" + ",Null");
            num--;
        }
    }


    public void viewTotalSpending() {
        List<String> lines = BasicFileUtils.read(filename);

        if (lines.isEmpty()) {
            System.out.println("No records yet.");
            return;
        }

        System.out.println("+----------------+----------------------+----------------+----------------+");
        System.out.println("|    Category    | Expected Spending    | Your Spending  |    Remarks     |");
        System.out.println("+----------------+----------------------+----------------+----------------+");

        for (String line : lines) {
            String[] parts = BasicFileUtils.splitIntoParts(line);
            String category = parts[0];
            String expectedLimit = parts[1];
            String actualSpending = parts[2];
            String remarks = parts[3];

            System.out.printf("| %-14s | %-20s | %-14s | %-14s | \n", category, expectedLimit, actualSpending, remarks);
        }

        System.out.println("+----------------+----------------------+----------------+----------------+");
    }

    public void updateSpending() {
        String category = BasicUtils.takeStringInput("Enter the category you spent on: ");
        String spendingInput = BasicUtils.takeStringInput("Enter the amount you spent: ");
        String remarks = BasicUtils.takeStringInput("Enter remarks for this spending: ");

        try {
            int spending = Integer.parseInt(spendingInput);
            List<String> lines = BasicFileUtils.read(filename);
            boolean found = false;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = BasicFileUtils.splitIntoParts(line);

                if (parts[0].equalsIgnoreCase(category)) {
                    int limit = Integer.parseInt(parts[1]);
                    int currentSpending = Integer.parseInt(parts[2]);
                    int updatedSpending = currentSpending + spending;  // Spending is always updated

                    // Update file with new spending
                    lines.set(i, parts[0] + "," + parts[1] + "," + updatedSpending + "," + remarks);
                    found = true;

                    // Alert if spending reaches/exceeds the limit
                    if (updatedSpending >= limit) {
                        System.out.println("\n⚠️ ALERT: You have reached/exceeded your spending limit for " + category + "!");
                        SoundUtils.playSound("Itinerary_Management/Alarm/sparcle.wav"); //Play alarm
                    }

                    break;
                }
            }

            if (!found) {
                System.out.println("Category not found.");
                return;
            }

            // Save updated spending data
            AdvancedFileUtils.clearFile(filename);
            for (String line : lines) {
                BasicFileUtils.write(filename, line);
            }

            System.out.println("✅ Spending updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount entered. Please enter a valid number.");
        }
    }


    public void consolePieChart() {
        List<String> lines = BasicFileUtils.read(filename);

        if (lines.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }

        int [] limitSpending = new int[lines.size()];
        int[] spendingValues = new int[lines.size()];
        String[] categories = new String[lines.size()];

        // Collect spending data
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = BasicFileUtils.splitIntoParts(lines.get(i));

            try {
                categories[i] = parts[0]; // Category name
                spendingValues[i] = Integer.parseInt(parts[2]); // Actual spending
                limitSpending[i] = Integer.parseInt(parts[1]); // Expected limit (fixed from += issue)
            } catch (NumberFormatException e) {
                System.out.println("Warning: Invalid data format in file. Skipping entry: " + lines.get(i));
                continue;
            }
        }

        // Display pie chart
        System.out.println("\nExpense Chart (Spending Breakdown):");
        for (int i = 0; i < categories.length; i++) {
            //  Prevent division by zero
            if (limitSpending[i] == 0) {
                System.out.printf("%-10s: No spending limit set.\n", categories[i]);
                continue;
            }

            int percentage = (int) ((spendingValues[i] / (double) limitSpending[i]) * 100);
            System.out.printf("%-10s: %3d%% ", categories[i], percentage);

            // Create visual bar for pie chart
            int barLength = Math.min(percentage / 2, 50); // Limit max bar length
            for (int j = 0; j < barLength; j++) {
                System.out.print("█");
            }
            System.out.println();
        }

        System.out.println("\nNote: Each '█' represents 2% of the total spending.");
    }



}
