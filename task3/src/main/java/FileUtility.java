import java.io.*;
import java.lang.reflect.Array;
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
        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            reader.readLine();
            String NumsParseString = reader.readLine();
            String sLine = NumsParseString;

            NumsParseString.replaceAll(" ", "");

            List<Integer> EvenNums = new ArrayList<>();
            for (int i = 0; i < NumsParseString.length(); i++) {
                int x = Integer.parseInt(NumsParseString.substring(i, i + 1));
                if (x % 2 == 0)
                    EvenNums.add(x);
            }

            Collections.sort(EvenNums);
            StringBuilder NewLine = new StringBuilder(sLine);

            for (int i = 0; i < EvenNums.size(); i ++){
                for (int j = 0; j < NewLine.length(); j++) {
                    if (NewLine.substring(j, j+1)!= " "){
                        int x = Integer.parseInt(NewLine.substring(j, j+1));
                        if (x%2==0)
                            NewLine.replace(j, j+1,EvenNums.get(i).toString());
                    }
                }
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
        List<String> logins = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(in))
        ) {
            String str;
            while ((str = reader.readLine()) != null) {
                logins.add(str);
            }

            for (String login :
                    logins) {
                String pass = randomPass(6);
                writer.write(login + " " + pass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String randomPass(int len) {
        Random random = new Random();

        StringBuilder pass = new StringBuilder();
        String alphabet = "qwertyuiopasdfghjklzxcvbnm";
        String nums = "0123456789";
        String signs = "?><!@#$%^&*)(";
        while (pass.length() <= len) {
            alphabet.charAt(random.nextInt(alphabet.length()));
            int x = random.nextInt(alphabet.length());
            char symb = alphabet.charAt(x);
            if (x % 2 == 0) {
                pass.append(symb);
            } else {
                pass.append(String.valueOf(symb).toUpperCase());
            }
            pass.append(nums.charAt(random.nextInt(nums.length())));
            pass.append(signs.charAt(random.nextInt(signs.length())));
        }

        return pass.toString();
    }

    /*
     *  Метод должен дописать в переданный файл все
     * записи из списка по одной записи в строке
     * */
    public void appender(File file, List<String> records) {

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {

            byte[] bb = new byte[(int) file.length()];
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
        try (RandomAccessFile raf = new RandomAccessFile(pathToFile, "rw")) {

            List<String> list = new ArrayList<>();
            File file = new File(pathToFile);
            int pos = file.length() > 80 ? (int) file.length() - 80 : 0; //?((int)file.length()>80, (int) file.length()-80, 0);
            byte[] bb = new byte[80]; //new byte[(int) file.length()];
            //raf.readFully(bb, pos, 80);
            raf.seek(pos);

            raf.write(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
