import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class ObjectInfo {

    private Object [] objects;
    private int counter;

    //Use your path
    //Объект неизвестен)))
    private static String defaultProjectsPath = "/Users/levinmk/IdeaProjects/Java3";
    private static String javaProjectsPath = "";
    static {
        javaProjectsPath = System.getenv("PR_JAVA").replace('\\', '/') + "Java3-1";
        if (javaProjectsPath == null) {
            javaProjectsPath = defaultProjectsPath;
        }
    }

    private String path = javaProjectsPath +
            "/task7/src/main/resources/";

    public ObjectInfo() {
        objects = new Object[2];
        try {
            readInstances(new ObjectInputStream(
                    new FileInputStream(path + "object.txt")));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        counter = 0;
    }

    public boolean hasNext(){
        return counter < objects.length;
    }

    public void next() {
        counter++;
    }

    public Object getInstance() {
        return objects[counter];
    }

    /*
     * Верните строку с информацией о содержимом объекта в формате:
     * имя класса:
     * тип1 имя_поля1 : значение1
     * тип2 имя_поля2 : значение2
     * ......
     * типN имя_поляN : значениеN
     * */
    public String objectInfo() throws IllegalAccessException, InstantiationException {
        String objInfo = "";

        for (Object object : objects) {
            String className = object.getClass().getName();
            objInfo += className + ":\n";

            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldType = field.getType().toString();
                String fieldName = field.getName();
                String fieldValue = objectFieldValue(object, field);

                objInfo += fieldType + " " + fieldName + " : " + fieldValue;

                objInfo += "\n";
            }
        }

        return objInfo;
    }

    String objectFieldValue(Object object, Field field) throws IllegalAccessException, InstantiationException {
        String result = "";
        Object fieldValue;
        String[] typePath;
        String typeName;

        field.setAccessible(true);

        fieldValue = field.get(object);
        typePath = field.getType().toString().split("\\.");
        typeName = typePath[typePath.length-1];
        switch (typeName) {
            case "String":
                result += (String) fieldValue;
                break;
            case "int":
                result += String.format("%d", fieldValue);
                break;
            case "ImageIcon":
                ImageIcon image = (ImageIcon) fieldValue;
                result += image.getDescription();
                break;
            case "Date":
                Date date = (Date) fieldValue;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                result += dateFormat.format(date);
                break;
            case "List":
                List<Object> objectList = new ArrayList<Object>((List)fieldValue);
                String tmp = "";
                for (Object item : objectList) {
                    if (item.getClass().getName().equals("User")) {
                        User user = (User) item;
                        tmp += user.getName() + " " + user.getSurname() + "; ";
                    } else {
                        tmp += item.toString();
                    }
                }

                result += tmp;
                break;
            default:
                break;
        }

        return result;
    }

    /*
    * Необходимо вернуть строку в формате JSON
    * посмотрите на импорты)))
    * */
    public String JSONInfo(){
        Gson gson = new Gson();

        return gson.toJson(objects);
    }

    /*
     *  Необходимо аернуть строку в формате  XML
     * поля классов отмаркированны так, чтобы работали методы
     * javax.xml.bind.*
     * */
    public String XMLInfo() {
        // TODO: 18/11/2019
        return null;
    }

    private void readInstances(ObjectInputStream is) throws IOException, ClassNotFoundException {
        objects[0] = is.readObject();
        objects[1] = is.readObject();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ObjectInfo info = new ObjectInfo();
        while (info.hasNext()){
            System.out.println(info.objectInfo());
            System.out.println(info.JSONInfo());
            System.out.println(info.XMLInfo());
            info.next();
        }
    }
}
