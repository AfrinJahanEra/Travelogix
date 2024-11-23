package Authentication;

public class User {
    protected String role;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected String encryptedPassword;

    public User(String role, String name, String phoneNumber, String email, String encryptedPassword) {
        this.role = role;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
