package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	//TODO: GAME VARIABLES
	SpriteBatch batch;
	Random r;
	static String current_type = "";
	static boolean pause = false;

	//TODO: GAME LISTS
	static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	static ArrayList<Cannon> cannons = new ArrayList<Cannon>();
	static ArrayList<Button> buttons = new ArrayList<Button>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	static ArrayList<Wall> walls = new ArrayList<Wall>();
	static ArrayList<Effect> effects = new ArrayList<Effect>();

	//THIS RUNS *ONCE* WHEN THE APPLICATION IS LOADED / OPENED
	@Override
	public void create () {
		batch = new SpriteBatch();
		r = new Random();
		setup();
	}

	//THIS RUNS ONCE PER FRAME
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		update();
		batch.begin();
		/* bg */ batch.draw(Resources.bg, 0, 0);
		/* UI */ UI.draw(batch);
		for(Cannon c : cannons) c.draw(batch);
		for(Zombie z : zombies) z.draw(batch);
		for(Button b : buttons) b.draw(batch);
		for(Bullet b : bullets) b.draw(batch);
		for(Wall w : walls) w.draw(batch);
		for(Effect e : effects) e.draw(batch);
		batch.end();
	}

	void update(){
		tap();
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
	}

	//TODO: CHALLENGE -> Once a tooltip is open we can either tap a button again to complete an unlock,
	// 					 or tap the close button to close the tooltip

	void tap(){
		if(Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();

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

			for(Cannon c : cannons) if(c.hitbox().contains(x, y)) { if(c.disabled) c.active = false; return; }
			if(buildable(x, y)) cannons.add(new Cannon(current_type, x, y));
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
		for ( int i = 0; i < 5; i ++) zombies.add(new Zombie("speedy", 1024 + i * 50, r.nextInt(400)));
	}

	void housekeeping(){
		for(Zombie z : zombies) if(!z.active) { zombies.remove(z); break; }
		for(Bullet b : bullets) if(!b.active) { bullets.remove(b); break; }
		for(Cannon c : cannons) if(!c.active) { cannons.remove(c); break; }
		for(Wall w : walls) 	if(!w.active) { walls.remove(w);   break; }
		for(Effect e : effects) if(!e.active) { effects.remove(e); break; }
	}

	//*******************END OF FILE********************
	@Override
	public void dispose () {
		batch.dispose();
	}
}
