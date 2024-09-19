package Admin.AdminFunctionalities.SeeReviews;
import Admin.AdminDashboard;

public class Comment {
    public void addComment(String comment) {
        AdminDashboard adminDashboard = new AdminDashboard();
        System.out.println("Comment added: " + comment);
        adminDashboard.displayAdminMenu();
    }
}
