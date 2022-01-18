package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class ZTD {
    //TODO: GAME VARIABLES
    static String current_type = "";
    static boolean pause = false;
    static Random r;

    //TODO: GAME LISTS
    static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    static ArrayList<Cannon> cannons = new ArrayList<Cannon>();
    static ArrayList<Button> buttons = new ArrayList<Button>();
    static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    static ArrayList<Wall>   walls   = new ArrayList<Wall>();
    static ArrayList<Effect> effects = new ArrayList<Effect>();

    ZTD(){
        r = new Random();
        setup();
    }

    void update(){
        spawn_zombies();
        //update loops
        if(!pause){
            for(Zombie z : zombies) z.update();
            for(Cannon c : cannons) c.update();
            for(Button b : buttons) b.update();
            for(Bullet b : bullets) b.update();
            for(Wall w : walls) w.update();
        }
        //removal of inactive elements
        housekeeping();
        Main.gameover = UI.life <= 0;
    }

    void draw(SpriteBatch batch){
        /* bg */ batch.draw(Resources.bg, 0, 0);
        /* UI */ UI.draw(batch);
        for(Cannon c : cannons) c.draw(batch);
        for(Zombie z : zombies) z.draw(batch);
        for(Button b : buttons) b.draw(batch);
        for(Bullet b : bullets) b.draw(batch);
        for(Wall w : walls) w.draw(batch);
        for(Effect e : effects) e.draw(batch);
    }

    void tap(int x, int y){
        effects.add(new Effect("boom", x, y));

        for(Button b : buttons) {
            if (b.t != null && !b.t.hidden && b.t.close.hitbox().contains(x, y)) { b.t.hidden = true; return; }
            if (b.t != null && !b.t.hidden && b.t.hitbox().contains(x, y)) return;
        }

        for(Button b : buttons) if(b.hitbox().contains(x, y)){
            if(b.type.equals("pause") || b.type.equals("play")){
                pause = !pause;
                b.type = pause ? "play" : "pause";
                return;
            }
            if(b.locked) {
                if(b.t != null && b.t.hidden) { hide_tt(); b.t.hidden = false; }
                else {
                    if(UI.money >= (Tables.values.get("unlock_" + current_type) == null ? 500 : Tables.values.get("unlock_" + current_type))) {
                        UI.money -= (Tables.values.get("unlock_" + current_type) == null ? 500 : Tables.values.get("unlock_" + current_type));
                        b.locked = false;
                    }
                    if(b.t != null) b.t.hidden = true;
                }
            }
            else {
                if(b.type.equals("wall") || b.type.equals("mounted")){
                    if(walls.size() < 3) walls.add(new Wall(walls.size() * 50, 0, b.type.equals("mounted")));
                    return;
                }
                deselect();
                b.selected = true;
                current_type = b.type;
            }
            return;
        }

        for(Cannon c : cannons) if(c.hitbox().contains(x, y)) { if(c.disabled) { UI.money += 5; c.active = false; } return; }
        if(UI.money >= (Tables.values.get("place_" + current_type) == null ? 15 : Tables.values.get("place_" + current_type)) && buildable(x, y)) {
            UI.money -= (Tables.values.get("place_" + current_type) == null ? 15 : Tables.values.get("place_" + current_type));
            cannons.add(new Cannon(current_type, x, y));
        }
    }

    void deselect(){
        for(Button b : buttons) b.selected = false;
    }

    void hide_tt(){
        for(Button b : buttons) if(b.t != null) b.t.hidden = true;
    }

    boolean buildable(int x, int y){
        return ((y > 0 && y < 200) || (y > 300 && y < 500)) && x < 1000;
    }

    void setup(){
        //clear lists
        zombies.clear();
        cannons.clear();
        buttons.clear();
        bullets.clear();
        walls.clear();
        effects.clear();
        //set game values
        UI.money = 250;
        UI.wave = 0;
        UI.score = 0;
        UI.life = 1;
        //init tables
        Tables.init();
        //make some buttons
        buttons.add(new Button("ccc", 225 + buttons.size() * 75, 525));
        buttons.get(buttons.size() - 1).locked = false;
        buttons.get(buttons.size() - 1).selected = true;
        buttons.add(new Button("fire", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("super", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("double", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("laser", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("wall", 225 + buttons.size() * 75, 525));
        buttons.get(buttons.size() - 1).locked = false;
        buttons.get(buttons.size() - 1).selected = false;
        buttons.add(new Button("mounted", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("pause", 1024 - 75, 525));
        buttons.get(buttons.size() - 1).locked = false;
        buttons.get(buttons.size() - 1).selected = false;
    }

    void spawn_zombies(){
        if(!zombies.isEmpty()) return;
        UI.wave++;
        for ( int i = 0; i < UI.wave; i ++) {
            zombies.add(new Zombie("zzz", 1024 + i * 50, r.nextInt(400)));
            if(UI.wave > 2) zombies.add(new Zombie("dif", 1024 + i * 50, r.nextInt(400)));
            if(UI.wave > 3) zombies.add(new Zombie("fast", 1024 + i * 50, r.nextInt(400)));
            if(UI.wave > 5) zombies.add(new Zombie("speedy", 1024 + i * 50, r.nextInt(400)));
            if(UI.wave > 10) zombies.add(new Zombie("riot", 1024 + i * 50, r.nextInt(400)));
            if(UI.wave > 25) zombies.add(new Zombie("riot", 1024 + i * 50, r.nextInt(400)));
            if(UI.wave > 50) zombies.add(new Zombie("riot", 1024 + i * 50, r.nextInt(400)));
            if(UI.wave > 100) zombies.add(new Zombie("riot", 1024 + i * 50, r.nextInt(400)));
        }
    }

    void housekeeping(){
        for(Zombie z : zombies) if(!z.active) { zombies.remove(z); break; }
        for(Bullet b : bullets) if(!b.active) { bullets.remove(b); break; }
        for(Cannon c : cannons) if(!c.active) { cannons.remove(c); break; }
        for(Wall w : walls) 	if(!w.active) { walls.remove(w);   break; }
        for(Effect e : effects) if(!e.active) { effects.remove(e); break; }
    }
}
