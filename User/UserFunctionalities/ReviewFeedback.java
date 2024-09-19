package User.UserFunctionalities;
import Utilities_Package.Musers.*;
import java.io.*;

class ReviewFeedback {
    private static int reviewCount = 0;

    public void postReview(String reviewText) {
        ReviewAnalyzer analyzer = new ReviewAnalyzer();
        String feedback = analyzer.analyzeReview(reviewText);
        storeReview(reviewText, feedback);
    }

    private void storeReview(String reviewText, String feedback) {
        File reviewFile = new File("review.txt");
        reviewCount++;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reviewFile, true))) {
            writer.write(reviewCount + ". Review: " + reviewText);
            writer.newLine();
            writer.write("   Feedback: " + feedback);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Review posted and analyzed.");
    }
}

