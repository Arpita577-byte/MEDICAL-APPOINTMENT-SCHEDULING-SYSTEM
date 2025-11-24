package model;

/**
 * Abstract User class for Patient and Doctor.
 */
public abstract class User {
    protected String id;
    protected String name;
    protected String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public boolean authenticate(String pwd) { return password.equals(pwd); }
}
