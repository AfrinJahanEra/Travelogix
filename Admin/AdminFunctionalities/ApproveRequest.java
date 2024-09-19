package Admin.AdminFunctionalities;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Admin.AdminDashboard;

public class  ApproveRequest{
    public void approveTransportAgencyRequests(){

        AdminDashboard adminDashboard=new AdminDashboard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 if there is delete account request else enter 0");
        int userInput = scanner.nextInt();

        if(userInput==1){
            System.out.println("Request Approved");
        }
        adminDashboard.displayAdminMenu();
    }
}





// public boolean approve(String email) {
//     logRequest(email);
//     System.out.println("Request approved for: " + email);
//     return true;
// }

// private void logRequest(String email) {
//     File requestFile = new File("request.txt");
//     try (BufferedWriter writer = new BufferedWriter(new FileWriter(requestFile, true))) {
//         writer.write(email);
//         writer.newLine();
//     } catch (IOException e) {
//         e.printStackTrace();
//     }
// }