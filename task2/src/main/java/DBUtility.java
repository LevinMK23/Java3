

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    public void createPrinterTable(Statement  stmt) throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS  Printer (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, model INTEGER, color TEXT, type TEXT, price INTEGER);");
        stmt.execute("INSERT INTO Printer(model, color, type , price) VALUES (1012, 'col', 'laser', 20000)");
        stmt.execute("INSERT INTO Product(maker, model, type) VALUES ('HP', 1012, 'Printer')");
        stmt.execute("INSERT INTO Printer(model, color, type , price) VALUES (1010, 'bw', 'jet', 5000)");
        stmt.execute("INSERT INTO Product(maker, model, type) VALUES ('Canon', 1010, 'Printer')");
        stmt.execute("INSERT INTO Printer(model, color, type , price) VALUES (1010, 'bw', 'jet', 5000)");
        stmt.execute("INSERT INTO Product(maker, model, type) VALUES ('Canon', 1010, 'Printer')");
        //TODO
    }

    /*
    * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt) throws SQLException {
        ArrayList<String> expensivePC = new ArrayList<>();
        ResultSet expensiveModelPC = stmt.executeQuery("SELECT DISTINCT model FROM PC WHERE price > 15000");
//        List<Integer> modelsPC = new ArrayList<>();
        while (expensiveModelPC.next()){
            expensivePC.add(expensiveModelPC.getString("model"));
        }
//        for (int i = 0; i < modelsPC.size(); i++) {
//            ResultSet rs = stmt.executeQuery("SELECT DISTINCT * from Product WHERE model = " + modelsPC.get(i) + ";");
//            while (rs.next()){
//                expensivePC.add(rs.getString(1) + " " + rs.getInt(2)+ " " + rs.getString(3));
//            }
//        }

        //TODO
        return expensivePC;
    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt) throws SQLException {
        ArrayList<Integer> quickLaptop = new ArrayList<>();
        ResultSet quickModelLaptop = stmt.executeQuery("SELECT DISTINCT id FROM Laptop WHERE speed >= 2500");
        while (quickModelLaptop.next()){
            quickLaptop.add(quickModelLaptop.getInt("id"));
        }

        //TODO
        return quickLaptop;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt) throws SQLException {
        ArrayList <String> makers = new ArrayList<>();
        ArrayList <String> makersPC = new ArrayList<>();
        ArrayList <String> makersLaptop = new ArrayList<>();
        ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT maker FROM Product WHERE type = 'PC';");
        while (resultSet.next()){
            makersPC.add(resultSet.getString("maker"));
        }
        resultSet = stmt.executeQuery("SELECT DISTINCT maker FROM Product WHERE type = 'Laptop';");
        while (resultSet.next()){
            makersLaptop.add(resultSet.getString("maker"));
        }
        for (int i = 0; i < makersPC.size(); i++) {
            if (makersLaptop.contains(makersPC.get(i))) {
                makers.add(makersPC.get(i));
            }
        }
        //TODO
        return makers;
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
        int maxProceeds = Integer.MIN_VALUE;
        List <String> makers = new ArrayList<>();
        ResultSet makersSet = stmt.executeQuery("SELECT DISTINCT maker FROM Product;");
        while (makersSet.next()){
            makers.add(makersSet.getString("maker"));
        }
        for (int i = 0; i < makers.size(); i++) {
            int currentSum = 0;
            String maker = makers.get(i);
            ResultSet makerModelsSet = stmt.executeQuery("SELECT * FROM Product WHERE maker = '" + maker + "';");
            while (makerModelsSet.next()){
                String type = makerModelsSet.getString("type");
                int model = makerModelsSet.getInt("model");
                System.out.println(maker + " " + model + " " + type);
//                int modelPrice = 0;
//                ResultSet modelPricesSet = stmt.executeQuery("SELECT DISTINCT * FROM " + type + " WHERE model = " + model + ";");
//                while (modelPricesSet.next()){
//                    modelPrice = modelPricesSet.getInt("price");
//                }
//                currentSum = currentSum + modelPrice;
            }
//            if (currentSum > maxProceeds) maxProceeds = currentSum;
            System.out.println();
        }
        //TODO
        return maxProceeds;
    }


}
