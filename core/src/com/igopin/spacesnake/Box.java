package com.igopin.spacesnake;

import spacesnake.Direction;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor {

    static private int _height;
    static private int _width;
    static final Sprite[] sprites = { null, new Sprite(new Texture("straight.png")), new Sprite(new Texture("head.png")), new Sprite(new Texture("turn.png")), new Sprite(new Texture("tail.png")), new Sprite(new Texture("asteroid.png")) };

    private Sprite _sprite;
    private Direction _originalDirection, _textureDirection;

    static void init(final int width, final int height) {
        _width = width;
        _height = height;

        sprites[0] = new Sprite(createTexture(new Color(0xFF000000)));
        for (int i = 1; i < sprites.length; i++) {
            sprites[i].setSize(_width, _height);
            sprites[i].setOriginCenter();
        }
    }

    public Box(int colorIndex, Direction originalDir) {
        _originalDirection = _textureDirection = originalDir;
        _sprite = sprites[colorIndex];
    }

    public void setColor(final int colorIndex) {
        _sprite = sprites[colorIndex];
    }

    public void setTextureDirection(Direction direction) {
        _textureDirection = direction;
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
        float rotateAngle = 0;

        _sprite.setPosition(this.getX(), this.getY());

        if (_originalDirection == Direction.DOWN) {
            switch (_textureDirection) {
            case LEFT:
                rotateAngle = 90;
                break;
            case RIGHT:
                rotateAngle = -90;
                break;
            case DOWN:
                rotateAngle = 0;
                break;
            case UP:
                rotateAngle = 180;
                break;
            }
        }

        _sprite.rotate(rotateAngle);
        _sprite.draw(batch);
        _sprite.rotate(-rotateAngle);
    }
}
