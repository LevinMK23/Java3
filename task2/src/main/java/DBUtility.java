

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


    void AddPrinters(Statement stmt){

    }


    public void createPrinterTable(Connection con, Statement  stmt){
        try {
            //stmt.execute("CREATE TABLE IF NOT EXISTS \"Printers\" ( \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, \"model\"\tINTEGER, \"color\" TEXT, \"type\" TEXT, \"price\" INTEGER)");
            stmt.execute("CREATE TABLE IF NOT EXISTS " +
                    "Printer (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" +
                    " UNIQUE, model  INTEGER, color TEXT, type TEXT, price INTEGER)");
            AddPrinters(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt){
        ArrayList<String> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT DISTINCT model from PC where price > 15000");
            while (rs.next()) {
                result.add(rs.getString("model"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt){
        ArrayList<Integer> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT DISTINCT id from Laptop where speed > 2500");
            while (rs.next()) {
                result.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt){
        ArrayList<String> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT DISTINCT Makers.Name from Makers " +
                    "where Makers.id in (SELECT Laptop.maker FROM Laptop) AND Makers.id " +
                    "in (SELECT PC.maker FROM PC)");
            while (rs.next()) {
                result.add(rs.getString("model"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("select  Makers.Name, sum (Pc.price  + Laptop.price) as TotalPrice FROM Makers \n" +
                    "left join Laptop on Makers.id= Laptop.maker\n" +
                    "left JOIN PC on Makers.id = PC.maker\n" +
                    "GROUP by Makers.Name\n" +
                    "ORDER by TotalPrice DESC \n" +
                    "LIMIT 1");
            if (rs.next()) {
                result = (rs.getInt("TotalPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
