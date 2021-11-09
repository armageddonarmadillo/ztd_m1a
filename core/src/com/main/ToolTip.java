package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

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
        close.locked = false;
    }

    void draw(SpriteBatch batch){
        if(hidden) return;
        batch.draw(Resources.tooltip_bg, x, y, w, h);

        String[] words = (Tables.tooltip_info.get(type) == null ? "No tooltip found..." : Tables.tooltip_info.get(type)).split(" ");
        int rtx = 35, rty = 5; //relative position to position of tooltip
        for(String s : words) {
            if (rtx + layout.width >= w - 25) {
                rtx = 35;
                rty += layout.height + 5;
            }
            font.setColor(Color.BLACK);
            font.draw(batch, s, x + rtx + 2 + 1, y + h - rty - 2 - 1);
            font.setColor(Color.MAROON);
            font.draw(batch, s, x + rtx + 2, y + h - rty - 2);
            layout.setText(font, " " + s);
            rtx += layout.width;
        }
        font.getData().setScale(1.5f);
        font.setColor(Color.BLACK);
        font.draw(batch, "Unlock: " + 5000, x + 35 + 1, y + 50 - 1);
        font.setColor(Color.GOLD);
        font.draw(batch, "Unlock: " + 5000, x + 35, y + 50);
        font.getData().setScale(1);

        font.setColor(Color.WHITE);
        font.draw(batch, "(tap again to unlock)", x + 35 + 1, y + 15 - 1);
        font.setColor(Color.BLACK);
        font.draw(batch, "(tap again to unlock)", x + 35, y + 15);
        close.draw(batch);
    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h); }
}
