

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


    void AddPrinters(Statement stmt) {
        //Предзаполняем массив с принтерами
        ArrayList<Printer> PrinterMass = new ArrayList<>();

        PrinterMass.add(new Printer(1012,"HP", "col", "lase", 20000));
        PrinterMass.add(new Printer(1010,"Canon", "bw", "jet", 5000));
        PrinterMass.add(new Printer(1010,"Canon", "bw", "jet", 5000));

        try {
        //Генирируем SQL Batch для вставки принтеров
        for (Printer pM : PrinterMass) {

                stmt.addBatch("INSERT INTO Printer ( model, maker, color, type, price)" +
                "VALUES ( '  " + pM.getModel() + "  ','" + pM.getMaker() + "','" + pM.getColor() + "','" + pM.getType() + "','" + pM.getPrice() + "');");


        }
        //Выполняем предзаполненый список запросов
        stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* При каждой вставке происходит выполнение тригера product_BI
        CREATE TRIGGER product_BI BEFORE INSERT ON Product FOR EACH ROW BEGIN     INSERT INTO Product ( maker, model,type)  VALUES (new.maker,
                            new.model,
                            'Printer'
                        );
END;


         */
    }


    public void createPrinterTable(Connection con, Statement  stmt) {

        try {
        stmt.addBatch("CREATE TABLE IF NOT EXISTS Printer (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE " +
                ", model INTEGER, maker TEXT, color TEXT, type TEXT, price INTEGER);");
        stmt.addBatch("CREATE TRIGGER  IF NOT EXISTS product_BI BEFORE INSERT ON Product FOR EACH ROW BEGIN     INSERT INTO Product ( maker, model,type)  VALUES (new.maker, new.model, 'Printer');");
            //Выполняем предзаполненый список запросов
        stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddPrinters(stmt);


    }

    /*
    * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt){
        ArrayList<String> Result = new ArrayList<>();
        try {
        //Запрос
        ResultSet rSet = stmt.executeQuery("SELECT DISTINCT model FROM PC WHERE PRICE > 15000");
        //Перебираем результат
        while (rSet.next()) {
            Result.add(rSet.getString("model"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result;

    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt)  {
        ArrayList<Integer> Result = new ArrayList<>();
        try {
        //Запрос
        ResultSet rSet = stmt.executeQuery("SELECT id FROM PC WHERE speed > 2500;");
        //Перебираем результат
        while (rSet.next()) {
            Result.add(rSet.getInt ("id"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt)  {
        ArrayList<String> Result = new ArrayList<>();
        try {
        //Запрос
        ResultSet rSet = stmt.executeQuery("SELECT maker, COUNT() FROM (SELECT DISTINCT maker, type FROM Product WHERE type in ('Laptop', 'PC') ) GROUP BY maker HAVING COUNT()>1;");
        //Перебираем результат
        while (rSet.next()) {
            Result.add(rSet.getString("maker"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result;
    }

    /*
     * Метод должен вернуть максимальную среди всех произодителей
     * суммарную стоимость всех изделий типов ноутбук или компьютер,
     * произведенных одним производителем
     * Необходимо объединить таблицы продуктов ноутбуков и компьютеров
     * и отгрупировать по сумме прайсов после чего выбрать максимум
     * или сделать любым другим способом
     */

    public int makerWithMaxProceeds(Statement stmt)  {
        int result = 0;
        try {
        ResultSet rSet = stmt.executeQuery("SELECT p.maker,SUM(A_mod.price) M_SUM FROM (SELECT model, price  FROM PC UNION ALL SELECT model, price FROM Laptop) A_mod, (SELECT DISTINCT * FROM product pp) p  WHERE p.model=A_mod.model  GROUP BY p.maker  ORDER BY 2 DESC;");
        //Перебираем результат
        while (rSet.next()) {
            result = rSet.getInt ("M_SUM");
            break;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }
}
