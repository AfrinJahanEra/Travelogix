// package Admin;

// import Utilities.FileManager.ReviewFile;
// import java.util.List;
// import java.util.Scanner;

// public class SeeReviews {

//     private List<String> reviews;  
    
//     private ReviewFile reviewFile = new ReviewFile();

//     public void reviewUserSuggestions() {
    
//         reviews = reviewFile.loadReviewsFromFile("src\\TXT_Files\\review.txt");

       
//         displayReviewsWithFeedback();
//         takeUserComment();
//     }
//     private void displayReviewsWithFeedback() {
//         System.out.printf("%-5s %-50s %-15s%n", "No.", "Review", "Feedback");
//         System.out.println("--------------------------------------------------------------------");
    
//         for (int i = 0; i < reviews.size(); i++) {
//             String review = reviews.get(i);
//             String feedback = analyzeReview(review);
//             System.out.printf("%-5d %-50s %-15s%n", (i + 1), review, feedback);
//         }
//     }
    

//     private void takeUserComment() {
//         Scanner inputScanner = new Scanner(System.in);

//         System.out.println("\nSelect the review number you want to comment on (1, 2, 3...): ");
//         int reviewNumber = inputScanner.nextInt();
//         inputScanner.nextLine();  

//         if (reviewNumber < 1 || reviewNumber > reviews.size()) {
//             System.out.println("Invalid selection.");
//             return;
//         }

//         System.out.println("Enter your comment for review " + reviewNumber + ": ");
//         String userComment = inputScanner.nextLine();

//         addComment(reviews.get(reviewNumber - 1), userComment);
//         System.out.println("Your comment: \"" + userComment + "\" has been added to review " + reviewNumber);

//         reviewFile.saveCommentToFile("src\\TXT_Files\\review.txt", reviewNumber - 1, userComment);

//     }

//     public String analyzeReview(String review) {
//         String lowerCaseReview = review.toLowerCase();

//         if (lowerCaseReview.contains("not") || lowerCaseReview.contains("bad") || lowerCaseReview.contains("worst")) {
//             return "Negative";
//         }else if (lowerCaseReview.contains("amazing") || lowerCaseReview.contains("good") || lowerCaseReview.contains("great")) {
//             return "Positive";
//         } else {
//             return "Neutral";
//         }
//     }
    
//     public void addComment(String review, String comment) {
//         System.out.println("Comment added for review: \"" + review + "\"");
//         System.out.println("Comment: " + comment);
//     }

// }
package Admin;

import Utilities.FileManager.ReviewFile;
import java.util.List;
import java.util.Scanner;

public class SeeReviews {

    private List<String> reviews;
    private ReviewFile reviewFile = new ReviewFile();

    public void reviewUserSuggestions() {
        reviews = reviewFile.loadReviewsFromFile("src\\TXT_Files\\review.txt");
        displayReviewsWithFeedback();
        takeUserComment();
    }

    private void displayReviewsWithFeedback() {
        final int reviewColumnWidth = 50; // Width for the review column

        System.out.println("╔═════╦══════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-3s ║ %-50s ║ %-13s ║%n", "No.", "Review", "Feedback");
        System.out.println("╠═════╬══════════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < reviews.size(); i++) {
            String review = reviews.get(i);
            String feedback = analyzeReview(review);
            printRow(i + 1, review, feedback, reviewColumnWidth);
        }

        System.out.println("╚═════╩═════════════════════════════════════════════════════════════════════╝");
    }

    private void printRow(int number, String review, String feedback, int reviewColumnWidth) {
        // Split the review into multiple lines if it's too long
        String[] reviewLines = splitIntoLines(review, reviewColumnWidth);

        // Print the first line with the row number and feedback
        System.out.printf("║ %-3d ║ %-50s ║ %-13s ║%n", number, reviewLines[0], feedback);

        // Print the remaining lines of the review (if any) without row number or feedback
        for (int i = 1; i < reviewLines.length; i++) {
            System.out.printf("║     ║ %-50s ║               ║%n", reviewLines[i]);
        }
    }

    private String[] splitIntoLines(String text, int lineWidth) {
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        List<String> lines = new java.util.ArrayList<>();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > lineWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }

        // Add the last line
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines.toArray(new String[0]);
    }

    private void takeUserComment() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("\nSelect the review number you want to comment on (1, 2, 3...): ");
        int reviewNumber = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume newline

        if (reviewNumber < 1 || reviewNumber > reviews.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        System.out.println("Enter your comment for review " + reviewNumber + ": ");
        String userComment = inputScanner.nextLine();

        addComment(reviews.get(reviewNumber - 1), userComment);
        System.out.println("Your comment: \"" + userComment + "\" has been added to review " + reviewNumber);

        reviewFile.saveCommentToFile("src\\TXT_Files\\review.txt", reviewNumber - 1, userComment);
    }

    public String analyzeReview(String review) {
        String lowerCaseReview = review.toLowerCase();

        if (lowerCaseReview.contains("not") || lowerCaseReview.contains("bad") || lowerCaseReview.contains("worst")) {
            return "Negative";
        } else if (lowerCaseReview.contains("amazing") || lowerCaseReview.contains("good") || lowerCaseReview.contains("great")) {
            return "Positive";
        } else {
            return "Neutral";
        }
    }

    public void addComment(String review, String comment) {
        System.out.println("Comment added for review: \"" + review + "\"");
        System.out.println("Comment: " + comment);
    }
}
