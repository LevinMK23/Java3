import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DBTests {

    Connection con;
    Statement stmt;
    DBUtility util;

    @Before
    public void starter() {
        try {
            util = new DBUtility();
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:/Users/liliakobleva/Developer/Java/Java3/homework.db");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closer(){
        try {
            stmt.close();
            con.close();
        }catch (Exception ignored){

        }
    }

    @Test
    public void testTable() throws SQLException {
        stmt.execute("DROP TABLE IF EXISTS Printer");
        util.createPrinterTable(con, stmt);
        ResultSet res = stmt.executeQuery("SELECT * FROM Printer");
        int cnt = 0;
        while (res.next())cnt++;
        Assert.assertEquals(3, cnt);
    }

    @Test
    public void testExpensivePC(){
        ArrayList<String> list = util.selectExpensivePC(stmt);
        list.sort(Comparator.comparing(o->o));
        Assert.assertArrayEquals(new String[]{"2205", "2210"}, list.toArray());
    }

    @Test
    public void testQuickLaptop(){
        ArrayList<Integer> list = util.selectQuickLaptop(stmt);
        Collections.sort(list);
        Assert.assertArrayEquals(new Integer[]{3, 7}, list.toArray());
    }

    @Test
    public void testMaker(){
        ArrayList<String> list = util.selectMaker(stmt);
        Collections.sort(list);
        Assert.assertArrayEquals(new String[]{"Intel"}, list.toArray());
    }

    @Test
    public void maxCostTest(){
        int max = util.makerWithMaxProceeds(stmt);
        Assert.assertEquals(140000, max);
    }


}
