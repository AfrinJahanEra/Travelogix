import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PasswordField {

    /**
     * @param prompt The prompt to display to the user
     * @return The password as entered by the user
     */
    public static String readPassword(String prompt) {
        EraserThread et = new EraserThread(prompt);
        Thread mask = new Thread(et);
        mask.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            password = in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Stop masking immediately after password is read
        et.stopMasking();
        try {
            // Wait for the masking thread to finish
            mask.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Clear the last printed '*'
        System.out.print("\b \b");

        // Return the password entered by the user
        return password;
    }
}

