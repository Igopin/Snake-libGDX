package spacesnake;

public class Snake {

    public final static Snake START_SNAKE;
    private final int START_LENGHT = 3;
    private int _length;

    static {
        START_SNAKE = new Snake();
    }

    public Snake() {
        _length = START_LENGHT;
    }

    public int getLength() {
        return _length;
    }
}
