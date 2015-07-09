package spacesnake;

import java.awt.Point;
import java.util.Iterator;

public class Logic {

    private State _state;

    public Logic(final State state) {
        _state = state;
    }

    private boolean moveTowards(Direction newDir, Direction oppositeDir) {
        boolean res = false;
        if (_state.dir != oppositeDir) {
            System.out.println("NewDir: " + newDir);
            _state.dir = newDir;
            System.out.println("NewState: " + _state.dir);
            res = true;
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
        Iterator it = _state.coordinates.iterator();
        Point head = (Point) it.next();

        Point dir = _state.directions.get(_state.dir);

        Point prev = new Point();
        prev.setLocation(head);

        head.translate(dir.x, dir.y);
        if (head.x >= _state._field.getWidth())
            head.x = 0;
        if (head.x < 0)
            head.x = _state._field.getWidth() - 1;

        if (head.y >= _state._field.getHeigth())
            head.y = 0;
        if (head.y < 0)
            head.y = _state._field.getHeigth() - 1;

        Point tmp = new Point();
        while (it.hasNext()) {
            Point cur = (Point) it.next();

            tmp.setLocation(cur);
            cur.setLocation(prev);
            prev.setLocation(tmp);
        }
        return true;
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
