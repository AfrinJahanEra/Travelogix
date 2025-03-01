package Traveler.budget_tracker;

import Traveler.Itinerary_Management.Alarm.SoundUtils;
import Utilities.utils.AdvancedFileUtils;
import Utilities.utils.BasicFileUtils;
import Utilities.utils.BasicUtils;

import java.util.List;

public class BudgetTracker {
    private final String budgetFile;

    public BudgetTracker(String budgetFile) {
        this.budgetFile = budgetFile;
    }
    protected void showBudgetOptions() {
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
            String category = BasicUtils.takeStringInput("Enter the name of category: ");
            String expenseLimit = BasicUtils.takeStringInput("Enter expected limit for this category: ");
            int limit;
            try {
                limit = Integer.parseInt(expenseLimit);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Try again.");
                continue;
            }
            BasicFileUtils.write(budgetFile, category + "," + limit + ",0" + ",Null");
            num--;
        }
        System.out.println("✅ Category and limit set successfully!");
    }

    public void viewTotalSpending() {
        List<String> lines = BasicFileUtils.read(budgetFile);

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
            List<String> lines = BasicFileUtils.read(budgetFile);
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


                    //Alert if spending reaches/exceeds the limit

                    if (updatedSpending >= limit) {
                        System.out.println("\n⚠️ ALERT: You have reached/exceeded your spending limit for " + category + "!");
                        SoundUtils.playSound("src/Traveler/Itinerary_Management/Alarm/sparcle.wav");
                    }
                    break;
                }

            }

            if (!found) {
                System.out.println("Category not found.");
                return;
            }

            AdvancedFileUtils.clearFile(budgetFile);
            for (String line : lines) {
                BasicFileUtils.write(budgetFile, line);
            }

            System.out.println("✅ Spending updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount entered. Please enter a valid number.");
        }



    }

    /*public void consolePieChart() {
        List<String> lines = BasicFileUtils.read(budgetFile);

        if (lines.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }

        int[] limitSpending = new int[lines.size()];
        int[] spendingValues = new int[lines.size()];
        String[] categories = new String[lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = BasicFileUtils.splitIntoParts(lines.get(i));

            try {
                categories[i] = parts[0];
                spendingValues[i] = Integer.parseInt(parts[2]);
                limitSpending[i] = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Warning: Invalid data format. Skipping entry: " + lines.get(i));
                continue;
            }
        }

        System.out.println("\nExpense Chart (Spending Breakdown):");
        for (int i = 0; i < categories.length; i++) {
            if (limitSpending[i] == 0) {
                System.out.printf("%-10s: No spending limit set.\n", categories[i]);
                continue;
            }

            int percentage = (int) ((spendingValues[i] / (double) limitSpending[i]) * 100);
            System.out.printf("%-10s: %3d%% ", categories[i], percentage);

            int barLength = Math.min(percentage / 2, 50);
            for (int j = 0; j < barLength; j++) {
                System.out.print("█");
            }
            System.out.println();
        }
    }
*/

    public void consolePieChart() {
        List<String> lines = BasicFileUtils.read(budgetFile);

        if (lines.isEmpty()) {
            System.out.println("\n📌 No records to display.\n");
            return;
        }

        int[] limitSpending = new int[lines.size()];
        int[] spendingValues = new int[lines.size()];
        String[] categories = new String[lines.size()];

        System.out.println("\nExpense Breakdown (Expected (░) vs. Actual Spending (█)):\n");

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = BasicFileUtils.splitIntoParts(lines.get(i));

            try {
                categories[i] = parts[0]; // Category name
                spendingValues[i] = Integer.parseInt(parts[2]); // Actual spending
                limitSpending[i] = Integer.parseInt(parts[1]); // Budget limit
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Warning: Invalid data format. Skipping entry: " + lines.get(i));
                continue;
            }
        }

        int maxBarLength = 50; // Full width of budget bar

        for (int i = 0; i < categories.length; i++) {
            if (limitSpending[i] == 0) {
                System.out.printf("%-14s: $%-6d | Budget: N/A  | Over Budget 🚨\n", categories[i], spendingValues[i]);
                continue;
            }

            int percentage = (int) ((spendingValues[i] / (double) limitSpending[i]) * 100);

            // Print category, spending, and budget
            System.out.printf("%-14s: $%-6d | Budget: %-6d | %3d%%\n",
                    categories[i], spendingValues[i], limitSpending[i], percentage);

            // Print expected budget bar (░)
            for (int j = 0; j < maxBarLength; j++) {
                System.out.print("░");
            }
            System.out.println();

            // Print actual spending bar
            if (spendingValues[i] > 0) {
                int spendingBarLength = (percentage * maxBarLength) / 100;

                if (percentage <= 100) {
                    // Spending within budget (█)
                    for (int j = 0; j < spendingBarLength; j++) {
                        System.out.print("█");
                    }
                } else {
                    // Spending exceeds budget → Normal + Over-budget in red
                    for (int j = 0; j < maxBarLength; j++) {
                        System.out.print("█"); // Normal spending within budget
                    }
                    System.out.print("\u001B[31m"); // Start red color
                    for (int j = 0; j < spendingBarLength - maxBarLength; j++) {
                        System.out.print("█"); // Over-budget spending
                    }
                    System.out.print("\u001B[0m"); // Reset color
                }

                System.out.printf("  %3d%%", percentage);

                // If spending exceeds budget, show alert message
                if (spendingValues[i] > limitSpending[i]) {
                    System.out.print("  🚨 Over Budget!");
                }
            }

            System.out.println("\n");
        }
    }


}
