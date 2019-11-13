import java.sql.SQLException;

public class HomeWorkLesson2 {

    public static void main(String[] args){
        DBTests tests = new DBTests();
        tests.starter();
        try {
            tests.testTable();
            tests.testExpensivePC();
            tests.testQuickLaptop();
            tests.maxCostTest();
            tests.testMaker();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            tests.closer();
        }
    }
}
