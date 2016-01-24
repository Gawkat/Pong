package com.github.gawkat.pong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.gawkat.pong.Pong;

public class MenuScreen implements Screen {

	final Pong game;

	OrthographicCamera camera;
	Viewport viewport;

	public MenuScreen(final Pong game) {
		this.game = game;

		camera = new OrthographicCamera();
		viewport = new StretchViewport(Pong.VIRTUAL_WIDTH, Pong.VIRTUAL_HEIGHT);
		viewport.apply();

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	/**
	 * Updates MenuScreen components
	 * 
	 * @param delta
	 */
	private void update(float delta) {
		if (Gdx.input.justTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	/**
	 * Draws MenuScreen components
	 */
	private void draw() {
		game.font.draw(game.batch, "PONG", (Pong.VIRTUAL_WIDTH * 3) / 8, Pong.VIRTUAL_HEIGHT - Pong.VIRTUAL_HEIGHT / 8);

		game.flash(Gdx.graphics.getDeltaTime(), "CLICK TO PLAY", (Pong.VIRTUAL_WIDTH * 1) / 8,
				Pong.VIRTUAL_HEIGHT - (Pong.VIRTUAL_HEIGHT * 5) / 8);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update(delta);

		game.batch.begin();
		draw();
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	@Override
	public void show() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
