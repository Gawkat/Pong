package com.github.gawkat.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

	public static Sound wallBounce;
	public static Sound paddleBounce;
	public static Sound scorePoint;

	/**
	 * Loads all sounds
	 */
	public static void load() {
		wallBounce = Gdx.audio.newSound(Gdx.files.internal("sounds/wallBounce.wav"));
		paddleBounce = Gdx.audio.newSound(Gdx.files.internal("sounds/paddleBounce.wav"));
		scorePoint = Gdx.audio.newSound(Gdx.files.internal("sounds/scorePoint.wav"));
	}

	/**
	 * Disposes off all loaded sounds
	 */
	public static void dispose() {
		wallBounce.dispose();
		paddleBounce.dispose();
		scorePoint.dispose();
	}

}
