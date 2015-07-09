package com.igopin.spacesnake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {

    private Texture _texture;
    private Sprite _sprite;

    public Background() {
        _texture = new Texture("space.jpg");
        _sprite = new Sprite(_texture);
        _sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        _sprite.draw(batch);
    }
}
