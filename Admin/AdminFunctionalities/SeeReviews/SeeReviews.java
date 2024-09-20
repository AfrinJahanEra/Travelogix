package Admin.AdminFunctionalities.SeeReviews;
import Utilities_Package.FileManager.ReviewFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeeReviews {

    private List<String> reviews = new ArrayList<>();
    private ReviewAnalyzer analyzer = new ReviewAnalyzer();
    private Comment comment = new Comment();
    ReviewFile reviewFile = new ReviewFile();

    public void reviewUserSuggestions() {
        reviewFile.loadReviewsFromFile("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\SeeReviews\\review.txt");
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

        comment.addComment(userComment);
        System.out.println("Your comment: \"" + userComment + "\" has been added to review " + reviewNumber);
        inputScanner.close();
    }
}


