package classwork;

import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class RW {

    static void writeBigDataOneByte(OutputStream os){
        try {
            for (int i = 0; i < 1_000_000; i++) {
                os.write(0);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeBigDataWithByteBuffer(OutputStream os){
        try {
            for (int i = 0; i < 1000; i++) {
                byte [] array = new byte[1000];
                Arrays.fill(array, (byte) 0);
                os.write(array);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static byte [] buffer = new byte[10];
    static int pos = 0;
    static void close(OutputStream os) {
        for (int i = 0; i < pos; i++) {
            try {
                os.write(buffer[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Arrays.fill(buffer, (byte) 0); // flush
        pos = 0;                       //
    }



    static void bufferedOutput(byte b, OutputStream os) {
        if (pos < buffer.length) {
            buffer[pos++] = b;
        } else {
            try {
                os.write(buffer);
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Arrays.fill(buffer, (byte) 0);
            pos = 0;
        }
    }


    public static void main(String[] args) {
        File file = new File("java3_gb.txt");
        File logo = new File("logo2.jpg");
        try {
            //JSON, XML
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
            os.writeObject(new User(1, "Vasya"));
            os.close();
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
            User user = (User) is.readObject();
            System.out.println(user);
            Gson gson = new Gson();
            String json = gson.toJson(user);
            json = json.replaceAll("1", "34")
                    .replaceAll("Vasya", "Oleg");
            System.out.println(json);
            User user2 = gson.fromJson(json, User.class);
            System.out.println(user2);
            System.out.println(gson.toJson(user));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
