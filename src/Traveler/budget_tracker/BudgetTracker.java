package Traveler.budget_tracker;

import Traveler.Itinerary_Management.Alarm.SoundUtils;
import Utilities.utils.AdvancedFileUtils;
import Utilities.utils.BasicFileUtils;
import Utilities.utils.BasicUtils;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker {
    private final String budgetFile;

    public BudgetTracker(String budgetFile) {
        this.budgetFile = budgetFile;
    }
    protected void showBudgetOptions() {
        while (true) {

            waitForEnterKey();
            clearTerminal();


            System.out.println("\n                                              ╔══════════════════════════════════════════╗");
            System.out.println("                                              ║              BUDGET TRACKER              ║");
            System.out.println("                                              ╠══════════════════════════════════════════╣");
            System.out.println("                                              ║                                          ║");
            System.out.println("                                              ║    [1] Set Categories and Limits         ║");
            System.out.println("                                              ║    [2] View Total Spending               ║");
            System.out.println("                                              ║    [3] Update Spending                   ║");
            System.out.println("                                              ║    [4] Budget Overview                   ║");
            System.out.println("                                              ║    [5] Back to Main Menu                 ║");
            System.out.println("                                              ║                                          ║");
            System.out.println("                                              ╚══════════════════════════════════════════╝");

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
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void setCategoryAndLimit() {
        String numOfCategories = BasicUtils.takeStringInput("How many categories you want to add? : ");
        int num;
        try {
            num = Integer.parseInt(numOfCategories);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Try again.");
            return;
        }

        while (num > 0) {
            String category = BasicUtils.takeStringInput("Enter the name of category: ");

          
            String foundCategory = BasicFileUtils.search(budgetFile, category.toLowerCase());  

            if (foundCategory != null) {
                System.out.println("This category already exists.");
                num--;
            } else {
                String expenseLimit = BasicUtils.takeStringInput("Enter expected limit for this category: ");
                int limit;
                try {
                    limit = Integer.parseInt(expenseLimit);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Try again.");
                    continue;
                }
                
                BasicFileUtils.write(budgetFile, category.toLowerCase() + "," + limit + ",0");
                num--;
                System.out.println("Category and limit set successfully!");
            }
        }
    }


    public void viewTotalSpending() {
        List<String> lines = BasicFileUtils.read(budgetFile);

        if (lines.isEmpty()) {
            System.out.println("No records yet.");
            return;
        }

        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("║        Category        ║        Budget       ║      Spending      ║          Remarks          ║     Remaining      ║");
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");

        for (String line : lines) {
            String[] parts = line.split(",");
            String category = parts[0];
            int budget = Integer.parseInt(parts[1]);
            int totalSpending = Integer.parseInt(parts[2]);
            int extra = budget - totalSpending;

            String plainRemaining = String.valueOf(Math.abs(extra)); 
            int columnWidth = 18; 

            String formattedRemaining = String.format("%-" + columnWidth + "s", plainRemaining);
            
            String remaining;
            if (extra < 0) {
                
                formattedRemaining = "\u001B[31m" + formattedRemaining + "\u001B[0m"; 
                
            } else {
                remaining = String.valueOf(extra);
            }

            if (parts.length == 3) { 
                System.out.printf("║ %-22s ║ %-19d ║ %-18s ║ %-25s ║ %-18s ║\n",
                        category, budget, "0", "null", formattedRemaining);
            } else {
                for (int i = 3; i < parts.length; i++) {
                    String[] entryParts = parts[i].split("@");
                    if (entryParts.length < 2) continue; 

                    String remark = entryParts[0];
                    String spending = entryParts[1];

                    boolean firstRow = (i == 3); 

                    if (i == 3) {
                        System.out.printf("║ %-22s ║ %-19d ║ %-18s ║ %-25s ║ %-18s ║\n",
                                category, budget, spending, remark, firstRow ? formattedRemaining : 0);
                    } else {
                        System.out.printf("║ %-22s ║ %-19s ║ %-18s ║ %-25s ║ %-18s ║\n",
                                "", "", spending, remark, firstRow ? formattedRemaining : "");
                    }
                }
            }

            System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        }
    }





    public void updateSpending() {
        String category = BasicUtils.takeStringInput("Enter the category you spent on: ");
        String foundCategory = BasicFileUtils.search(budgetFile, category);

        if (foundCategory == null) {
            System.out.println("This category doesn't exist.");
            return;
        }

        String spendingInput = BasicUtils.takeStringInput("Enter the amount you spent: ");
        String remarks = BasicUtils.takeStringInput("Enter remarks for this spending: ");

        String updatedRemarks = remarks+"@"+spendingInput;

        try {
            int spending = Integer.parseInt(spendingInput);
            List<String> lines = BasicFileUtils.read(budgetFile);
            boolean found = false;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",");
                int partsLength = parts.length;

                if (parts[0].equalsIgnoreCase(category)) {
                    int budget = Integer.parseInt(parts[1]);
                    int currentSpending = Integer.parseInt(parts[2]);
                    int updatedSpending = currentSpending + spending;
                    String remarksAndSpendings = "";

                    if(partsLength>3){
                        for(int j=3;j<partsLength;j++){
                            remarksAndSpendings+=parts[j]+",";
                        }
                    }
                    lines.set(i, parts[0] + "," + parts[1] + "," + updatedSpending + "," +remarksAndSpendings+ updatedRemarks);
                    found = true;

                    if (updatedSpending >= budget) {
                        System.out.println("\nALERT: You have reached/exceeded your spending limit for " + category + "!");
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

            System.out.println("Spending updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered. Please enter a valid number.");
        }
    }


    private void waitForEnterKey() {
        System.out.println("\nPress ENTER to continue...");
        Scanner enterScanner = new Scanner(System.in);
        enterScanner.nextLine();
    }

    private void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to clear terminal.");
        }
    }

    public void consolePieChart() {
        List<String> lines = BasicFileUtils.read(budgetFile);

        if (lines.isEmpty()) {
            System.out.println("\n📌 No records to display.\n");
            return;
        }

        int[] limitSpending = new int[lines.size()];
        int[] spendingValues = new int[lines.size()];
        String[] categories = new String[lines.size()];

        System.out.println("\nExpense Breakdown (Expected (▓) vs. Actual Spending (█)):\n");

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");

            try {
                categories[i] = parts[0]; 
                spendingValues[i] = Integer.parseInt(parts[2]); 
                limitSpending[i] = Integer.parseInt(parts[1]); 
            } catch (NumberFormatException e) {
                System.out.println("Warning: Invalid data format. Skipping entry: " + lines.get(i));
                continue;
            }
        }

        int maxBarLength = 50; 

        for (int i = 0; i < categories.length; i++) {
            if (limitSpending[i] == 0) {
                System.out.printf("\n%-14s\nSpent: %-6d | Budget: N/A  | Over Budget 🚨\n",
                        categories[i], spendingValues[i]);
                continue;
            }

            int percentage = (int) ((spendingValues[i] / (double) limitSpending[i]) * 100);

            
            System.out.printf(categories[i]+"\n");
            System.out.printf("Spent: %-6d | Budget: %-6d | %3d%%\n",
                    spendingValues[i], limitSpending[i], percentage);

           
            for (int j = 0; j < maxBarLength; j++) {
                System.out.print("▓");
            }
            System.out.println();

            
            if (spendingValues[i] > 0) {
                int spendingBarLength = (percentage * maxBarLength) / 100;

                if (percentage <= 100) {
                    
                    for (int j = 0; j < spendingBarLength; j++) {
                        System.out.print("█");
                    }
                } else {
                   
                    for (int j = 0; j < maxBarLength; j++) {
                        System.out.print("█"); 
                    }
                    System.out.print("\u001B[31m"); 
                    for (int j = 0; j < spendingBarLength - maxBarLength; j++) {
                        System.out.print("█"); 
                    }
                    System.out.print("\u001B[0m"); 

                System.out.printf("  %3d%%", percentage);

              
                if (spendingValues[i] > limitSpending[i]) {
                    System.out.print("   Over Budget!");
                }
            }

            System.out.println("\n");
        }
    }


    }
}