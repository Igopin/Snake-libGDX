package com.igopin.spacesnake;

import com.badlogic.gdx.Game;

public class SpaceSnake extends Game {

    FieldScreen _fieldScreen;
    StartScreen _startScreen;
    public Background _background;

    @Override
    public void create() {
        _background = new Background();
        _background.setPosition(0, 0);

        _fieldScreen = new FieldScreen(this);
        _startScreen = new StartScreen(this);
        setScreen(_startScreen);

    }

    @Override
    public void resize(int width, int height) {
        // all actors resize with window
    }
}
