package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    int x, y, w, h;
    String type;

    Button(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        w = 50;
        h = 50;
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.button_cannon, x, y);
    }

    void update(){

    }
}
