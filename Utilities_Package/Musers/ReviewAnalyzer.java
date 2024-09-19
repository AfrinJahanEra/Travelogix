package Utilities_Package.Musers;

public class ReviewAnalyzer {
    public String analyzeReview(String reviewText) {
        if (reviewText.toLowerCase().contains("amazing") || reviewText.toLowerCase().contains("great")) {
            return "Positive";
        } else if (reviewText.toLowerCase().contains("bad") || reviewText.toLowerCase().contains("terrible")) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }
}