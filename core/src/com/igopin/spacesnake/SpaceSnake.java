package com.igopin.spacesnake;

import com.badlogic.gdx.Game;

public class SpaceSnake extends Game {

    FieldScreen _fieldscreen;
    StartScreen _startscreen;
    public Background _background;

    @Override
    public void create() {
        _background = new Background();
        _background.setPosition(0, 0);

        _fieldscreen = new FieldScreen(this);
        setScreen(_fieldscreen);

    }

    @Override
    public void resize(int width, int height) {
        _background.setSize(width, height);
    }
}
