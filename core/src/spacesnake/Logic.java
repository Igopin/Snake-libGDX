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
            _state.dir = newDir;
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
        Point dir = _state.directions.get(_state.dir);
        Point head = _state.coordinates.getFirst();

        head.translate(dir.x, dir.y);

        Iterator it = _state.coordinates.iterator();
        Point prev = new Point();
        prev.setLocation(head);

        while (it.hasNext()) {
            Point cur = (Point) it.next();
            Point tmp = new Point();

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
