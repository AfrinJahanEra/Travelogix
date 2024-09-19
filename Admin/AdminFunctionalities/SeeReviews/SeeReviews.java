package Admin.AdminFunctionalities.SeeReviews;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeeReviews {

    private List<String> reviews = new ArrayList<>();
    private ReviewAnalyzer analyzer = new ReviewAnalyzer();
    private Comment comment = new Comment();

    public void reviewUserSuggestions() {
        loadReviewsFromFile("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\SeeReviews\\review.txt");
        displayReviewsWithFeedback();
        takeUserComment();
    }


    private void loadReviewsFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String review = fileScanner.nextLine();
                reviews.add(review);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
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


