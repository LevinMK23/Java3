package classWork.java3_02_2020.hibernate;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        //HiberCore.createTableForClass(new User());
//        HiberCore.insertObject(new User("Ivan3", 125));
//        HiberCore.insertObject(new User("Ivan1", 17));
//        HiberCore.insertObject(new User("Ivan2", 15));
        System.out.println(HiberCore.selectAll(User.class));
    }
}
