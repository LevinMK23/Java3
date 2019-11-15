import java.sql.*;
import java.util.Arrays;


public class DBClass {
  
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager
                .getConnection("jdbc:sqlite:homework.db");
        Statement stmt = conn.createStatement();
        String fieldName1 = "id";
        String fieldName2 = "msg";
        stmt.execute("CREATE TABLE IF NOT EXISTS lol(" + fieldName1 + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + fieldName2 + " TEXT);");
        String type = "'Laptop'";
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT * FROM Product WHERE type=" + type + ";");
        while (rs.next()) {
            System.out.println(rs.getString("maker") + " "
            + rs.getInt("model") + " " + rs.getString("type"));
        }
        stmt.execute("CREATE table IF NOT EXISTS users(id INTEGER, name TEXT);");
        //PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name) values(?, ?);");
        for (int i = 0; i < 100; i++) {
            User u = new User(i+1, "Ivan " + (i + 1));
            stmt.executeUpdate("INSERT INTO users(id, name) VALUES (" + u.getId() +", '" +u.getName() +"');");
        }
        //System.out.println(Arrays.toString(ps.executeBatch()));
        //rs = stmt.executeQuery("SELECT * FROM users");
//        while (rs.next()){
//            System.out.println(rs.getInt(1) + " " + rs.getString(2));
//        }
    }
}
