package Agency;
import Bus.*;
import java.util.Scanner;

public class Agency {
    Buslist buslist = new Buslist();

    public void agency()
    {
        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            System.out.println("1.add bus");
            System.out.println("2.view details");
            System.out.println("3.edit details");
            System.out.println("4. delete details");
            System.out.println("5.exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    Addbus addbus = new Addbus();
                    addbus.inputBusDetails();
                    addbus.saveToFile();
                    break;

                case 2:
                    buslist.list();
                    int n;
                    Scanner sc1 = new Scanner(System.in);
                    System.out.print("Enter the index of the bus: ");
                    n = sc1.nextInt();
                    Viewdetails viewdetails = new Viewdetails();
                    viewdetails.showdetails(n);
                    break;

                case 3:
                    buslist.list();
                    int n1;
                    Scanner sc2 = new Scanner(System.in);
                    System.out.print("Enter the index of the bus: ");
                    n1 = sc2.nextInt();
                    Editdetails editdetails = new Editdetails();
                    editdetails.editdetails(n1);
                    break;

                case 4:
                    buslist.list();
                    int n2;
                    Scanner sc3 = new Scanner(System.in);
                    System.out.print("Enter the index of the bus: ");
                    n2 = sc3.nextInt();
                    Deletedetails deletedetails = new Deletedetails();
                    deletedetails.deletedetails(n2);
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        while (choice != 5);
    }
}
