package com.github.gawkat.pong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.gawkat.pong.Pong;

public class GameScreen implements Screen {

	final Pong game;

	OrthographicCamera camera;
	Viewport viewport;

	World world;

	boolean gameOver = false;

	private static final int maxScore = 7;

	public GameScreen(final Pong game) {
		this.game = game;

		camera = new OrthographicCamera();
		viewport = new StretchViewport(Pong.VIRTUAL_WIDTH, Pong.VIRTUAL_HEIGHT, camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

		world = new World(game);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		update(delta);
		game.batch.begin();
		draw();
		game.batch.end();
	}

	/**
	 * Updates all game objects
	 * 
	 * @param delta
	 */
	private void update(float delta) {
		if (!gameOver) {
			world.update(delta);
		} else {
			// Check for input, return to menu
			if (Gdx.input.justTouched()) {
				game.setScreen(new MenuScreen(game));
				dispose();
			}
		}
		if (world.playerPoints >= maxScore || world.aiPoints >= maxScore) {
			gameOver = true;
		}
	}

	/**
	 * Draws game
	 */
	private void draw() {
		// Draws game objects
		world.draw();

		// Draws cores
		drawScore();
		if (gameOver) {
			if (world.playerPoints >= maxScore) {
				game.font.draw(game.batch, "PLAYER WINS!", Pong.VIRTUAL_WIDTH / 8, (Pong.VIRTUAL_HEIGHT * 3) / 4);
			} else {
				game.font.draw(game.batch, "COMPUTER WINS!", Pong.VIRTUAL_WIDTH / 18, (Pong.VIRTUAL_HEIGHT * 3) / 4);
			}
			game.flash(Gdx.graphics.getDeltaTime(), "CLICK TO RETURN", Pong.VIRTUAL_WIDTH / 30,
					Pong.VIRTUAL_HEIGHT / 5);
		}
	}

	/**
	 * Draws scores
	 */
	private void drawScore() {
		// TODO fix ugly hardcode

		// Player score
		game.font.draw(game.batch, "" + world.playerPoints, Pong.VIRTUAL_WIDTH / 2 - Pong.VIRTUAL_WIDTH / 5,
				Pong.VIRTUAL_HEIGHT - Pong.VIRTUAL_HEIGHT / 16);

		// AI score
		game.font.draw(game.batch, "" + world.aiPoints, Pong.VIRTUAL_WIDTH / 2 + Pong.VIRTUAL_WIDTH / 7,
				Pong.VIRTUAL_HEIGHT - Pong.VIRTUAL_HEIGHT / 16);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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
