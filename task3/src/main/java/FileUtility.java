import java.io.*;
import java.util.*;

public class FileUtility {



    private void ReadFile(String _FileName) {

      /*  try (BufferedWriter writer = new BufferedWriter(new FileWriter(_FileName))) {
            for (int i = 0; i < 20; i++) {
                writer.write("Java\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try (BufferedReader reader = new BufferedReader(new FileReader(_FileName))) {
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * Структура файла ввода: в первой строке одно целое число - N
    * далее следует N целых чисел через пробел
    * Метод должен отсортировать элементы с четным значением,
    * а нечетные оставить на своих местах и вывести результат через пробел в файл вывода
    * Пример:
    * 5
    * 5 4 2 1 3
    * 5 2 4 1 3
     */
    public void sortEvenElements(File in, File out) {
        //TODO

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
    public void passwordGen(File in, File out){
        //TODO

    }

    /*
    *  Метод должен дописать в переданный файл все
    * записи из списка по одной записи в строке
    * */
    public void appender(File file, List<String> records){

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {

            byte[] bb = new byte[(int)file.length()];
            raf.readFully(bb);
            raf.seek(0);
            for (String record :
                    records) {
                raf.writeBytes(record);
            }
            raf.write(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;

      /*  try (BufferedWriter writer = new BufferedWriter(new FileWriter(file) )) {

            for (String record :
                    records) {
                writer.append(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
    public List<String> getNString(String pathToFile, int n){
        //TODO
        return null;
    }

}
