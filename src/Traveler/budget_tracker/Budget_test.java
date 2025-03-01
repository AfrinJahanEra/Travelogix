package Traveler.budget_tracker;

import Utilities.utils.BasicUtils;

public class Budget_test {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n🌍 Travel & Budget Management System");
            System.out.println("1. View Upcoming Trips & Manage Budget");
            System.out.println("2. Exit");

            String choiceStr = BasicUtils.takeStringInput("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    Budget.selectTripAndManageBudget();
                    break;
                case 2:
                    System.out.println("Goodbye! 👋");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}