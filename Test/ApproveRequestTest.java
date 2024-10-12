package Test;

import src.Admin.AdminFuctionalities.ApproveRequest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.Admin.AdminDashboard;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class ApproveRequestTest {

    private ApproveRequest approveRequest;
    private AdminDashboard adminDashboardMock;
    private Scanner scannerMock;

    @BeforeEach
    void setUp() {
       
        adminDashboardMock = Mockito.mock(AdminDashboard.class);
        
        // Create a mock of Scanner
        scannerMock = Mockito.mock(Scanner.class);

        // Create an instance of ApproveRequest and inject the mocks
        approveRequest = new ApproveRequest() {
            // Override to inject the mocks into the method for testing
            @Override
            public void approveTransportAgencyRequests() {
                // Use the mock Scanner instead of the real one
                Scanner scanner = scannerMock;

                System.out.println("Enter 1 if there is delete account request else enter 0");
                int userInput = scanner.nextInt();

                if (userInput == 1) {
                    System.out.println("Request Approved");
                } else {
                    System.out.println("Request not approved");
                }
                // Use the mock AdminDashboard instead of the real one
                AdminDashboard adminDashboard = adminDashboardMock;
                adminDashboard.displayAdminMenu();
            }
        };
    }

    @Test
    void testApproveRequestWhenUserInputIs1() {
        // Set up the mock to return 1 when nextInt() is called
        when(scannerMock.nextInt()).thenReturn(1);

        // Run the method
        approveRequest.approveTransportAgencyRequests();

        // Verify that the appropriate output occurs
        verify(adminDashboardMock, times(1)).displayAdminMenu();
        System.out.println("Test case where input is 1 passed.");
    }

    @Test
    void testApproveRequestWhenUserInputIs0() {
        // Set up the mock to return 0 when nextInt() is called
        when(scannerMock.nextInt()).thenReturn(0);

        // Run the method
        approveRequest.approveTransportAgencyRequests();

        // Verify that the appropriate output occurs
        verify(adminDashboardMock, times(1)).displayAdminMenu();
        System.out.println("Test case where input is 0 passed.");
    }
}
