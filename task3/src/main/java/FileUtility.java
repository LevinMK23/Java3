import java.io.*;
import java.util.*;

public class FileUtility {

    /*
     * Структура файла ввода: в первой строке одно целое число - N
     * далее следует N целых чисел через пробел
     * Метод должен отсортировать элементы с четным значением,
     * а нечетные оставить на своих местах и вывести результат через пробел в файл вывода
     * Пример:
     * 5
     * 5 4 2 1 3    2 4
     * 5 2 4 1 3
     */
    public void sortEvenElements(File in, File out) {
        //TODO
        try {
            Scanner cin = new Scanner(in);
            int n = cin.nextInt();
            int[] a = new int[n];
            LinkedList<Integer> even = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                a[i] = cin.nextInt();
                if (a[i] % 2 == 0) {
                    even.add(a[i]);
                }
            }
            Collections.sort(even);
            for (int i = 0; i < n; i++) {
                if (a[i] % 2 == 0) {
                    a[i] = even.pollFirst(); //O(1)
                }
            }
            PrintWriter writer = new PrintWriter(out);
            for (int i = 0; i < n; i++) {
                writer.print(a[i] + " ");
            }
            writer.close();
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
    static Random r = new Random();

    private char genaz() {
        return (char) ('a' + r.nextInt(26));
    }

    private char genAZ() {
        return (char) ('A' + r.nextInt(26));
    }

    private char gen09() {
        return (char) ('0' + r.nextInt(10));
    }

    private char genSymbol() {
        char[] sym = {'*', '!', '%'};
        return sym[r.nextInt(3)];
    }

    private String gen() {
        StringBuilder pass = new StringBuilder();
        pass.append(genaz()).append(genAZ()).append(gen09()).append(genSymbol());
        for (int i = 0; i < 2 + r.nextInt(6); i++) {
            pass.append(genaz());
        }
        return pass.toString();
    }

    public void passwordGen(File in, File out) {
        //TODO
        try {
            Scanner cin = new Scanner(in);
            ArrayList<String> entries = new ArrayList<>();
            while (cin.hasNextLine()) {
                String login = cin.nextLine();
                entries.add(login);
            }
            PrintWriter writer = new PrintWriter(out);
            for (String entry : entries) {
                writer.println(entry + " " + gen());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     *  Метод должен дописать в переданный файл все
     * записи из списка по одной записи в строке
     * */
    public void appender(File file, List<String> records) {
        //TODO
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            for (String str : records) {
                writer.println(str);
            }
        } catch (FileNotFoundException e) {
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
        //TODO
        try {
            RandomAccessFile file = new RandomAccessFile(pathToFile, "rwd");
            file.seek(file.length() - 81 * 100);
            LinkedList<String> list = new LinkedList<>();
            //000 111 111 '\n'
            while (true){
                String line = file.readLine();
                System.out.println(line);
                if(line == null) break;
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
