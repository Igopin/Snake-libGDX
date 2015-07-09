package spacesnake;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Map;

public class State implements Cloneable {

    Field _field;
    Snake _snake;

    public final Map<Direction, Point> directions;
    public Deque<Point> coordinates;
    public Direction dir;

    // funny
    public State() {
        Map<Direction, Point> tmp = new EnumMap<Direction, Point>(Direction.class);
        tmp.put(Direction.LEFT, new Point(-1, 0));
        tmp.put(Direction.RIGHT, new Point(1, 0));
        tmp.put(Direction.UP, new Point(0, -1));
        tmp.put(Direction.DOWN, new Point(0, 1));
        directions = Collections.unmodifiableMap(tmp);
    }

    public Field getField() {
        return _field;
    }

    public void setField(Field field) {
        _field = field;
    }

    public void setSnake(Snake snake) {
        _snake = snake;

        coordinates = new ArrayDeque<Point>();

        for (int i = 0; i < _snake.getLength(); i++) {
            coordinates.addLast(new Point(_field.getWidth() / 2 + i, _field.getHeigth() / 2));
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
