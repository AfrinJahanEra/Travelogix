package src.Admin.AdminFuctionalities;

import java.util.Scanner;

import src.Admin.AdminDashboard;

public class  ApproveRequest{
    public void approveTransportAgencyRequests(){

        AdminDashboard adminDashboard=new AdminDashboard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 if there is delete account request else enter 0");
        int userInput = scanner.nextInt();

        if(userInput==1){
            System.out.println("Request Approved");
        }
        else{
            System.out.println("Request not approved");
        }
        adminDashboard.displayAdminMenu();
    }
}

