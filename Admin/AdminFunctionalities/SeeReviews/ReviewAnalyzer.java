package Admin.AdminFunctionalities.SeeReviews;

public class ReviewAnalyzer {

    // Method to analyze and classify reviews
    public String analyzeReview(String review) {
        String lowerCaseReview = review.toLowerCase();

        if (lowerCaseReview.contains("not") || lowerCaseReview.contains("bad") || lowerCaseReview.contains("worst")) {
            return "Negative";
        // Simple classification based on keywords
        }else if (lowerCaseReview.contains("amazing") || lowerCaseReview.contains("good") || lowerCaseReview.contains("great")) {
            return "Positive";
        } else {
            return "Neutral";
        }
    }
}
