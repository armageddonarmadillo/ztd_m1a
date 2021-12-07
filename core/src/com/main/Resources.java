package com.main;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    //UI Elements
    static Texture bg = new Texture(Gdx.files.internal("bg_lab.png"));
    static Texture button_cannon = new Texture(Gdx.files.internal("CannonIcon.png"));
    static Texture button_cannon_fire = new Texture(Gdx.files.internal("FireCannonIcon.png"));
    static Texture button_cannon_super = new Texture(Gdx.files.internal("SuperCannonIcon.png"));
    static Texture button_cannon_double = new Texture(Gdx.files.internal("doubleCannonIcon.png"));
    static Texture button_cannon_laser = new Texture(Gdx.files.internal("laserCannonIcon.png"));
    static Texture button_cannon_mounted = new Texture(Gdx.files.internal("mountedCannonIcon.png"));
    static Texture button_locked = new Texture(Gdx.files.internal("locked.png"));
    static Texture button_selected = new Texture(Gdx.files.internal("border.png"));
    static Texture button_close = new Texture(Gdx.files.internal("x.png"));
    static Texture button_pause = new Texture(Gdx.files.internal("pause.png"));
    static Texture button_play = new Texture(Gdx.files.internal("play.png"));
    static Texture tooltip_bg = new Texture(Gdx.files.internal("ttbg.png"));
    static Texture red_bar = new Texture(Gdx.files.internal("red_bar.png"));
    static Texture green_bar = new Texture(Gdx.files.internal("green_bar.png"));
    static Texture wall = new Texture(Gdx.files.internal("Wall.png"));
    static Texture button_wall = new Texture(Gdx.files.internal("WallIcon.png"));
    static Texture damaged = new Texture(Gdx.files.internal("damaged.png"));

    //Cannons
    static Texture cannon = new Texture(Gdx.files.internal("Cannon.png"));
    static Texture cannon_fire = new Texture(Gdx.files.internal("Firecannon.png"));
    static Texture cannon_super = new Texture(Gdx.files.internal("SuperCannon.png"));
    static Texture cannon_double = new Texture(Gdx.files.internal("doubleCannon.png"));
    static Texture cannon_laser = new Texture(Gdx.files.internal("laserCannon.png"));
    static Texture cannon_mounted = new Texture(Gdx.files.internal("mountedCannon.png"));
    static Texture cannon_missile = new Texture(Gdx.files.internal("cannon_missile.png"));

    //Bullets
    static Texture bullet = new Texture(Gdx.files.internal("Bullet.png"));
    static Texture bullet_fire = new Texture(Gdx.files.internal("firebullet.png"));
    static Texture bullet_super = new Texture(Gdx.files.internal("superbullet.png"));

    //Zombies
    static Texture test_zombie = new Texture(Gdx.files.internal("Zombie.png"));
    static Texture zombie = new Texture(Gdx.files.internal("Zombies.png"));
    static Texture zombie_dif = new Texture(Gdx.files.internal("DifZombies.png"));
    static Texture zombie_speedy = new Texture(Gdx.files.internal("speedy_zombie.png"));
    static Texture zombie_riot = new Texture(Gdx.files.internal("riotzombieBIG.png"));
    static Texture zombie_fast = new Texture(Gdx.files.internal("Fastzombies.png"));

    //Effects
    static Texture boom = new Texture(Gdx.files.internal("boom.png"));
    static Texture muzzle_flash = new Texture(Gdx.files.internal("muzzleFlash.png"));
    static Texture particles = new Texture(Gdx.files.internal("particles.png"));
    static Texture click = new Texture(Gdx.files.internal("click_effect.png"));
}
