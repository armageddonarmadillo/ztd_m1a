package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cannon {
    int x, y, w, h;
    String type;

    Cannon(String type, int x, int y){
        this.type = type;
        w = Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getWidth();
        h = Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getHeight();
        this.x = grid_lock(x);
        this.y = grid_lock(y);
    }

    void draw(SpriteBatch batch){
        batch.draw(Tables.cannon_resources.get(type) == null ? Resources.cannon : Tables.cannon_resources.get(type), x, y);
    }

    void update(){

    }

    int grid_lock(int n){
        return ((n + 25) / 50);
    }
}
