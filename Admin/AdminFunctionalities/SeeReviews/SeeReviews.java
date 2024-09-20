package Admin.AdminFunctionalities.SeeReviews;

import Utilities_Package.FileManager.ReviewFile;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class SeeReviews {

    private List<String> reviews;  
    private ReviewAnalyzer analyzer = new ReviewAnalyzer();
    private Comment comment = new Comment();
    private ReviewFile reviewFile = new ReviewFile();

    public void reviewUserSuggestions() {
        // Load reviews from the file
        reviews = reviewFile.loadReviewsFromFile("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\SeeReviews\\review.txt");

       
        displayReviewsWithFeedback();
        takeUserComment();
    }

    private void displayReviewsWithFeedback() {
        System.out.println("Reviews with Feedback:");
        for (int i = 0; i < reviews.size(); i++) {
            String review = reviews.get(i);
            String feedback = analyzer.analyzeReview(review);
            System.out.println((i + 1) + ". " + review + " [" + feedback + "]");
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

        comment.addComment(reviews.get(reviewNumber - 1), userComment);
        System.out.println("Your comment: \"" + userComment + "\" has been added to review " + reviewNumber);

        reviewFile.saveCommentToFile("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\SeeReviews\\review.txt", reviewNumber - 1, userComment);

    }
}
