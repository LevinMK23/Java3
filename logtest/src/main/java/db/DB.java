package db;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class DB {

    private static Connection connection;
    private static Statement st;

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        st = connection.createStatement();
    }

    private static void disconnect() throws SQLException {
        st.close();
        connection.close();
    }

    public static void createTable(Class clazz) throws SQLException {
        HashMap<Class, String> map = new HashMap<>();
        map.put(String.class, "TEXT");
        map.put(int.class, "INTEGER");
        if(!clazz.isAnnotationPresent(XTable.class)){
            return;
        }
        String tName = ((XTable)clazz.getAnnotation(XTable.class)).title();
        st.executeUpdate("drop table if exists " + tName + ";");
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(tName).append(" (");
        Field [] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(XField.class)) {
                query.append(field.getName()).append(" ").append(map.get(field.getType())).append(", ");
            }
        }
        query.delete(query.length()-2, query.length()-1);
        query.append(");");
        System.out.println(query);
        st.execute(query.toString());
    }

    public static void insert(Object obj) throws SQLException, IllegalAccessException {
        Class clazz = obj.getClass();
        if(!clazz.isAnnotationPresent(XTable.class)){
            return;
        }
        String tName = ((XTable)clazz
                .getAnnotation(XTable.class)).title();
        //INSERT INTO title (f1, f2) VALUES()
        StringBuilder query = new StringBuilder("INSERT INTO " + tName + "(");
        Field [] fields = clazz.getFields();
        for (Field field : fields){
            System.out.println(field);
            if(field.isAnnotationPresent(XField.class)){
                query.append(field.getName()).append(", ");
            }
        }
        query.delete(query.length()-2, query.length()-1)
                .append(") VALUES(");
        System.out.println(query);
        for (Field field : fields){
            System.out.println(field);
            if(field.isAnnotationPresent(XField.class)){
                query.append("?, ");
            }
        }
        query.delete(query.length()-2, query.length()-1)
                .append(");");
        System.out.println(query);
        PreparedStatement ps = connection.prepareStatement(query.toString());
        int counter = 1;
        for (Field field : fields){
            System.out.println(field);
            if(field.isAnnotationPresent(XField.class)){
                ps.setObject(counter, field.get(obj));
                counter++;
            }
        }
        ps.executeUpdate();

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try{
            connect();
            insert(new Cat());
            insert(new Cat(1, "Vaska", 100, 1));
            insert(new Cat(2, "Oleg", 35, 2));
            //createTable(User.class);
            //createTable(Cat.class);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
