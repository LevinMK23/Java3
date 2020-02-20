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

  public ArrayList<String> selectExpensivePC(Statement stmt) throws SQLException {
    ArrayList<String> result = new ArrayList<>();
    try (ResultSet resultSet =
        stmt.executeQuery("select distinct model from PC where price > 15000")) {
      while (resultSet.next()) {
        result.add(resultSet.getString(1));
      }
    }
    return result;
  }

  /*
   * Метод должен вернуть список id ноутов, скорость процессора
   * которых выше чем 2500
   */

  public ArrayList<Integer> selectQuickLaptop(Statement stmt) {
    // TODO: 16.12.2019
    return null;
  }

  /*
   * Метод должен вернуть список производителей которые
   *  делают и пк и ноутбуки
   */
  public ArrayList<String> selectMaker(Statement stmt) {
    ArrayList<String> ans = new ArrayList<>();
    // TODO: 18.02.2020
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
    // todo
    return result;
  }
}
