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

	//TODO: GAME LISTS
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	ArrayList<Cannon> cannons = new ArrayList<Cannon>();
	ArrayList<Button> buttons = new ArrayList<Button>();

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
		ScreenUtils.clear(0, 0, 0, 1);
		update();
		batch.begin();
		/* bg */ batch.draw(Resources.bg, 0, 0);
		for(Zombie z : zombies) z.draw(batch);
		for(Cannon c : cannons) c.draw(batch);
		for(Button b : buttons) b.draw(batch);
		batch.end();
	}

	void update(){
		tap();
		for(Zombie z : zombies) z.update();
		for(Cannon c : cannons) c.update();
		for(Button b : buttons) b.update();
	}

	void tap(){
		if(Gdx.input.isTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();
			cannons.add(new Cannon("ccc", x, y));
		}
	}

	void setup(){
		Tables.init();
		for(int i = 0; i < 5; i++){
			buttons.add(new Button("bbb", 25 + i * 75, 525));
		}
	}

	void spawn_zombies(){
		for ( int i = 0; i < 1000; i ++){
			zombies.add(new Zombie("zzz", 526 + i * 50, r.nextInt(600), 5));
		}
	}

	//*******************END OF FILE********************
	@Override
	public void dispose () {
		batch.dispose();
	}
}
