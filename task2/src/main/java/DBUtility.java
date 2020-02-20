

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


    void AddPrinters(Statement stmt) throws SQLException {
        ArrayList<Printer> printers = new ArrayList<>();
        Printer printer1 = new Printer(1012,"HP", "col", "laser", 20000);
        Printer printer2 = new Printer(1010,"Canon", "bw", "jet", 5000);
        Printer printer3 = new Printer(1010,"Canon", "bw", "jet", 5000);
        printers.add(printer1);
        printers.add(printer2);
        printers.add(printer3);

        for (Printer printer : printers) {
            stmt.addBatch("INSERT INTO Printer (model, maker, color, type, price) VALUES (" + printer.getModel()
                + ", '" + printer.getMaker() + "', '" + printer.getColor() + "', '" + printer.getType()
                + "', " + printer.getPrice() + ");");
        }
        stmt.executeBatch();
    }


    public void createPrinterTable(Connection con, Statement  stmt) throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS " +
            "Printer (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE " +
            ", model  INTEGER, maker TEXT, color TEXT, type TEXT, price INTEGER)");
        AddPrinters(stmt);
    }

    /*
    * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt) throws SQLException {
        ArrayList<String> models = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select distinct p.model from pc p where p.price > 15000");
        while (rs.next()) {
            models.add(rs.getString("model"));
        }
        return models;
    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt) throws SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT l.id from Laptop l where l.speed > 2500");
        while (rs.next()) {
            ids.add(rs.getInt("id"));
        }
        return ids;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt) throws SQLException {
        ArrayList<String> ans = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT pr.maker from Product pr, (" +
            "SELECT p.maker from Product p where p.type = 'PC' and p.type <> 'Laptop') p " +
            "where pr.type = 'Laptop' and pr.maker = p.maker");
        while (rs.next()) {
            ans.add(rs.getString("maker"));
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

    public int makerWithMaxProceeds(Statement stmt) throws SQLException {
        int result = 0;
        ResultSet rs = stmt.executeQuery("select max(m.sum) summ from (select sum(m.price) sum, p.maker from " +
            "(SELECT pc.price, pc.model from PC pc\n" +
            "UNION ALL\n" +
            "SELECT l.price, l.model from Laptop l) m,\n" +
            "(SELECT p.* FROM Product p GROUP by p.model) p\n" +
            "where m.model = p.model\n" +
            "group by p.maker) m");
        while (rs.next()) {
            result = rs.getInt("summ");
        }
        return result;

    }
}
