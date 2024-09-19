import java.util.Scanner;

public class User {
    String name;
    int selectedBusIndex;
    int selectedSeatIndex;

    public void inputUserDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        this.name = sc.nextLine();
        System.out.println("Congo!");
    }

    public void showDetails()
    {
        System.out.println("Your name: "+ name);

    }
}
