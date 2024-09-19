package Admin.AdminFunctionalities;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class SeeReview {
    public void viewReviews() {
        File reviewFile = new File("review.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(reviewFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commentOnReview(int reviewNumber, String comment) {
        List<String> reviews = new ArrayList<>();
        File reviewFile = new File("review.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(reviewFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reviews.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int reviewIndex = findReviewIndex(reviews, reviewNumber);
        if (reviewIndex != -1) {
            reviews.add(reviewIndex + 2, "   Comment: " + comment);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(reviewFile))) {
                for (String review : reviews) {
                    writer.write(review);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Comment added.");
        } else {
            System.out.println("Review number not found.");
        }
    }

    private int findReviewIndex(List<String> reviews, int reviewNumber) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).startsWith(reviewNumber + ". Review")) {
                return i;
            }
        }
        return -1;
    }
}
