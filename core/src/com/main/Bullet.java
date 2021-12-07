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
        float zx = 0, zy = 0;
        float[] difs = new float[Main.zombies.size()];
        int index = 0;
        for(Zombie z : Main.zombies){
            int dx = x - z.x, dy = y - z.y;
            difs[index++] = (float)Math.sqrt(dx * dx + dy * dy);
        }
        Arrays.sort(difs);
        float closest = difs[0];
        for(Zombie z : Main.zombies){
            int dx = x - z.x, dy = y - z.y;
            if((float)Math.sqrt(dx * dx + dy * dy) == closest){
                zx = z.x + (float)z.w / 2;
                zy = z.y + (float)z.h / 2;
            }
        }
        return (float)(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h); }

    void hit_detect(){
        if(Main.zombies.isEmpty()) return;
        for(Zombie z : Main.zombies) if(z.hitbox().contains(hitbox())) {
            active = false;
            z.hp--;
        }
    }
}
