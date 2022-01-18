package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Go /* GAME OVER */ extends Scene{
    mButton m1;

    Go(){
        m1 = new mButton("back", (1024 / 2) - (mButton.bw / 2), 325, mButton.bw, mButton.bh, Color.DARK_GRAY);
        font.setColor(Color.RED);
        font.getData().setScale(5f);
        layout.setText(font, "GAME OVER");
    }

    void handle_clicks(int x, int y){
        if(m1.hitbox().contains(x, y)) {
            Main.gameover = false;
            Main.started = false;
            Main.about = false;
            Main.ztd = new ZTD();
        }
    }

    void draw(SpriteBatch batch){
        ScreenUtils.clear(new Color(0x33000000));
        font.draw(batch, layout, (float)1024 / 2 - layout.width / 2, 575);
        m1.draw(batch);
    }
}
