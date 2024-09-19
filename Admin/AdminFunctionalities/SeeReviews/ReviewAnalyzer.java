package Admin.AdminFunctionalities.SeeReviews;

public class ReviewAnalyzer {

    // Method to analyze and classify reviews
    public String analyzeReview(String review) {
        String lowerCaseReview = review.toLowerCase();

        // Simple classification based on keywords
        if (lowerCaseReview.contains("amazing") || lowerCaseReview.contains("good") || lowerCaseReview.contains("great")) {
            return "Positive";
        } else if (lowerCaseReview.contains("not") || lowerCaseReview.contains("bad") || lowerCaseReview.contains("worst")) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }
}

