package com.igopin.spacesnake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StartScreen implements Screen {

    final SpaceSnake _game;

    Stage _stage;
    Sprite _startMessage;

    SpriteBatch _batch;

    // BitmapFont _font;

    public StartScreen(final SpaceSnake game) {
        _game = game;
        _startMessage = new Sprite(new Texture("head.png"));
        _startMessage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        _batch = new SpriteBatch();
        // _font = new BitmapFont(new FileHandle("lcd_font.ttf"));
        // _font = new BitmapFont();
    }

    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true);

        _stage = new Stage();
        _stage.setViewport(new ScreenViewport(camera));
        _stage.addActor(_game._background);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _stage.act(delta);
        _stage.draw();
        _batch.begin();
        _startMessage.draw(_batch);
        _batch.end();
        /*
         * _batch.begin(); _font.draw(_batch, "Tap anywhere to begin!",
         * Gdx.graphics.getWidth() / 2, Gdx.graphics .getHeight() / 2);
         * _batch.end();
         */
        if (Gdx.input.isTouched()) {
            _game.setScreen(_game._fieldScreen);
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
        _stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
