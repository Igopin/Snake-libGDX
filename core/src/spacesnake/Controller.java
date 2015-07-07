package spacesnake;

public class Controller implements ModelListener {

    public View _view;
    public Model _model;

    public void setView(final View view) {
        _view = view;
    }

    @Override
    public void onChange(State state) {
        _view.draw(state);
    }

    public void moveLeft() {
        _model.moveLeft();
    }

    public void moveRight() {
        _model.moveRight();
    }

    public void moveDown() {
        _model.moveDown();
    }

    public void setModel(final Model model) {
        _model = model;
    }

}
