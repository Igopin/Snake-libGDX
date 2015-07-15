package spacesnake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    public static final int ROWS = 20;
    public static final int COLUMNS = 20;

    Logic _logic;
    List<ModelListener> _listeners = new ArrayList<ModelListener>();

    public Model() {
        init();
        fireChangeEvent();
    }

    private void init() {
        Field field = new Field(COLUMNS, ROWS);
        State state = new State();
        state.setField(field);
        state.setSnake(Snake.START_SNAKE);
        state.dir = Direction.DOWN;

        Random rnd = new Random();
        state.food.setLocation(rnd.nextInt(COLUMNS), rnd.nextInt(ROWS));
        _logic = new Logic(state);
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
        if (_logic.move()) {
            fireChangeEvent();
        }
    }

    public boolean isGameOver() {
        return _logic.isGameOver();
    }

    public void reset() {
        init();
    }

}
