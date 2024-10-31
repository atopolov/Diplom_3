package api;

import lombok.Data;

// Class for user with email, password and name
@Data
public class User {
    private String email;
    private String password;
    private String name;

// Constructor with email, password and name
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

// Constructor with email and password
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}