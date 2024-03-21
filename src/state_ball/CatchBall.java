package state_ball;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
import java.util.Random;

public class CatchBall implements statusBall {
    final float SPEED_BALL = 600;

    private GameObject paddle;
    private UserInputListener inputListener;
    private float ballHeight;

    public CatchBall(GameObject paddle, UserInputListener inputListener, float ballHeight) {
        this.paddle = paddle;
        this.inputListener = inputListener;
        this.ballHeight = (paddle.getDimensions().y() + ballHeight) / 2;
    }

    @Override
    public statusBall launchBall(GameObject ball) {
        ball.setVelocity(Vector2.ZERO);
        ball.setCenter(paddle.getCenter().add(Vector2.UP.mult(ballHeight)));
        if (this.inputListener.isKeyPressed(KeyEvent.VK_SPACE))
            return ThrowBall(ball);

        return this;
    }

    private statusBall ThrowBall(GameObject ball) {
        Random rand = new Random();
        ball.setVelocity(new Vector2(rand.nextBoolean() ? SPEED_BALL : -SPEED_BALL,
                -SPEED_BALL));

        return new FreeBall();
    }

}
