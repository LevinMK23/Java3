package chess;

public abstract class ChessItem {

    protected int x, y;
    protected Color color;

    public ChessItem(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void move(ChessItem [][] desk, int x, int y);
}
