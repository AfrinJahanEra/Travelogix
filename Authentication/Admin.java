package Authentication;

public class Admin {
    private String email;
    private String password;
    private String contactNumber;

    public Admin(String email, String password, String contactNumber) {
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
