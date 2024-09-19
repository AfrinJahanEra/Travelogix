package Admin.AdminFunctionalities;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ApproveRequest {
    public boolean approve(String email) {
        logRequest(email);
        System.out.println("Request approved for: " + email);
        return true;
    }

    private void logRequest(String email) {
        File requestFile = new File("request.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(requestFile, true))) {
            writer.write(email);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
