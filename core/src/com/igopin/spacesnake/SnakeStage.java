package com.igopin.spacesnake;

import spacesnake.Controller;
import spacesnake.Direction;
import spacesnake.Model;
import spacesnake.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SnakeStage extends Stage {

    SpaceSnake _game;

    public SnakeStage(final SpaceSnake game) {
        _game = game;

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true);
        setViewport(new ScreenViewport(camera));
    }

    public void init() {
        Model model = new Model();

        final Controller controller = new Controller();
        model.addListener(controller);

        View view = new View() {
            private Box[][] _boxes = new Box[Model.ROWS][Model.COLUMNS];

            {
                int height = Gdx.graphics.getHeight();
                int width = Gdx.graphics.getWidth();
                System.out.println(width / Model.COLUMNS + " " + height / Model.ROWS);
                Box.init(width / Model.COLUMNS, height / Model.ROWS);
            }

            @Override
            protected void drawBox(int row, int col, int color, Direction textureDir) {
                if (_boxes[row][col] == null) {
                    Box box = new Box(color, Direction.DOWN);
                    _boxes[row][col] = box;

                    box.setBounds(col * box.getBoxWidth(), row * box.getBoxHeight(), box
                            .getBoxWidth(), box.getBoxHeight());
                    SnakeStage.this.addActor(box);
                }
                _boxes[row][col].setColor(color);
                _boxes[row][col].setTextureDirection(textureDir);
            }
        };

        controller.setView(view);
        controller.setModel(model);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                controller.move();
                if (controller.isGameOver()) {
                    controller.reset();
                    _game.setScreen(_game._startScreen);
                }
            }
        }, .3f, .3f);

        Gdx.input.setInputProcessor(this);
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                case Input.Keys.LEFT:
                    controller.moveLeft();
                    break;
                case Input.Keys.RIGHT:
                    controller.moveRight();
                    break;
                case Input.Keys.UP:
                    controller.moveUp();
                    break;
                case Input.Keys.DOWN:
                    controller.moveDown();
                    break;
                default:
                    break;
                }
                return true;
            }
        });

        /*
         * addListener(new GestureListener() {
         * 
         * @Override public boolean touchDown(float x, float y, int pointer, int
         * button) { // TODO Auto-generated method stub return false; }
         * 
         * @Override public boolean tap(float x, float y, int count, int button)
         * { // TODO Auto-generated method stub return false; }
         * 
         * @Override public boolean longPress(float x, float y) { // TODO
         * Auto-generated method stub return false; }
         * 
         * @Override public boolean fling(float velocityX, float velocityY, int
         * button) { // TODO Auto-generated method stub return false; }
         * 
         * @Override public boolean pan(float x, float y, float deltaX, float
         * deltaY) { // TODO Auto-generated method stub return false; }
         * 
         * @Override public boolean panStop(float x, float y, int pointer, int
         * button) { // TODO Auto-generated method stub return false; }
         * 
         * @Override public boolean zoom(float initialDistance, float distance)
         * { // TODO Auto-generated method stub return false; }
         * 
         * @Override public boolean pinch(Vector2 initialPointer1, Vector2
         * initialPointer2, Vector2 pointer1, Vector2 pointer2) { // TODO
         * Auto-generated method stub return false; } });
         */
    }
}
