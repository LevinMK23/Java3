package classwork;

import jdk.management.resource.internal.inst.FileInputStreamRMHooks;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystem {

    static LinkedList<String> images;
    static String path = "C:\\Users" +
            "\\Mikhail\\IdeaProjects\\Java3" +
            "\\task3\\src\\main\\java\\classwork\\";
    static String sec = "C:\\Users\\Mikhail\\IdeaProjects\\Java3\\task3\\src\\main\\java\\secretPack\\";
    static String recPath = "C:\\Users\\";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        File file = new File(path + "file.txt");
//        File nfile = new File(sec + "nfile.txt");
//        nfile.createNewFile();
//        System.out.println(nfile.isFile());
//        System.out.println(nfile.isDirectory());
//        System.out.println(nfile.exists());
//        images = new LinkedList<>();
//        File file = new File(recPath);
//        System.out.println(file.isDirectory());
//        deepShowFileData(file, "", 4);
//        System.out.println(images);
//        createPaths(new File("C:\\Users\\Mikhail\\IdeaProjects" +
//                "\\Java3\\task3\\src\\main\\java\\classwork"));
        File f = new File(path + "file.txt");
//        writeData(f, true);
        writeUser(f, new User(12, "Mike"));
        System.out.println(readUser(f));
        BufferedReader br = new BufferedReader(new FileReader(new File(path + "nfile.txt")));
        br.lines().filter(x->!x.isEmpty())
                .map(String::trim)
                .flatMap(x-> Stream.of(x.split(" ")))
                .forEach(System.out::println);
    }

    static void writeUser(File file, User user) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
        os.writeObject(user);
        os.close();
    }

    static User readUser(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
        User u = (User) is.readObject();
        return u;
    }

    static void writeData(File file, boolean append) throws IOException {
        long start = System.currentTimeMillis();
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(file, append))) {
            for (int i = 0; i < 10; i++) {
                writer.println(i);
                writer.flush();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start + "ms");
    }

    static void printFileData(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        while (in.hasNextLine()){
            System.out.println(in.nextLine());
        }
    }

    static void createPaths(File file) throws IOException {
        if(file.isDirectory()){
            for (int i = 1; i < 10; i++) {
                File tmp = new File(file.getPath() + "\\folder" + i);
                tmp.mkdir();
            }
        }
    }

    static void deepShowFileData(File file, String depth, int d){
        if(file == null || d == 0) return;
        if (file.isFile()){
            //System.out.println(depth + file.getName());
            String name = file.getName();
            if(name.matches("\\S+.png")){
                images.add(name);
            }
        } else if(file.isDirectory()){
            File [] files = file.listFiles();
            if(files == null) return;
            for(File f : files){
                deepShowFileData(f, depth + "-", d - 1);
            }
        }
    }
}
