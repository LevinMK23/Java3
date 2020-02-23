import java.io.*;
import java.sql.Time;
import java.util.*;
import java.time.LocalDate;

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

        ArrayList<Integer>  ArrInt = new ArrayList<>();
        ArrayList<Integer>  ArrPos = new ArrayList<>();
        ArrayList<Integer>  ArrMod = new ArrayList<>();

        try {
            Scanner sc = new Scanner(in);
            int FirstNum= Integer.parseInt(sc.nextLine()); //Видимо для получение общего кол-ва элементов в массиве

            while (sc.hasNext()) {
                int i=sc.nextInt();
                ArrInt.add(i);
                //Если значение чётное, записываем в массив с чётными
                if (i%2==0) {
                    ArrMod.add(i);
                    ArrPos.add(ArrInt.size()-1);
                }
               
            }

            ArrMod.sort(Comparator.naturalOrder()); //Сортировка чётных значений

            //Возвращаем на место с отсортированными значениями
            for (int i = 0; i < ArrMod.size(); i++) ArrInt.set(ArrPos.get(i), ArrMod.get(i));

            //Записываем в файл через пробел
            FileWriter fW = new FileWriter(out);
            for (Integer I : ArrInt)  fW.write(I.toString() + " ");
            fW.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
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

    //Генерируем пароль
    public String passGeneration(){
        int asciiForLoA = 97;
        int asciiCount = 25;
        int asciiForHiA = 65;
        char[] chars = {'*','!','%'};

        StringBuilder newPass = new StringBuilder();
       //Находим длину
       int len = (int) (Math.random() * 5)+7;
       int rndC = (int)( Math.random()*3);
        for (int i = 0; i < len-1; i++) {
            //выбираем набор 1 из 4х
            int indChars=Math.floorMod(rndC,4);  //(int)( Math.random()*3);
            rndC++;

            if (indChars==0) {
                newPass.append(  chars[(int)(Math.random()*2)] );
            }
            else if (indChars==1) {
                newPass.append((char)((int)(Math.random()*asciiCount) +asciiForLoA));
            }
            else if (indChars==2) {
                newPass.append((char)((int)(Math.random()*asciiCount) +asciiForHiA));
            }
            else if (indChars==3) {

                newPass.append( String.valueOf((int)(Math.random()*9)) );
            }


        }

        return newPass.toString();
    }
    //Добаволяем пароль если его нет
    public String GetIsNotExistPass(ArrayList<String> Arr){

        String newPass=passGeneration();
        while (Arr.indexOf(newPass)>-1) {
            newPass=passGeneration();
        }
         Arr.add(newPass);
        return newPass;
    }

    public void passwordGen(File in, File out) {
        ArrayList<String> passArr= new ArrayList<>();
        try {
            Scanner sc = new Scanner(in);//Читаем
            FileWriter fW = new FileWriter(out); //Записываем
            while (sc.hasNext())
            {
                String pass = GetIsNotExistPass(passArr); //Генерируем новый уникальный пароль
                fW.write(sc.nextLine().trim() + " " + pass+"\n"); //Сохраняем в файл

            }
            fW.flush();//Скидываем буфер
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *  Метод должен дописать в переданный файл все
     * записи из списка по одной записи в строке
     * */
    public void appender(File file, List<String> records) {

        try {
            //Считываем то, что есть в файле;
            ArrayList<String> arr= new ArrayList<>();
            Scanner sc = new Scanner(file);//Читаем
            while (sc.hasNext())
            {
                arr.add(sc.nextLine());
            }

            boolean Test=true;
            int ln=arr.size()-1;
            for (int i = records.size() -1;  i >-1 ; i--) {

                if (!arr.get(ln).equals(records.get(i))) {
                    Test=false;
                    break;
                }
                ln--;
            }
            sc.close();
            if (Test) {return; }

            FileWriter fW = new FileWriter(file);
            for (int i = 0; i < records.size(); i++) {
                 arr.add(records.get(i));

            }
            for (int i = 0; i < arr.size(); i++) {
                fW.write(arr.get(i)+"\n");

            }
            fW.flush();



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
        ArrayList<String> arr= new ArrayList<>();
        try {
            RandomAccessFile rAF = new RandomAccessFile(pathToFile, "r");
            rAF.seek(rAF.length()-80*n);
            String ln;
            while ( (ln=rAF.readLine())!=null )
            {
                arr.add(ln);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return arr;
    }

    public static void main(String[] args) {


    }
}
