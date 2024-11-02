package Admin;

import java.util.Scanner;

public class  ApproveRequest{
    
    public void approveTransportAgencyRequests(){

        AdminDashBoard adminDashboard=new AdminDashBoard();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1 if there is delete account request else enter 0");
        int userInput = scanner.nextInt();

        if( userInput == 1){
            System.out.println(" Request Approved ");
        }
        else{
            System.out.println(" Request not approved ");
        }

        adminDashboard.displayAdminMenu();
    }
}

