package com.github.gawkat.pong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.github.gawkat.pong.Pong;
import com.github.gawkat.pong.gameobjects.Ball;
import com.github.gawkat.pong.gameobjects.Paddle;

public class World {

	Pong game;

	Texture white;

	Ball ball;
	Paddle playerPaddle;
	Paddle aiPaddle;

	public int playerPoints = 0;
	public int aiPoints = 0;

	public World(final Pong game) {
		this.game = game;

		// Game texture
		white = new Texture(Gdx.files.internal("sprite.png"));

		/*
		 * Creates all game objects
		 */
		ball = new Ball(this);

		playerPaddle = new Paddle(ball, false, Pong.VIRTUAL_WIDTH / 8, Pong.VIRTUAL_HEIGHT / 2 - Paddle.HEIGHT / 2);
		aiPaddle = new Paddle(ball, true, (Pong.VIRTUAL_WIDTH / 8) * 7 - Paddle.WIDTH,
				Pong.VIRTUAL_HEIGHT / 2 - Paddle.HEIGHT / 2);
	}

	public void update(float delta) {
		ball.update(delta);
		playerPaddle.update(delta);
		aiPaddle.update(delta);
	}

	public void draw() {
		drawNet();
		drawBall();
		drawPaddles();
	}

	/**
	 * Draws net between player sides
	 */
	private void drawNet() {
		int width = 2; // width of each line
		int height = 12; // height of each line
		int space = 4; // space between each line
		int x = Pong.VIRTUAL_WIDTH / 2 - width; // x position of all lines
		int y = 2; // y position of first line

		// Draws 30 lines, to form net
		for (int i = 0; i < 30; i++) {
			game.batch.draw(white, x, y, width, height);
			y = y + space + height; // Increase y value to fit new line
		}
	}

	/**
	 * Draws ball
	 */
	private void drawBall() {
		game.batch.draw(white, ball.getX(), ball.getY(), Ball.WIDTH, Ball.HEIGHT);
	}

	/**
	 * Draws the paddles
	 */
	private void drawPaddles() {
		game.batch.draw(white, playerPaddle.getX(), playerPaddle.getY(), Paddle.WIDTH, Paddle.HEIGHT);
		game.batch.draw(white, aiPaddle.getX(), aiPaddle.getY(), Paddle.WIDTH, Paddle.HEIGHT);
	}

	/**
	 * Resets the world
	 */
	public void reset() {
		// TODO fix ugly null check
		if (aiPaddle != null) {
			aiPaddle.reset();
		}
	}

}
