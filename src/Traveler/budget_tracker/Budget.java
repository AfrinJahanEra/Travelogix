package Traveler.budget_tracker;

import Utilities.utils.AdvancedFileUtils;
import Utilities.utils.BasicFileUtils;
import Utilities.utils.BasicUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    public String filename;

    public Budget(String filename){
        this.filename = filename;
    }

    public static void selectTripAndManageBudget() {
        String tripFile = "trips.txt"; // File storing trips
        List<String> upcomingTrips = new ArrayList<>();
        LocalDate today = LocalDate.now();

        // Read trips using BasicFileUtils
        List<String> tripLines = BasicFileUtils.read(tripFile);

        if (tripLines.isEmpty()) {
            System.out.println("No trips found.");
            return;
        }

        // Filter only upcoming & ongoing trips
        int index = 1;
        System.out.println("\nOngoing & Upcoming Trips:");
        for (String line : tripLines) {
            String[] tripData = line.split(", ");
            LocalDate startDate = LocalDate.parse(tripData[1].split(" ")[0]);
            LocalDate endDate = LocalDate.parse(tripData[2].split(" ")[0]);

            if (!endDate.isBefore(today)) { // Show only upcoming/ongoing trips
                upcomingTrips.add(line);
                System.out.println(index + ". " + tripData[0] + " | Start: " + tripData[1] + " | End: " + tripData[2]);
                index++;
            }
        }

        if (upcomingTrips.isEmpty()) {
            System.out.println("No upcoming trips found.");
            return;
        }

        // Ask the user to select a trip
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
        String tripBudgetFile = "budget_" + destination.replace(" ", "_") + ".txt"; // Unique budget file for each trip

        // Show budget tracker for the selected trip
        Budget budget = new Budget(tripBudgetFile);
        budget.showBudgetOptions();
    }



    public void setCatagoryandLimit(){
        String numOfCatagories = BasicUtils.takeStringInput("How many catagories you want to add? ");
        int num = Integer.parseInt(numOfCatagories);
        while(num>0){
            String catagory = BasicUtils.takeStringInput("Enter catagory name: ");
            String expenselimit = BasicUtils.takeStringInput("Enter expected limit for this: ");
            int limit = Integer.parseInt(expenselimit);
            BasicFileUtils.write(filename, catagory+","+limit+",0"+",Null");
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
                    int currentSpending = Integer.parseInt(parts[2]);
                    int updatedSpending = currentSpending + spending;

                    lines.set(i, parts[0] + "," + parts[1] + "," + updatedSpending + "," + remarks);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Category not found.");
                return;
            }

            AdvancedFileUtils.clearFile(filename);
            for (String line : lines) {
                BasicFileUtils.write(filename, line);
            }

            System.out.println("Spending updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered. Please enter a valid number.");
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

        // Calculate total spending and collect data
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = BasicFileUtils.splitIntoParts(lines.get(i));
            categories[i] = parts[0]; // Category name
            spendingValues[i] = Integer.parseInt(parts[2]); // Actual spending
            limitSpending[i] += Integer.parseInt(parts[1]);
        }

        // Display pie chart
        System.out.println("\nExpense Chart (Spending Breakdown):");
        for (int i = 0; i < categories.length; i++) {
            int percentage = (int) ((spendingValues[i] / (double) limitSpending[i]) * 100);
            System.out.printf("%-10s: %3d%% ", categories[i], percentage);

            // Create visual bar for pie chart
            for (int j = 0; j < percentage / 2; j++) {
                System.out.print("█");
            }
            System.out.println();
        }

        System.out.println("\nNote: Each '█' represents 2% of the total spending.");
    }


}
