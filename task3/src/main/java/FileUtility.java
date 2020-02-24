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
    public void sortEvenElements(File in, File out){
        //TODO
        ArrayList<Integer> tmp = new ArrayList<>();;
        try {
            Scanner sc = new Scanner(in);
            sc.nextLine();
            while (sc.hasNext()) {
                tmp.add(sc.nextInt());
            }
            sc.close();

            for (int i = 0; i < tmp.size(); i++) {
                if ((tmp.get(i) % 2) != 0) continue;
                for (int j = i; j < tmp.size(); j++) {
                    if ((tmp.get(j) % 2) != 0) continue;
                    if (tmp.get(i) > tmp.get(j)) {
                        int temp = tmp.get(i);
                        tmp.set(i, tmp.get(j));
                        tmp.set(j, temp);
                    }
                }
            }
            FileWriter fw = new FileWriter(out);
            for (int i = 0; i < tmp.size(); i++) {
                fw.write(tmp.get(i) + " ");
            }
            fw.close();
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

    public void passwordGen(File in, File out){
        //TODO
        String Char = "abcdefghijklmnopqrstuvwxyz";
        String Char_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NUM = "0123456789";
        String Symbols = "*!%";
        ArrayList<String> n = new ArrayList<>();
        try {
            FileReader fr = new FileReader(in);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                n.add(br.readLine());
            }
            br.close();
            fr.close();
            String pass = "";
            FileWriter fw = new FileWriter(out);
            Random rnd = new Random();
            for (int i = 0; i < n.size(); i++) {
                for (int j = 0; j < ((int) (Math.random() * 1 + 2)); j++) {
                    int index = rnd.nextInt(Char.length());
                    pass += Char.charAt(index);
                    index = rnd.nextInt(Char_CAPS.length());
                    pass += Char_CAPS.charAt(index);
                    index = rnd.nextInt(NUM.length());
                    pass += NUM.charAt(index);
                    index = rnd.nextInt(Symbols.length());
                    pass += Symbols.charAt(index);
                }
                fw.write(n.get(i) + " " + pass + "\n");
                pass = "";
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *  Метод должен дописать в переданный файл все
     * записи из списка по одной записи в строке
     * */
    public void appender(File file, List<String> records) {
        try {
            FileWriter fw = new FileWriter(file, true);
            for (int i = 0; i < records.size(); i++) {
                fw.write(records.get(i) + "\n");
            }
            fw.close();
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
        //TODO
        List<String> res = new ArrayList<>();
        try {
            RandomAccessFile raf  = new RandomAccessFile(pathToFile, "r");
            for (int i = n; i != 0 ; i--) {
                raf.seek(raf.length() - (i * 80));
                res.add(raf.readLine());
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
