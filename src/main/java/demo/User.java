package demo;

/**
 * A basic User class.
 */
public class User {
    private String username;
    private String password;
    private int role;

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        // 0 for generic users (no role), 1 for employees, 2 for customers
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() { return role; }

    public void setRole(int role) { this.role = role; }
}
