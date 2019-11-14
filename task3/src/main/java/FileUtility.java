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
    * 5 4 2 1 3
    * 5 2 4 1 3
     */
    public void sortEvenElements(File in, File out) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));

        int N = Integer.parseInt(br.readLine());

        String[] nums = br.readLine().split(" ");
        ArrayList<Integer> evenNums = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Integer tmp = Integer.parseInt(nums[i]);
            if (tmp % 2 == 0)
                evenNums.add(Integer.parseInt(nums[i]));
        }

        Collections.sort(evenNums);

        for (String n: nums) {
            Integer tmp = Integer.parseInt(n);
            if (tmp % 2 == 0)
                bw.write(evenNums.remove(0) + " ");
            else
                bw.write(tmp + " ");
        }

        br.close();
        bw.close();
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
    public void passwordGen(File in, File out) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));

        String user = "";
        while((user = br.readLine()) != null) {
            bw.write(String.format("%s %s\n", user, passGen()));
        }

        br.close();
        bw.close();
    }

    private String passGen() {
        String pass = "";

        Random random = new Random();
        int length = 6 + random.nextInt(6);

        int naz = 0;
        int nAZ = 0;
        int n09 = 0;
        int nSpChars = 0;

        while(pass.length() < length) {
            char ch = (char)random.nextInt(255);

            if (ch >= 'a' && ch <= 'z' && naz < 1) {
                pass += ch;
                naz++;
            }
            if (ch >= 'A' && ch <= 'Z' && nAZ < 1) {
                pass += ch;
                nAZ++;
            }
            if (ch >= '0' && ch <= '9' && n09 < 1) {
                pass += ch;
                n09++;
            }
            if ((ch == '*' || ch == '!' || ch == '%') && nSpChars < 1) {
                pass += ch;
                nSpChars++;
            }

            if ((naz + nAZ + n09 + nSpChars) == 4) {
                naz = nAZ = n09 = nSpChars = 0;
            }
        }

        return pass;
    }

    /*
    *  Метод должен дописать в переданный файл все
    * записи из списка по одной записи в строке
    * */
    public void appender(File file, List<String> records) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        for(String rec: records) {
            bw.write(rec + "\n");
        }
        bw.close();
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
    public List<String> getNString(String pathToFile, int n) throws IOException {
        RandomAccessFile fp = new RandomAccessFile(pathToFile, "r");
        List<String> strings = new ArrayList<>();

        long pos = fp.length() - 100L * 82L + 40;
        System.out.println(fp.length() + " " + pos);
        fp.seek(pos);
        System.out.println(fp.getFilePointer());

        String str = "";
        while((str = fp.readLine()) != null) {
            strings.add(str);
        }

        int count = 0;
        for (String s: strings) {
            System.out.println(count + " " + str);
            count++;
        }

        return strings;
    }

}
