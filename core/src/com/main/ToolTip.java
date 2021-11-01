package com.main;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ToolTip {
    int x, y, w, h;
    boolean hidden = true;
    String type;
    Button close;
    BitmapFont font = new BitmapFont(); // used to draw text to the screen
    GlyphLayout layout = new GlyphLayout(); // used to calculate dimensions of text elements

    ToolTip(String type, Button p){
        this.type = type;
        this.w = 200;
        this.h = 100;
        this.x = (p.x + w / 2) - this.w / 2;
        this.y = p.y - this.h - 15;
        close = new Button("close", this.x + this.w - 25, this.y + this.h - 26);
    }

    void draw(SpriteBatch batch){
        if(hidden) return;
        batch.draw(Resources.tooltip_bg, x, y, w, h);
        font.draw(batch, Tables.tooltip_info.get(type) == null ? "No tooltip found..." : Tables.tooltip_info.get(type), x - 25, y);
        close.draw(batch);
    }
}
