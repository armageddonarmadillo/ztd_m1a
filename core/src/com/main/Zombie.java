package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Zombie {
    int x, y, w, h, hp, mhp;
    int speed;
    String type;
    boolean active = true;

    //Animation Variables
    int rows = 1, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.2f; //1 second = 1.0f

    Zombie(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = Tables.values.get("speed_" + type) == null ? 1 : Tables.values.get("speed_" + type);
        this.hp = Tables.values.get("health_" + type) == null ? 3 : Tables.values.get("health_" + type);
        mhp = hp;
        cols = Tables.values.get("columns_" + type) == null ? 4 : Tables.values.get("columns_" + type);
        this.w = (Tables.zombie_resources.get(type) == null ? Resources.zombie.getWidth() : Tables.zombie_resources.get(type).getWidth()) / cols;
        this.h = (Tables.zombie_resources.get(type) == null ? Resources.zombie.getHeight() : Tables.zombie_resources.get(type).getHeight()) / rows;
        init_animations();
    }

    void draw(SpriteBatch batch){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        batch.draw(frame, x, y);
        batch.draw(Resources.red_bar, x, y + h, mhp * (float)(w / mhp), 5);
        batch.draw(Resources.green_bar, x, y + h, hp * (float)(w / mhp), 5);
    }

    void update(){
        x -= speed;
        active = x + w > 0 && hp > 0;
    }

    void init_animations(){
        // split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split(
                Tables.zombie_resources.get(type) == null ? Resources.zombie : Tables.zombie_resources.get(type),
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

    Rectangle hitbox(){ return new Rectangle(x, y, w, h); }
}
