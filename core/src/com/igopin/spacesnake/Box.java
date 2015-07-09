package com.igopin.spacesnake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor {

    static private int _height;
    static private int _width;
    static Sprite[] textures = new Sprite[5];
    private Sprite _texture;

    static void init(final int width, final int height) {
        _width = width;
        _height = height;
        textures[0] = new Sprite(createTexture(new Color(0xFF000033)));
        (textures[1] = new Sprite(new Texture("straight.png"))).setSize(_width, _height);
        (textures[2] = new Sprite(new Texture("head.png"))).setSize(_width, _height);
        textures[2].flip(false, true);
        (textures[3] = new Sprite(new Texture("tail.png"))).setSize(_width, _height);
        textures[3].flip(false, true);
        (textures[4] = new Sprite(new Texture("asteriod.png"))).setSize(_width, _height);
    }

    public Box(int colorIndex) {
        _texture = textures[colorIndex];
    }

    public void setColor(final int colorIndex) {
        _texture = textures[colorIndex];
    }

    public int getBoxHeight() {
        return _height;
    }

    public int getBoxWidth() {
        return _width;
    }

    public void setSize(int w, int h) {
        _width = w;
        _height = h;
    }

    private static Texture createTexture(Color color) {
        Pixmap image = new Pixmap(_width, _height, Pixmap.Format.RGBA8888);

        image.setColor(color);
        image.fill();

        Texture texture = new Texture(image);
        return texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        _texture.setPosition(this.getX(), this.getY());
        _texture.draw(batch);
    }
}
