package db2;

import db.XField;
import db.XTable;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class DBUtility {

    private static Connection con;
    private static Statement stmt;

    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = con.createStatement();
    }
    public static void disconnect(){
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //create table cats (id INTEGER, name TEXT, msg TEXT, hp INTEGER, age INTEGER );
    public static void createTable(Class clazz) throws SQLException {
        String name = ((BaseTable)clazz.getAnnotation(BaseTable.class)).name();
        //stmt.execute("DROP TABLE IF EXISTS " + name + ";");
        HashMap<Class, String> map = new HashMap<>();
        map.put(int.class, "INTEGER");
        map.put(String.class, "TEXT");
        if(clazz.isAnnotationPresent(BaseTable.class)) {
            String query = "create table if not exists ";
            query += name + " (";
            for(Field field : clazz.getDeclaredFields()){
                if(field.isAnnotationPresent(BaseItem.class)){
                    query += field.getName() + " " + map.get(field.getType()) + ", ";
                }
            }
            query = query.substring(0, query.length()-2) + ");";
            System.out.println(query);
            stmt.execute(query);
        }
    }

    public static void insert(Object obj) throws SQLException, IllegalAccessException {
        Class clazz = obj.getClass();
        if(!clazz.isAnnotationPresent(BaseTable.class)){
            return;
        }
        String tName = ((BaseTable)clazz
                .getAnnotation(BaseTable.class)).name();
        //INSERT INTO title (f1, f2) VALUES()
        StringBuilder query = new StringBuilder("INSERT INTO " + tName + "(");
        Field [] fields = clazz.getFields();
        for (Field field : fields){
            System.out.println(field);
            if(field.isAnnotationPresent(BaseItem.class)){
                query.append(field.getName()).append(", ");
            }
        }
        query.delete(query.length()-2, query.length()-1)
                .append(") VALUES(");
        System.out.println(query);
        for (Field field : fields){
            System.out.println(field);
            if(field.isAnnotationPresent(BaseItem.class)){
                query.append("?, ");
            }
        }
        query.delete(query.length()-2, query.length()-1)
                .append(");");
        System.out.println(query);
        PreparedStatement ps = con.prepareStatement(query.toString());
        int counter = 1;
        for (Field field : fields){
            System.out.println(field);
            if(field.isAnnotationPresent(BaseItem.class)){
                ps.setObject(counter, field.get(obj));
                counter++;
            }
        }
        ps.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        connect();
        createTable(Cat.class);
        Cat cat = new Cat();
        insert(cat);
        insert(cat);
        insert(cat);
        insert(cat);
        disconnect();
    }
}
