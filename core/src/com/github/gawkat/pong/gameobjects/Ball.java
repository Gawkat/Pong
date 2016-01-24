package com.github.gawkat.pong.gameobjects;

import java.util.Random;

import com.github.gawkat.pong.Pong;
import com.github.gawkat.pong.Sounds;
import com.github.gawkat.pong.screens.World;

public class Ball {

	final World world;

	private Random random;

	float x;
	float y;

	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;

	public static final int SPEED = 300;

	// Maximum bounce angle in radians
	public static final float MAX_ANGLE = 1.04719755f;

	public float speedX;
	public float speedY;

	public Ball(final World world) {
		this.world = world;

		random = new Random();

		reset();
	}

	/**
	 * Updates ball, movement and collision detection
	 * 
	 * @param delta
	 */
	public void update(float delta) {
		move(delta);
		if (!insideBounds()) {
			speedY = speedY * -1;
		}
	}

	/**
	 * Moves ball
	 * 
	 * @param delta
	 */
	private void move(float delta) {
		x = x + speedX * delta;

		y = y + speedY * delta;

	}

	/**
	 * Checks if ball is inside screen, plays sound if not
	 * 
	 * @return
	 */
	private boolean insideBounds() {
		if (x <= 0) {
			// Left player lose
			world.aiPoints++;
			Pong.playSound(Sounds.scorePoint, 0.1f);
			reset();
			return false;
		}
		if (x >= Pong.VIRTUAL_WIDTH - WIDTH) {
			// Right player lose
			world.playerPoints++;
			Pong.playSound(Sounds.scorePoint, 0.1f);
			reset();
			return false;
		}
		if (y <= 0) {
			// Bounce
			Pong.playSound(Sounds.wallBounce, 0.1f);
			return false;
		}
		if (y >= Pong.VIRTUAL_HEIGHT - HEIGHT) {
			// Bounce
			Pong.playSound(Sounds.wallBounce, 0.1f);
			return false;
		}
		return true;
	}

	/**
	 * Bounces ball based on location on paddle
	 * 
	 * @param paddleLocation
	 */
	public void bounce(float paddleLocation, boolean player) {
		float relativeLocation = paddleLocation / (Paddle.HEIGHT / 2);

		float bounceAngle = relativeLocation * MAX_ANGLE;

		speedX = (float) (SPEED * Math.cos(bounceAngle));
		speedY = (float) (SPEED * -Math.sin(bounceAngle));

		if (!player) {
			speedX = -1 * speedX;
		}

	}

	/**
	 * Resets the position of the ball
	 */
	public void reset() {
		x = Pong.VIRTUAL_WIDTH / 2 - Ball.WIDTH / 2;
		y = Pong.VIRTUAL_HEIGHT / 2 - Ball.HEIGHT / 2;

		speedX = SPEED;
		speedY = SPEED;

		if (!random.nextBoolean()) {
			speedX = speedX * -1;
		}
		if (!random.nextBoolean()) {
			speedY = speedY * -1;
		}

		world.reset();
	}

	/**
	 * Returns the x position of the ball
	 * 
	 * @return
	 */
	public float getX() {
		return x;
	}

	/**
	 * Returns the y position of the ball
	 * 
	 * @return
	 */
	public float getY() {
		return y;
	}

}
