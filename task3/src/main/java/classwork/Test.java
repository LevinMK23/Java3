package classwork;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String s = new String("asfasf".getBytes(),
                StandardCharsets.US_ASCII);
        System.out.println(s);
        FileOutputStream os = new FileOutputStream(new File("java3_gb.txt"));
        os.write(s.getBytes());
        /*RW.writeBigDataOneByte(os);
        System.out.println(System.currentTimeMillis() - start + "ms");

        start = System.currentTimeMillis();
        RW.writeBigDataWithByteBuffer(os);
        System.out.println(System.currentTimeMillis() - start + "ms");
        */
        /*RW.bufferedOutput((byte) 'a', os);
        RW.bufferedOutput((byte) 'b', os);
        RW.bufferedOutput((byte) 'c', os);
        RW.close(os);*/
        /*PrintWriter pr =
                new PrintWriter(new File("java3_gb.txt"));
        pr.println("OK");
        pr.close();*/
        byte [] buffer = new byte[1000];
        int readBytes = new FileInputStream("java3_gb.txt").read(buffer);
        System.out.println(readBytes);
    }
}
