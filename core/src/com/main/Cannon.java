package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Cannon {
    Sprite sprite;
    int x, y, w, h;
    String type;
    int counter = 0, delay;
    int timer = 0, time;
    boolean active = true, disabled = false;

    //Animation
    int rows = 1, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.2f; //1 second = 1.0f

    Cannon(String type, int x, int y){
        this.type = type;
        sprite = new Sprite(Tables.cannon_resources.get(type) == null ? Resources.cannon : Tables.cannon_resources.get(type));
        cols = Tables.values.get("columns_" + type) == null ? 1 : Tables.values.get("columns_" + type);
        w = (Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getWidth()) / cols;
        h = (Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getHeight()) / rows;
        this.x = grid_lock(x - w / 2);
        this.y = grid_lock(y - h / 2);
        delay = Tables.values.get("delay_" + type) == null ? 30 : Tables.values.get("delay_" + type);
        time = Tables.values.get("time_" + type) == null ? 300 : Tables.values.get("time_" + type);
        sprite.setPosition(this.x, this.y);
        init_animations();
    }

    void draw(SpriteBatch batch){
        sprite.draw(batch);
        if(disabled) { batch.draw(Resources.damaged, x, y); return;}
        batch.draw(Resources.green, x, y + h, ((float)time * ((float)w / (float)time)), 5);
        batch.draw(Resources.red, x, y + h, ((float)timer * ((float)w / (float)time)), 5);
    }

    void update(){
        disabled = timer++ >= time;
        if(disabled) return;
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        sprite = new Sprite(frame);
        sprite.setPosition(this.x, this.y);
        sprite.setRotation(get_angle());
        fire();
    }

    void fire(){
        if(ZTD.zombies.isEmpty()) return;
        if(counter++ >= delay){
            ZTD.bullets.add(new Bullet("bbb", x + w / 2, y + h / 2));
            //Main.effects.add(new Effect("muzzleflash", x + w + 25, y + h / 2));
            counter = 0;
        }
    }

    float get_angle(){
        Zombie closest = null;
        for(Zombie z : ZTD.zombies){
            if(closest == null) { closest = z; continue; }
            float cd = (float)Math.sqrt((x - closest.x) * (x - closest.x) + (y - closest.y) * (y - closest.y));
            float zd = (float)Math.sqrt((x - z.x) * (x - z.x) + (y - z.y) * (y - z.y));
            if(zd < cd) closest = z;
        }
        return (float)Math.toDegrees((Math.atan((float)(y - closest.y) / (x - closest.x)) + (x >= closest.x ? Math.PI : 0)));
    }

    int grid_lock(int n){
        return ((n + 25) / 50) * 50;
    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h); }

    void init_animations(){
        // split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split(
                Tables.cannon_resources.get(type) == null ? Resources.cannon : Tables.cannon_resources.get(type),
                w,
                h
        );

        // init number of frames to maximum number possible (all rows * all cols)
        frames = new TextureRegion[rows * cols];

        // loop through the sheet and fill frames array with cells (in order)
        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];

        //initialize animation
        anim = new Animation(frame_time, frames);
    }
}
