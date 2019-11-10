package test;

import java.io.*;
import java.util.InputMismatchException;

public class FastScanner {
    private BufferedReader br;
    private StringBuilder sb;
    private int len = 0, pos;
    private char[] buffer;
    private boolean EOF;

    public FastScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
        buffer = new char[4096];
    }

    public FastScanner(File is) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(is));
        buffer = new char[4096];
    }

    private void readBuffer() throws IOException{
        len = br.read(buffer);
        if (len == -1){
            EOF = true;
        }
        pos = 0;
    }

    public boolean hasNextLine() throws IOException {
        len = br.read(buffer);
        if (len == -1){
            EOF = true;
        }
        pos = 0;
        return !EOF;
    }

    public boolean hasNextChar(){
        if (EOF){
            return false;
        }
        if (pos >= len){
            try{
                readBuffer();
            }catch(IOException io){
                System.out.println("Something wrong");
            }
            if (EOF){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }

    public char nextChar() throws IOException{
        if (pos >= len){
            readBuffer();
        }
        return buffer[pos++];
    }

    public String nextWord() throws IOException{
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()){
            c = nextChar();
            if (Character.isDigit(c) || c == '-' || c == '+' || Character.isLetter(c)){
                sb.append(c);

            } else{
                if (!Character.isWhitespace(c)){
                    throw new InputMismatchException("");
                }
                break;


            }
        }
        if (!sb.toString().isEmpty()){
            return sb.toString();
        }else{
            throw new InputMismatchException("");
        }

    }

    public String nextLine() throws IOException{
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()){
            c = nextChar();
            if (c=='\n') {
                break;
            }
            if (c != '\r'){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public int nextInt() throws IOException {
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextChar();
            if (Character.isDigit(c) || c == '-' || c == '+') {
                sb.append(c);
            } else {
                if (!Character.isWhitespace(c)) {
                    throw new InputMismatchException("");
                }
                break;
            }
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    public void close() throws IOException{
        br.close();
    }
}