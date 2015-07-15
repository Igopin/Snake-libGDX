package spacesnake;

public class Controller implements ModelListener {

    public View _view;
    public Model _model;

    public void setView(final View view) {
        _view = view;
    }

    public void setModel(final Model model) {
        _model = model;
    }

    public void moveLeft() {
        _model.moveLeft();
    }

    public void moveRight() {
        _model.moveRight();
    }

    public void moveUp() {
        _model.moveUp();
    }

    public void moveDown() {
        _model.moveDown();
    }

    public void move() {
        _model.move();
    }

    public boolean isGameOver() {
        return _model.isGameOver();
    }

    public void reset() {
        _model.reset();
    }

    @Override
    public void onChange(State state) {
        _view.draw(state);
    }
}
