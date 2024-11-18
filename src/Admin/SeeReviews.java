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
        System.out.printf("%-5s %-50s %-15s%n", "No.", "Review", "Feedback");
        System.out.println("--------------------------------------------------------------------");
    
        for (int i = 0; i < reviews.size(); i++) {
            String review = reviews.get(i);
            String feedback = analyzeReview(review);
            System.out.printf("%-5d %-50s %-15s%n", (i + 1), review, feedback);
        }
    }
    

    private void takeUserComment() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("\nSelect the review number you want to comment on (1, 2, 3...): ");
        int reviewNumber = inputScanner.nextInt();
        inputScanner.nextLine();  

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
        }else if (lowerCaseReview.contains("amazing") || lowerCaseReview.contains("good") || lowerCaseReview.contains("great")) {
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
