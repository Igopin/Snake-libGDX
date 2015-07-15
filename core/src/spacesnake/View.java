package spacesnake;

import java.awt.Point;
import java.util.Deque;

public abstract class View {

    static public int FIELD = 0;
    static public int STRAIGHT = 1;
    static public int HEAD = 2;
    static public int TURN = 3;
    static public int TAIL = 4;
    static public int ASTEROID = 5;

    public void draw(final State state) {
        drawField(state.getField().getWidth(), state.getField().getHeigth());
        drawSnake(state.coordinates, state.dir);
        drawFood(state.food);
    }

    private void drawFood(Point food) {
        drawBox(food.y, food.x, ASTEROID, Direction.DOWN);
    }

    private void drawField(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                drawBox(i, j, FIELD, Direction.UP);
            }
        }
    }

    private int perpendicularity(Point prev, Point cur, Point next) {
        Point left = new Point(cur.x - prev.x, cur.y - prev.y);
        Point right = new Point(next.x - cur.x, next.y - cur.y);

        return left.x * right.x + left.y * right.y;
    }

    private Direction getTextureDirection(Point prev, Point cur, Point next) {

        Direction res;

        Point left = new Point(cur.x - prev.x, cur.y - prev.y);
        Point right = new Point(cur.x - next.x, cur.y - next.y);

        if (Math.abs(left.x) > 1) {
            left.x *= -1;
        }

        if (Math.abs(left.y) > 1) {
            left.y *= -1;
        }

        if (Math.abs(right.x) > 1) {
            right.x *= -1;
        }

        if (Math.abs(right.y) > 1) {
            right.y *= -1;
        }
        Point horizontal = new Point();
        Point vertical = new Point();
        if (left.y == 0) {
            horizontal.setLocation(left);
            vertical.setLocation(right);
        } else {
            horizontal.setLocation(right);
            vertical.setLocation(left);
        }

        if (horizontal.x > 0 && vertical.y > 0) {
            res = Direction.DOWN;
        } else if (horizontal.x > 0 && vertical.y < 0) {
            res = Direction.RIGHT;
        } else if (horizontal.x < 0 && vertical.y > 0) {
            res = Direction.LEFT;
        } else {
            res = Direction.UP;
        }

        return res;
    }

    private void drawSnake(Deque<Point> coordinates, Direction beginDir) {
        Point[] points = new Point[coordinates.size()];

        // OMG, i repent
        int i = 0;
        for (Point point : coordinates) {
            points[i++] = point;
        }

        Direction nextDirection, prevDir = beginDir;
        Point cur, next, prev;

        prev = points[0];
        drawBox(prev.y, prev.x, HEAD, beginDir);

        for (i = 1; i < points.length; i++) {
            cur = points[i];

            if (i + 1 < points.length) {
                next = points[i + 1];

                if (perpendicularity(prev, cur, next) == 0) {

                    Direction tmp;
                    Point vec = new Point(next.x - cur.x, next.y - cur.y);

                    if (prevDir == Direction.LEFT || prevDir == Direction.RIGHT) {
                        if (Math.abs(vec.y) > 1) {
                            vec.y *= -1;
                        }
                        tmp = (vec.y > 0) ? Direction.UP : Direction.DOWN;
                    } else {
                        if (Math.abs(vec.x) > 1) {
                            vec.x *= -1;
                        }
                        tmp = (vec.x > 0) ? Direction.LEFT : Direction.RIGHT;
                    }

                    prevDir = getTextureDirection(prev, cur, next);
                    drawBox(cur.y, cur.x, TURN, prevDir);
                    prevDir = tmp;

                } else {
                    drawBox(cur.y, cur.x, STRAIGHT, prevDir);
                }
                prev = cur;
            } else {
                drawBox(cur.y, cur.x, TAIL, prevDir);
            }
        }
    }

    protected abstract void drawBox(final int row, final int col, int color, Direction textureDir);
}
