import java.io.*;

public class ObjectInfo {

    private Object [] objects;
    private int counter;

    //Use your path
    //Объект неизвестен)))

    private String path = "/Users/levinmk/IdeaProjects/Java3" +
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
    public String objectInfo() {
        // TODO: 18/11/2019
        return null;
    }

    /*
    * Необходимо вернуть строку в формате JSON
    * посмотрите на импорты)))
    * */
    public String JSONInfo(){
        // TODO: 18/11/2019
        return null;
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

    public static void main(String[] args) throws IllegalAccessException {
        ObjectInfo info = new ObjectInfo();
        while (info.hasNext()){
            System.out.println(info.objectInfo());
            System.out.println(info.JSONInfo());
            System.out.println(info.XMLInfo());
            info.next();
        }
    }
}
