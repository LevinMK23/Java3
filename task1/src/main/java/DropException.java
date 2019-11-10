package java;

public class DropException extends Exception {

    @Override
    public String getMessage() {
        return "невозможно добавить фрукты в коробку";
    }

}
