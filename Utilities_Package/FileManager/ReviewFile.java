package Utilities_Package.FileManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ReviewFile {
    public void loadReviewsFromFile(String filename) {

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
    }
}
