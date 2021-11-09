package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {
    static int money = 9999;
    static int wave = 0;
    static int score = 0;
    static int life = 20;
    static BitmapFont font = new BitmapFont();

    static void draw(SpriteBatch batch){
        font.getData().setScale(1.2f);
        font.setColor(Color.BLACK);
        font.draw(batch, "Money: " + money, 15 + 1, 585 - 1);
        font.setColor(Color.YELLOW);
        font.draw(batch, "Money: " + money, 15, 585);
        font.setColor(Color.BLACK);
        font.draw(batch, "Wave: " + wave, 15 + 1, 565 - 1);
        font.setColor(Color.RED);
        font.draw(batch, "Wave: " + wave, 15, 565);
        font.setColor(Color.BLACK);
        font.draw(batch, "Score: " + score, 15 + 1, 545 - 1);
        font.setColor(Color.CYAN);
        font.draw(batch, "Score: " + score, 15, 545);
        font.setColor(Color.BLACK);
        font.draw(batch, "Life: " + life, 15 + 1, 525 - 1);
        font.setColor(Color.GREEN);
        font.draw(batch, "Life: " + life, 15, 525);
        font.getData().setScale(1);
    }
}
