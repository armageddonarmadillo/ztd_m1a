package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Arrays;

public class Bullet {
    int x, y, w, h, speed;
    float angle;
    String type;
    boolean active = true;

    Bullet(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        w = Resources.bullet.getWidth();
        h = Resources.bullet.getHeight();
        speed = 5;
        angle = get_angle();
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.bullet, x, y);
    }

    void update(){
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
        hit_detect();
    }

    float get_angle(){
        Zombie closest = null;
        for(Zombie z : ZTD.zombies){
            if(closest == null) { closest = z; continue; }
            float cd = (float)Math.sqrt((x - closest.x) * (x - closest.x) + (y - closest.y) * (y - closest.y));
            float zd = (float)Math.sqrt((x - z.x) * (x - z.x) + (y - z.y) * (y - z.y));
            if(zd < cd) closest = z;
        }
        return (float)((Math.atan((float)(y - closest.y) / (x - closest.x)) + (x >= closest.x ? Math.PI : 0)));
    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h); }

    void hit_detect(){
        if(ZTD.zombies.isEmpty()) return;
        for(Zombie z : ZTD.zombies) if(z.hitbox().contains(hitbox())) {
            active = false;
            z.hp--;
        }
    }
}
