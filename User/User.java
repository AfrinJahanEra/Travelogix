package User;

public class User {
    private String email;
    private String password;
    private String suggestion;
    private String review;
    

    public User(String email, String password,String suggestion,String review) {
        this.email = email;
        this.password = password;
        this.suggestion=suggestion;
        this.review=review;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getReview() {
        return review;
    }

    public String getSuggestion() {
        return suggestion;
    }

}
