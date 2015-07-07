package spacesnake;

public class Field {

    int[][] _data;

    public Field(final int width, final int height) {
        _data = new int[height][width];
    }

    public int getWidth() {
        return _data[0].length;
    }

    public int getHeigth() {
        return _data.length;
    }

    public int[][] getData() {
        return _data;
    }
}
