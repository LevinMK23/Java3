import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
      int inputSize = Integer.parseInt(reader.readLine()); // Reading 1st line
      numbers = reader
          .readLine()
          .split(" "); // Reading second line

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
    HashSet<String> passwords = new HashSet<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(in));
         BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out))
    ) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        while (true) {
          String password = genPassword();
          // Проверяем, что такого пароля еще нет
          if (!passwords.contains(password)) {
            passwords.add(password);
            System.out.println(line + " " + password);
            bufferedWriter.write(line + " " + password + "\n");
            break;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  private String genPassword() {
    int passwordLength = ThreadLocalRandom
        .current()
        .nextInt(6, 12);
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < passwordLength; i++) {
      // Вызываем по одному методу
      if (i % 4 == 0) {
        result.append(getPasswordSymbol("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
      } else if (i % 4 == 1) {
        result.append(getPasswordSymbol("abcdefghijklmnopqrstuvwxyz"));
      } else if (i % 4 == 2) {
        result.append(getPasswordSymbol("0123456789"));
      } else {
        result.append(getPasswordSymbol("*!%"));
      }
    }
    return result.toString();
  }

  private char getPasswordSymbol(final String stringLine) {
    char result = 0;
    for (int j = 0; j < stringLine.length(); j++) {
      result = stringLine.charAt(ThreadLocalRandom
                                     .current()
                                     .nextInt(stringLine.length()));
    }
    return result;
  }


  /*
   *  Метод должен дописать в переданный файл все
   * записи из списка по одной записи в строке
   * */
  public void appender(File file, List<String> records) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
      writer.newLine();
      for (String record : records) {
        writer.write(record + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    List<String> tmpResult = new ArrayList<>();
    try (RandomAccessFile randomAccessFile = new RandomAccessFile(pathToFile, "r")) {
      // lines number
      int lines = 0;
      // get file length
      long length = randomAccessFile.length();
      // сюда будем считывать символы строк
      StringBuilder builder = new StringBuilder();
      // нагуглил, что так надо
      length--;
      // seek to the end of file
      randomAccessFile.seek(length);
      // считываем строки с конца
      for (long i = length; i >= 0; --i) {
        randomAccessFile.seek(i);
        // читаем символ
        char c = (char) randomAccessFile.read();
        // добавляем символ в стрингбилдер
        builder.append(c);
        // Если новая строка и длина строки больше 1 - то добавляем строку в конечный результат
        if (c == '\n' && builder.length() > 1) {
          // реверсируем строку
          builder = builder.reverse();
          // добавляем строку
          tmpResult.add(String.valueOf(builder));
          lines++;
          // обнуляем стрингбилдер
          builder = new StringBuilder();
          // если прочитали нужное число строк - прерываем цикл
          if (lines == n) break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Реверсируем список, т.к. читаем снизу вверх
    Collections.reverse(tmpResult);
    return tmpResult;
  }

}
