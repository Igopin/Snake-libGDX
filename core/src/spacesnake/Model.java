package spacesnake;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public static final int ROWS = 20;
    public static final int COLUMNS = 20;

    Logic _logic;
    List<ModelListener> _listeners = new ArrayList<ModelListener>();

    public Model() {
        Field field = new Field(COLUMNS, ROWS);
        State state = new State();
        state.setField(field);
        state.setSnake(Snake.START_SNAKE);
        state.dir = Direction.DOWN;
        _logic = new Logic(state);
        fireChangeEvent();
    }

    public void addListener(final ModelListener listener) {
        _listeners.add(listener);
    }

    public void removeListener(final ModelListener listener) {
        _listeners.remove(listener);
    }

    void fireChangeEvent() {
        for (ModelListener modelListener : _listeners) {
            modelListener.onChange(_logic.getState());
        }
    }

    public void moveLeft() {
        if (_logic.moveLeft()) {
        }
    }

    public void moveRight() {
        if (_logic.moveRight()) {
        }
    }

    public void moveDown() {
        if (_logic.moveDown()) {
        }
    }

    public void moveUp() {
        if (_logic.moveUp()) {
        }
    }

    public void move() {
        _logic.move();
        fireChangeEvent();
    }
}
