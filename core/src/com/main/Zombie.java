package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Zombie {
    int x, y, w, h;
    int speed;
    String type;

    Zombie(String type, int x, int y, int speed){
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = speed;
        w = 50;
        h = 50;
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.test_zombie, x, y);
    }

    void update(){
        x -= speed;
    }
}
