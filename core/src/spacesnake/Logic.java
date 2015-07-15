package spacesnake;

import java.awt.Point;
import java.util.Iterator;
import java.util.Random;

public class Logic {

    private State _state;
    private boolean isChanged;
    private boolean isGameOver;

    public Logic(final State state) {
        _state = state;
        isChanged = true;
        isGameOver = false;
    }

    private boolean moveTowards(Direction newDir, Direction oppositeDir) {
        boolean res = false;
        if (_state.dir != oppositeDir && !isChanged) {
            _state.dir = newDir;
            res = true;
            isChanged = true;
        }
        return res;
    }

    public boolean moveLeft() {
        return moveTowards(Direction.LEFT, Direction.RIGHT);
    }

    public boolean moveRight() {
        return moveTowards(Direction.RIGHT, Direction.LEFT);
    }

    public boolean moveUp() {
        return moveTowards(Direction.UP, Direction.DOWN);
    }

    public boolean moveDown() {
        return moveTowards(Direction.DOWN, Direction.UP);
    }

    public boolean move() {
        isChanged = false;
        Point head = _state.coordinates.getFirst();
        Point dir = _state.directions.get(_state.dir);

        if (isPointOnSnake(new Point(head.x + dir.x, head.y + dir.y))) {
            isGameOver = true;
            return false;
        }

        Point prev = new Point(head);

        head.translate(dir.x, dir.y);
        if (head.x >= _state._field.getWidth())
            head.x = 0;
        if (head.x < 0)
            head.x = _state._field.getWidth() - 1;

        if (head.y >= _state._field.getHeigth())
            head.y = 0;
        if (head.y < 0)
            head.y = _state._field.getHeigth() - 1;

        if (head.x == _state.food.x && head.y == _state.food.y) {

            _state.coordinates.addFirst(new Point(head));
            Random rnd = new Random();

            do {
                _state.food.setLocation(rnd.nextInt(_state._field.getWidth()), rnd
                        .nextInt(_state._field.getHeigth()));
            } while (isPointOnSnake(_state.food));

            head.setLocation(prev);
            return true;
        }

        Iterator it = _state.coordinates.iterator();
        it.next();

        Point tmp = new Point();
        while (it.hasNext()) {
            Point cur = (Point) it.next();

            tmp.setLocation(cur);
            cur.setLocation(prev);
            prev.setLocation(tmp);
        }
        return true;
    }

    private boolean isPointOnSnake(Point point) {
        boolean res = false;

        for (Point snakePoint : _state.coordinates) {
            if (point.x == snakePoint.x && point.y == snakePoint.y) {
                res = true;
                break;
            }
        }
        return res;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public State getState() {
        try {
            return (State) _state.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
