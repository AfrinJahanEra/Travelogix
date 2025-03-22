package Test;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

import Admin.SeeReviews;

public class ReviewAnalysisTest {
    
    SeeReviews seeReviews=new SeeReviews();

    @Test
    public void testAnalyzeReview_Positive() {
        String review = "This was an amazing experience!";
        String result = seeReviews.analyzeReview(review);
        assertEquals("Positive", result);
    }

    @Test
    public void testAnalyzeReview_Negative() {
        String review = "This was the worst service I have ever experienced.";
        String result = seeReviews.analyzeReview(review);
        assertEquals("Negative", result);
    }

    @Test
    public void testAnalyzeReview_Neutral() {
        String review = "It was okay, nothing special.";
        String result = seeReviews.analyzeReview(review);
        assertEquals("Neutral", result);
    }

    @Test
    public void testAnalyzeReview_MixedSentiment() {
        String review = "The trip was good but not exceptional.";
        String result = seeReviews.analyzeReview(review);
        // Depending on your logic, adjust this test if necessary
        assertEquals("Neutral", result);
    }
}
