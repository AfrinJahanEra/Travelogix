package Traveler.budget_tracker;

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

    
}

