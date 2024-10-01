package Utilities_Package.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewFile {

    // Method to load reviews from file
    public List<String> loadReviewsFromFile(String filename) {
        List<String> reviews = new ArrayList<>();

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

        return reviews;
    }

    // save a comment to the file under the specific review
    public void saveCommentToFile(String filename, int reviewIndex, String comment) {
        List<String> reviews = loadReviewsFromFile(filename);

        if (reviewIndex >= 0 && reviewIndex < reviews.size()) {
            String reviewWithComment = reviews.get(reviewIndex) + " [Comment: " + comment + "]";

            // Update the review with the comment
            reviews.set(reviewIndex, reviewWithComment);

            try (FileWriter writer = new FileWriter(filename)) {
                for (String review : reviews) {
                    writer.write(review + "\n");
                }
                System.out.println("Comment saved to file.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + filename);
            }
        } else {
            System.out.println("Invalid review index.");
        }
    }
}
