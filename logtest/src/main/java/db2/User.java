package db2;
@BaseTable(name = "users")
public class User {
    @BaseItem
    int id;
    @BaseItem
    String name;
}
