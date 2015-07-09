package com.igopin.spacesnake;

import spacesnake.Controller;
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

    public SnakeStage() {
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

            @Override
            protected void drawBox(int color, int row, int col) {
                int height = Gdx.graphics.getHeight();
                int width = Gdx.graphics.getWidth();

                if (_boxes[row][col] == null) {
                    Box box = new Box(color, width / Model.COLUMNS, height / Model.ROWS);
                    _boxes[row][col] = box;
                    box.setBounds(col * box.getBoxWidth(), row * box.getBoxHeight(), box
                            .getBoxWidth(), box.getBoxHeight());

                    SnakeStage.this.addActor(box);
                }
                _boxes[row][col].setColor(color + 1);
            }
        };

        controller.setView(view);
        controller.setModel(model);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                controller.move();
            }
        }, .2f, .2f);

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
    }
}
