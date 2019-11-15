package Katya2;

import java.io.*;
import java.util.*;

public class FastScanner1 {
    private String nextToken = "";
    private StringTokenizer tokenizer = new StringTokenizer("");
    private Reader in;
    private boolean hasNext;

    public FastScanner1(File file) throws IOException {
        in = new FileReader(file.getName());
        hasNext = in.ready();
    }

    public FastScanner1(String string) throws IOException {
        in = new StringReader(string);
        hasNext = (string.length() > 0);
    }

    public FastScanner1(InputStream inStream) throws IOException {
        in = new InputStreamReader(inStream);
        hasNext = in.ready();
    }

    public void close() throws IOException {
        in.close();
    }

    private Integer toInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean hasNextLine() throws IOException {
        return (in.ready() && hasNext);
    }

    public String nextLine() throws IOException {
        if (!hasNextLine()) {
            throw new NoSuchElementException();
        }
        StringBuilder buildNewString = new StringBuilder();
        for (int next = in.read(); next != '\n'; next = in.read()) {
            if (next == -1) {
                hasNext = false;
                break;
            }
            buildNewString.append((char) next);
        }
        return buildNewString.toString();
    }

    public boolean hasNextToken() throws IOException {
        if (nextToken.length() > 0) {
            return true;
        }
        while (hasNextLine() || tokenizer.hasMoreTokens()) {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(nextLine());
            } else {
                nextToken = tokenizer.nextToken();
                return true;
            }
        }
        return false;
    }

    public String nextToken() throws IOException {
        if (!hasNextToken()) {
            throw new NoSuchElementException();
        }
        String result = nextToken;
        nextToken = "";
        return result;
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNextToken()) {
            return false;
        }
        return toInteger(nextToken) != null;
    }

    public int nextInt() throws IOException {
        if (!hasNextInt()) {
            throw new NoSuchElementException();
        }
        return toInteger(nextToken());
    }
}