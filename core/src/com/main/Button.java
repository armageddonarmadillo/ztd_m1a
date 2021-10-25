package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    int x, y, w, h;
    String type;
    boolean selected, locked;

    Button(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        w = 50;
        h = 50;
        selected = true;
        locked = true;
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.button_cannon, x, y);
        if(selected) batch.draw(Resources.button_selected, x - 7, y - 7);
        if(locked) batch.draw(Resources.button_locked, x, y);
    }

    void update(){

    }
}
