package com.github.gawkat.pong.gameobjects;

import com.badlogic.gdx.Gdx;
import com.github.gawkat.pong.PaddleAI;
import com.github.gawkat.pong.Pong;
import com.github.gawkat.pong.Sounds;

public class Paddle {

	float x;
	float y;

	boolean usesAI;

	Ball ball;

	public static final int WIDTH = 20;
	public static final int HEIGHT = 80;

	PaddleAI paddleAI;

	public Paddle(Ball ball, boolean usesAI, float x, float y) {
		this.usesAI = false;

		if (usesAI) {
			this.usesAI = true;
			paddleAI = new PaddleAI(this, ball);
		}

		this.ball = ball;
		this.x = x;
		this.y = y;
	}

	/**
	 * Updates Paddle position
	 * 
	 * @param delta
	 */
	public void update(float delta) {
		// Checks paddle hitbox
		if (overlaps()) {
			Pong.playSound(Sounds.paddleBounce, 0.1f);
			ball.bounce(y + (HEIGHT / 2) - ball.getY(), !usesAI);
		}

		// Updates paddle position
		if (usesAI) {
			y = paddleAI.update(delta);
		} else {
			if (pointerIsInsideBounds()) {
				y = Pong.VIRTUAL_HEIGHT - Gdx.input.getY() - HEIGHT / 2;
			}

		}
	}

	/**
	 * Checks if mouse pointers is inside screen and bounds
	 * 
	 * @return
	 */
	private boolean pointerIsInsideBounds() {
		if (Gdx.input.getY() >= Paddle.HEIGHT / 2 && Gdx.input.getY() <= Pong.VIRTUAL_HEIGHT - Paddle.HEIGHT / 2) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if paddle overlaps with ball
	 */
	public boolean overlaps() {
		if (ball.speedX < 0) {
			if (ball.getX() >= x && ball.getX() <= x + WIDTH) {
				if (ball.getY() + Ball.HEIGHT >= y && ball.getY() <= y + HEIGHT) {
					return true;
				}
			}
		} else {
			if (ball.getX() + Ball.WIDTH >= x && ball.getX() + Ball.WIDTH <= x + WIDTH) {
				if (ball.getY() + Ball.HEIGHT >= y && ball.getY() <= y + HEIGHT) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Resets the position of the paddle
	 */
	public void reset() {
		setY(Pong.VIRTUAL_HEIGHT / 2 - Paddle.HEIGHT / 2);
	}

	/**
	 * Sets the x position of the paddle
	 * 
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Returns the x position of the paddle
	 * 
	 * @return
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the y position of the paddle
	 * 
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Returns the y position of the paddle
	 * 
	 * @return
	 */
	public float getY() {
		return y;
	}

}
