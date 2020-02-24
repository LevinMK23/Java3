import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    /*
    * Каждый из тасков решается одним SQL запросом
     */

    /*
    Создать таблицу принтеры, Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)
    добавить в нее 3 записи:
    1 1012 col laser 20000 (производитель HP)
    2 1010 bw jet 5000 (производитель Canon)
    3 1010 bw jet 5000 (производитель Canon)
    Каждая вставка в таблицу принтер должна отражаться добавлением записи в таблицу продукт
     */


    void AddPrinters(Statement stmt, Printer printer) throws SQLException{
        
        stmt.execute("INSERT INTO printers(model,color,type,price) VALUES("+ printer.getModel() +",'"
                + printer.getColor() +"','"+ printer.getType() +"',"+ printer.getPrice()+ ");");
        stmt.execute("INSERT INTO Product VALUES('"+ printer.getMaker()+"',"
                + printer.getModel() +",'"+ printer.getType() +"');");
    }
    void AddPrinters(Statement stmt){
        // TODO: 16.12.2019
        Printer HP = new Printer(1012,"HP","col","laser",20000);
        Printer Canon1 = new Printer(1010,"Canon","bw","jet",5000);
        Printer Canon2 = new Printer(1010,"Canon","bw","jet",5000);
        try {
            stmt.execute("INSERT INTO printers(model,color,type,price) VALUES(" + HP.getModel() + ",'"
                    + HP.getColor() + "','" + HP.getType() + "'," + HP.getPrice() + ");");
            stmt.execute("INSERT INTO Product VALUES('" + HP.getMaker() + "',"
                    + HP.getModel() + ",'" + HP.getType() + "');");
            
            stmt.execute("INSERT INTO printers(model,color,type,price) VALUES(" + Canon1.getModel() + ",'"
                    + Canon1.getColor() + "','" + Canon1.getType() + "'," + Canon1.getPrice() + ");");
            stmt.execute("INSERT INTO Product VALUES('" + Canon1.getMaker() + "',"
                    + Canon1.getModel() + ",'" + Canon1.getType() + "');");
            
            stmt.execute("INSERT INTO printers(model,color,type,price) VALUES(" + Canon2.getModel() + ",'"
                    + Canon2.getColor() + "','" + Canon2.getType() + "'," + Canon2.getPrice() + ");");
            stmt.execute("INSERT INTO Product VALUES('" + Canon2.getMaker() + "',"
                    + Canon2.getModel() + ",'" + Canon2.getType() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPrinterTable(Connection con, Statement  stmt){
        // TODO: 16.12.2019
        String printersParam = "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, model INTEGER, color TEXT, type TEXT, price INTEGER";
        try {
            stmt.execute("CREATE table IF NOT EXISTS printers("+ printersParam + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt){
        //todo
        ArrayList<String> a = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT * FROM PC WHERE price > 15000 ;");
            while (rs.next()) {
                a.add(rs.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt) {
        // TODO: 16.12.2019
        ArrayList<Integer> a = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Laptop WHERE speed > 2500 ;");
            while (rs.next()) {
                a.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt){
        ArrayList<String> ans = new ArrayList<>();
        // TODO: 18.02.2020
        try {
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT * FROM Product GROUP BY 'maker' HAVING COUNT(*)>1 ;");
            while (rs.next()) {
                ans.add(rs.getString("maker"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    /*
     * Метод должен вернуть максимальную среди всех произодителей
     * суммарную стоимость всех изделий типов ноутбук или компьютер,
     * произведенных одним производителем
     * Необходимо объединить таблицы продуктов ноутбуков и компьютеров
     * и отгрупировать по сумме прайсов после чего выбрать максимум
     * или сделать любым другим способом
     */

    public int makerWithMaxProceeds(Statement stmt){
        int result = 0;
        //todo
        String combine = "SELECT model AS model, price FROM Laptop WHERE price>0 UNION ALL SELECT model, price FROM PC WHERE price>0";
        try {
            ResultSet rs = stmt.executeQuery("SELECT maker, MAX(SUM) AS MAX  " +
                    "FROM (" +
                            "SELECT tmp.model, SUM(tmp.price) AS SUM, P.maker AS maker FROM ("+ combine +") AS tmp , " +
                            "Product P WHERE tmp.model = P.model GROUP BY P.maker" +
                            ")");
            while (rs.next()) {
                System.out.println(rs.getString("maker")+" "+ rs.getString("MAX"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
