package classWork.java3_02_2020.hibernate;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HiberCore {

    private static Connection con;
    private static Statement stmt;
    static {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:DB");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static void createTableForClass(Object object) throws SQLException {
        Class c = object.getClass();
        String name = c.getSimpleName();
        ArrayList<Field> hiberFields = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            if (field.isAnnotationPresent(HiberField.class)) {
                hiberFields.add(field);
            }
        }
        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS " + name + "(");
        for (Field field : hiberFields) {
            query.append(field.getName())
                    .append(' ')
                    .append(field.getAnnotation(HiberField.class).type())
                    .append(", ");
        }
        query.delete(query.length()-2, query.length()).append(");");
        System.out.println(query);
        stmt.execute(query.toString());
    }

    static void insertObject(Object object) throws IllegalAccessException, SQLException {
        Class c = object.getClass();
        String tableName = object.getClass().getSimpleName();
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " VALUES(");
        for (Field field : c.getDeclaredFields()) {
            if (field.isAnnotationPresent(HiberField.class)) {
                Object value = field.get(object);
                if (value instanceof String) {
                    value = "'" + value + "'";
                }
                query.append(value).append(", ");
            }
        }
        query.delete(query.length()-2, query.length()).append(");");
        System.out.println(query);
        stmt.execute(query.toString());
    }

    static ArrayList<ArrayList<Object>> selectAll(Class<?> cl) throws SQLException {
        String tableName = cl.getSimpleName();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");

        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new ArrayList<>());
            for (Field field : cl.getDeclaredFields()) {
                if (field.isAnnotationPresent(HiberField.class)) {
                    String name = field.getName();
                    result.get(result.size()-1).add(rs.getString(name));
                }
            }
        }
        return result;
    }
}
