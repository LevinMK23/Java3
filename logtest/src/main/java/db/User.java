package db;
@XTable(title = "users")
public class User {
    @XField
    int id;
    @XField
    String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
