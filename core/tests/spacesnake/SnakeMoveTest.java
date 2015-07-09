package spacesnake;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class SnakeMoveTest {

    private Directions _input;
    private boolean _output;

    static class Directions {
        Direction _a, _b;
        
        public Directions(final Direction a, final Direction b) {
            _a = a;
            _b = b;
        }
    }

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new Directions(Direction.LEFT, Direction.LEFT),   true},
                {new Directions(Direction.LEFT, Direction.RIGHT),  false},
                {new Directions(Direction.LEFT, Direction.UP),     true},
                {new Directions(Direction.LEFT, Direction.DOWN),   true},
                
                {new Directions(Direction.RIGHT, Direction.LEFT),  false},
                {new Directions(Direction.RIGHT, Direction.RIGHT), true},
                {new Directions(Direction.RIGHT, Direction.UP),    true},
                {new Directions(Direction.RIGHT, Direction.DOWN),  true},
                
                {new Directions(Direction.UP, Direction.LEFT),     true},
                {new Directions(Direction.UP, Direction.RIGHT),    true},
                {new Directions(Direction.UP, Direction.UP),       true},
                {new Directions(Direction.UP,   Direction.DOWN),   false},
                
                {new Directions(Direction.DOWN, Direction.LEFT),   true},
                {new Directions(Direction.DOWN, Direction.RIGHT),  true},
                {new Directions(Direction.DOWN, Direction.UP),     false},
                {new Directions(Direction.DOWN, Direction.DOWN),   true},
        });
    }

    public SnakeMoveTest(final Directions input, final boolean output) {
        _input = input;
        _output = output;
    }

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

    @Test
    public void testMoveOppositeDirection() throws Exception {
        _state.dir = _input._a;
        switch (_input._b) {
        case LEFT:
            assertEquals(_logic.moveLeft(), _output);
            break;
        case RIGHT:
            assertEquals(_logic.moveRight(), _output);
            break;
        case UP:
            assertEquals(_logic.moveUp(), _output);
            break;
        case DOWN:
            assertEquals(_logic.moveDown(), _output);
            break;
        }
    }
}
