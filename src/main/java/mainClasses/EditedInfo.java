package mainClasses;

public class EditedInfo {
    private String username;
    private String firstname;
    private String lastName;
    private String email;
    private String user;

    // Constructor
    public EditedInfo(String username, String firstname, String lastname, String email, String user) {
        this.username = username;
        this.firstname = firstname;
        this.lastName = lastname;
        this.email = email;
        this.user = user;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUser() {
        return user;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}