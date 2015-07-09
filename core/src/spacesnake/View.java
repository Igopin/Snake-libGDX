package spacesnake;

import java.awt.Point;
import java.util.Deque;

public abstract class View {

    public void draw(final State state) {
        drawField(state.getField().getData());
        drawSnake(state.coordinates);
    }

    private void drawSnake(Deque<Point> coordinates) {
        int index = 0;
        int color = 1;
        for (Point point : coordinates) {
            if (index == 0) {
                color = 2;
            } else if (index == coordinates.size() - 1) {
                color = 3;
            } else {
                color = 1;
            }
            drawBox(color, point.y, point.x);
            index++;
        }
    }

    private void drawBoxes(final int[][] data, final int row, final int col) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (i == 2 && j == 10)
                    data[i][j] = 4;
                drawBox(data[i][j], row + i, col + j);
            }
        }
    }

    private void drawField(final int[][] data) {
        drawBoxes(data, 0, 0);
    }

    protected abstract void drawBox(int color, final int row, final int col);
}
