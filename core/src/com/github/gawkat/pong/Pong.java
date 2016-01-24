package com.github.gawkat.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.github.gawkat.pong.screens.MenuScreen;

public class Pong extends Game {

	public SpriteBatch batch;

	public BitmapFont font;

	// States whether text should show
	private static boolean isShowing = true;

	// Current time
	private static float currentTime;

	// Time to show flashing text
	private static final float showTime = 1.0f;

	// Time to hide flashing text
	private static final float hideTime = 0.8f;

	public static final int VIRTUAL_WIDTH = 640;
	public static final int VIRTUAL_HEIGHT = 480;

	@Override
	public void create() {
		batch = new SpriteBatch();

		Sounds.load();
		loadFont();

		// Set current time to zero
		currentTime = 0.0f;

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	/**
	 * Plays sound
	 * 
	 * @param sound
	 */
	public static void playSound(Sound sound, float volume) {
		sound.play(volume);
	}

	/**
	 * Flashes text on screen
	 * 
	 * @param text
	 * @param x
	 * @param y
	 */
	public void flash(float delta, String text, float x, float y) {
		if (isShowing) {
			if (currentTime >= showTime) {
				currentTime = 0.0f;
				isShowing = false;
			}
			font.draw(batch, text, x, y);
		} else {
			if (currentTime >= hideTime) {
				currentTime = 0.0f;
				isShowing = true;
			}
		}

		currentTime = currentTime + delta;

	}

	/**
	 * Loads game font into memory
	 */
	public void loadFont() {
		font = new BitmapFont();

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 72;
		font = generator.generateFont(parameter);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		generator.dispose();
	}

}
