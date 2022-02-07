package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class mButton {
    //THIS CLASS IS DESIGNED TO BUILD CUSTOM BUTTONS BASED ON A GIVEN ASSET
    int x, y, w, h, b = 2;
    Color color;
    String type;
    BitmapFont font = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();
    public static final int bw = 150;
    public static final int bh = 75;

    mButton(String type, int x, int y, int w, int h, Color color){
        this.type = type;
        this.color = color;
        font = new BitmapFont(Gdx.files.internal("./fonts/squid.fnt"));
        font.setColor(Resources.inverse_color(color));
        while((layout.width < w - 4 * (float)(w / 10) && layout.height < h - 3 * (float)(h / 10))){
            font.getData().setScale(font.getData().scaleX + 0.1f);
            layout.setText(font,
                    type.equals("start") ? "Start" :
                    type.equals("exit") ? "Exit" :
                    type.equals("about") ? "About" :
                    type.equals("back") ? "Back" :
                    "Button"
            );
        }
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.createTexture(w, h, color), x, y);
        font.draw(batch, layout, x + (float)(w / 2) - (layout.width / 2), y + (float)(h / 2) + (layout.height / 2));
        batch.draw(Resources.createTexture(1, 1, Resources.inverse_color(color)), x, y, w, b);
        batch.draw(Resources.createTexture(1, 1, Resources.inverse_color(color)), x, y + h, w, b);
        batch.draw(Resources.createTexture(1, 1, Resources.inverse_color(color)), x, y, b, h);
        batch.draw(Resources.createTexture(1, 1, Resources.inverse_color(color)), x + w - b, y, b, h);
    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h); }
}
