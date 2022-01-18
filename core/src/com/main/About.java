package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class About extends Scene {
    mButton m1;

    About(){
        m1 = new mButton("back", (1024 / 2) - (mButton.bw / 2), 325, mButton.bw, mButton.bh, Color.GRAY);
        font.setColor(Color.YELLOW);
        font.getData().setScale(5f);
        layout.setText(font, "Zombie Tower Defense");
    }

    void handle_clicks(int x, int y){
        if(m1.hitbox().contains(x, y)) Main.about = false;
    }

    void draw(SpriteBatch batch){
        ScreenUtils.clear(new Color(0x03101500));
        font.draw(batch, layout, (float)1024 / 2 - layout.width / 2, 575);
        m1.draw(batch);
    }
}
