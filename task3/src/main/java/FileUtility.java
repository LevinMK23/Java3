import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtility {

  /*
   * Структура файла ввода: в первой строке одно целое число - N
   * далее следует N целых чисел через пробел
   * Метод должен отсортировать элементы с четным значением,
   * а нечетные оставить на своих местах и вывести результат через пробел в файл вывода
   * Пример:
   * in:
   * 5
   * 5 4 2 1 3    // 2 4
   * out:
   * 5 2 4 1 3
   */
  public void sortEvenElements(File in, File out) {
    String[] numbers = new String[0];

    try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
      int inputSize = Integer.parseInt(reader.readLine());
      numbers = reader
          .readLine()
          .split(" ");

      // Adding even numbers to ArrayList
      ArrayList<Integer> evens = new ArrayList<>();
      for (int i = 0; i < inputSize; i++) {
        int currentItem = Integer.parseInt(numbers[i]);
        if ((currentItem % 2) == 0) {
          evens.add(currentItem);
          // Replacing even number with % symbol
          numbers[i] = "%";
        }
      }
      Collections.sort(evens); // Сортируем четные числа

      int j = 0;
      for (int i = 0; i < inputSize; i++)
        if (numbers[i].equals("%")) {
          numbers[i] = String.valueOf(
              // Восстанавливаем начальный массив отсортированными четными числами
              evens.get(j++));
        }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Записываем полученный массив в файл
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
      for (final String number : numbers) {
        writer.write(number + " ");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /*
   * Генератор паролей, пароль должен отвечать требованиям:
   * длина не менее 6 и не более 12, включает минимум по одному символу
   * из наборов: a-z, A-Z, 0-9, {*,!,%}
   * все пароли должны быть разными
   * На вход метод получает файл с логинами пользователей
   * Метод должен записать в файл вывода записи с логинами и паролями
   * для каждого пользователя
   */

  public void passwordGen(File in, File out) {
    //TODO

  }

  /*
   *  Метод должен дописать в переданный файл все
   * записи из списка по одной записи в строке
   * */
  public void appender(File file, List<String> records) {

  }

  /*
   * Метод возвращает список из N последних строк файла
   * строки можно дополнять пробелами до длины 80
   * тогда количество символов в файле будет предсказуемо
   * 10 строк это ровно 800 символов
   * Изучите класс RandomAccessFile для эффективного решения данной
   * задачи
   * Альтернативное решение: использование очереди или стека
   * */
  public List<String> getNString(String pathToFile, int n) {
    //TODO
    return null;
  }

}
