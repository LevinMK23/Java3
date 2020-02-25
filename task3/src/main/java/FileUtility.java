import java.io.*;
import java.util.*;

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
        try {
            Scanner cin = new Scanner(in);
            LinkedList<Integer> evens = new LinkedList<>();
            int n = cin.nextInt();
            int [] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = cin.nextInt();
                if (a[i] % 2 == 0) {
                    evens.add(a[i]);
                }
            }
            evens.sort(Comparator.comparingInt(o->o));
            for (int i = 0; i < n; i++) {
                if (a[i] % 2 == 0) {
                    a[i] = evens.pollFirst(); //
                }
            }
            PrintWriter pr = new PrintWriter(out);
            for (int i : a) {
                pr.print(i + " ");
            }
            pr.close();
        } catch (FileNotFoundException e) {
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
        LinkedList<String> queue = new LinkedList<>();
        File file = new File(pathToFile);
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                if (queue.size() < n) {
                    queue.add(in.nextLine());
                } else {
                    queue.removeFirst();
                    queue.add(in.nextLine());
                }
            }
            return queue;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
