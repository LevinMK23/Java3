import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

  public void createPrinterTable(Connection con, Statement stmt) throws SQLException {
    String query =
        "create table if not exists Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)";
    stmt.execute(query);
    AddPrinters(stmt);
  }

  void AddPrinters(Statement stmt) throws SQLException {
    String addPrinter1 =
        "INSERT INTO Printer(id, model, color, type, price) VALUES(1, 1012, 'col', 'laser', 20000);";
    String addPrinter2 =
        "INSERT INTO Printer(id, model, color, type, price) VALUES(2, 1010, 'bw', 'jet', 5000);";
    String addPrinter3 =
        "INSERT INTO Printer(id, model, color, type, price) VALUES(3, 1010, 'bw', 'jet', 5000);";
    stmt.addBatch(addPrinter1);
    stmt.addBatch(addPrinter2);
    stmt.addBatch(addPrinter3);
    stmt.executeBatch();
  }

  /*
   * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
   */

  public ArrayList<String> selectExpensivePC(Statement stmt) {
    ArrayList<String> result = new ArrayList<>();
    try (ResultSet resultSet =
        stmt.executeQuery("select distinct model from PC where price > 15000")) {
      while (resultSet.next()) {
        result.add(resultSet.getString(1));
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

  public ArrayList<Integer> selectQuickLaptop(Statement stmt) {
    ArrayList<Integer> result = new ArrayList<>();
    try {
      try (ResultSet resultSet = stmt.executeQuery("select id from laptop l where speed > 2500")) {
        while (resultSet.next()) {
          result.add(resultSet.getInt(1));
        }
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
  public ArrayList<String> selectMaker(Statement stmt) {
    ArrayList<String> ans = new ArrayList<>();
    try {
      try (ResultSet resultSet =
          stmt.executeQuery("select distinct maker from product p group by type")) {
        while (resultSet.next()) {
          ans.add(resultSet.getString(1));
        }
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

  public int makerWithMaxProceeds(Statement stmt) {
    int result = 0;
    try {
      try (ResultSet resultSet =
          stmt.executeQuery(
              "SELECT MAX(s)\n"
                  + "FROM (SELECT p.maker,\n"
                  + "             SUM(COALESCE(k.price,COALESCE(l.price,0))) s\n"
                  + "      FROM (SELECT DISTINCT * FROM product pp) p\n"
                  + "        LEFT JOIN pc k\n"
                  + "               ON k.model = p.model\n"
                  + "              AND p.\"type\" = 'PC'\n"
                  + "        LEFT JOIN laptop l\n"
                  + "               ON l.model = p.model\n"
                  + "              AND p.\"type\" = 'Laptop'\n"
                  + "      GROUP BY p.maker)")) {
        while (resultSet.next()) {
          result = resultSet.getInt(1);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
