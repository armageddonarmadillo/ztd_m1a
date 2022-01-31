package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	//TODO: GAME VARIABLES
	static SpriteBatch batch;
	static OrthographicCamera camera;
	static Start start;
	static About about_scene;
	static Go go;
	static ZTD ztd;
	static boolean 	started = false,
					about = false,
					gameover = false;

	//THIS RUNS *ONCE* WHEN THE APPLICATION IS LOADED / OPENED
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
		start = new Start();
		about_scene = new About();
		go = new Go();
		ztd = new ZTD();
	}

	//THIS RUNS ONCE PER FRAME
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		tap();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if(!started) if(about) about_scene.draw(batch); else start.draw(batch);
		else {
			if(gameover) go.draw(batch);
			else {
				ztd.update();
				ztd.draw(batch);
			}
		}
		batch.end();
	}

	void tap(){
		if(Gdx.input.justTouched()){
			Vector3 t = new Vector3();
			t.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(t);
			int x = (int)t.x, y = (int)t.y;

			if(about) { about_scene.handle_clicks(x, y); return; }
			if(!started) { start.handle_clicks(x, y); return; }
			if(gameover) { go.handle_clicks(x, y); return; }
			ztd.tap(x, y);
		}
	}

	//*******************END OF FILE********************
	@Override
	public void dispose () {
		batch.dispose();
	}
}
