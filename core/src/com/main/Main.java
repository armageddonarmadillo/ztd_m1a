package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	//TODO: GAME VARIABLES
	SpriteBatch batch;

	//TODO: GAME LISTS
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	//THIS RUNS *ONCE* WHEN THE APPLICATION IS LOADED / OPENED
	@Override
	public void create () {
		batch = new SpriteBatch();
		//FOR
		for ( int i = 0; i < 1000; i ++){
			zombies.add(new Zombie("zzz", 526 + i * 50, 300, 5));
		}
	}

	//THIS RUNS ONCE PER FRAME
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		update();
		batch.begin();
		for(Zombie z : zombies) z.draw(batch);
		batch.end();
	}

	public void update(){
		for(Zombie z : zombies) z.update();
	}

	//*******************END OF FILE********************
	@Override
	public void dispose () {
		batch.dispose();
	}
}
