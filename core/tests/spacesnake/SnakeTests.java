package spacesnake;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SnakeTests {
    private State _state;
    private Logic _logic;

    @Before
    public void setUp() {
        _state = new State();
        Field field = new Field(20, 20);
        Snake snake = Snake.START_SNAKE;
        _state.setField(field);
        _state.setSnake(snake);
        _logic = new Logic(_state);
    }

    // Test of test
    @Test
    public void move() {
        assertTrue(true);
    }

}
