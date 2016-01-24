package com.github.gawkat.pong;

import com.github.gawkat.pong.gameobjects.Ball;
import com.github.gawkat.pong.gameobjects.Paddle;

public class PaddleAI {

	Paddle paddle;
	Ball ball;

	private static final int SPEED = 300;

	public PaddleAI(Paddle paddle, Ball ball) {
		this.paddle = paddle;
		this.ball = ball;
	}

	/**
	 * Positions paddle using super smart algorithm
	 * 
	 * @param delta
	 * @return
	 */
	public float update(float delta) {
		if (ball.getX() >= Pong.VIRTUAL_WIDTH / 2 - Ball.WIDTH / 2) {
			return moveTowards(delta);
		} else {
			return paddle.getY();
		}
	}

	/**
	 * Moves paddle towards ball if necessary
	 * 
	 * @param delta
	 * @return
	 */
	public float moveTowards(float delta) {
		// If paddle is inside bounds and is moving towards paddle
		if (ball.getY() >= Paddle.HEIGHT / 2 && ball.getY() <= Pong.VIRTUAL_HEIGHT - Paddle.HEIGHT / 2
				&& ball.speedX > 0) {
			// Paddle should move up
			if (ball.getY() + Ball.HEIGHT > paddle.getY() + Paddle.HEIGHT / 2) {
				return paddle.getY() + SPEED * delta;
			} else if (ball.getY() < paddle.getY() + Paddle.HEIGHT / 2) {
				// Paddle should move down
				return paddle.getY() - SPEED * delta;
			}
		}
		return paddle.getY();
	}

}
