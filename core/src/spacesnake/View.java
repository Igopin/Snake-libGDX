package spacesnake;

import java.awt.Point;
import java.util.Deque;

public abstract class View {

    public void draw(final State state) {
        drawField(state.getField().getData());
        drawSnake(state.coordinates);
    }

    private void drawSnake(Deque<Point> coordinates) {
        for (Point point : coordinates) {
            System.out.println(point.x + " " + point.y);
            drawBox(1, point.x, point.y);
        }
    }

    private void drawBoxes(final int[][] data, final int row, final int col) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                drawBox(data[i][j], row + i, col + j);
            }
        }
    }

    private void drawField(final int[][] data) {
        drawBoxes(data, 0, 0);
    }

    protected abstract void drawBox(int color, final int row, final int col);
}
