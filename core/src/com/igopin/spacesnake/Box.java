package com.igopin.spacesnake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor {

    private int _height;
    private int _width;
    private Texture _texture;

    Texture[] textures = new Texture[7];
    Color[] colors = { new Color(0x00FF0088), new Color(0xFF000088), Color.GREEN, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.GREEN };

    public Box(int colorIndex, int width, int heigth) {
        _width = width;
        _height = heigth;

        for (int i = 0; i < colors.length; i++) {
            textures[i] = createTexture(colors[i]);
        }

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

    public void setSize(final int width, final int height) {
        _width = width;
        _height = height;
        redrawTexrureArray();
    }

    private void redrawTexrureArray() {
    }

    private Texture createTexture(Color color) {
        Pixmap image = new Pixmap(_width, _height, Pixmap.Format.RGBA8888);

        image.setColor(color);
        image.fill();

        Texture texture = new Texture(image);
        return texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(_texture, this.getX(), getY(), this.getOriginX(), this.getOriginY(), _texture
                .getWidth(), _texture.getHeight(), this.getScaleX(), this.getScaleY(), this
                .getRotation(), 0, 0, _texture.getWidth(), _texture.getHeight(), false, false);
    }
}
