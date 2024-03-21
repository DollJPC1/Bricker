package MyObject;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import state_ball.*;

public class Ball extends GameObject {
    final float SPEED_BALL = 600;

    private Sound collisionSoundBackground;
    private Sound collisionSoundPaddle;
    private statusBall stateBallNow;
    private statusBall ballHolder;

    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
            Sound collisionSoundBackground, Sound collisionSoundPaddle, statusBall catchBall) {
        super(topLeftCorner, dimensions, renderable);

        this.collisionSoundBackground = collisionSoundBackground;
        this.collisionSoundPaddle = collisionSoundPaddle;
        this.ballHolder = catchBall;
    }

    public void catchBall() {
        this.stateBallNow = this.ballHolder;
        ballHolder.launchBall(this);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getClass() == Ball.class || other.getClass() == StatusDefiners.class)
            return;
        

        setVelocity(getVelocity().flipped(collision.getNormal()));

        if (other.getClass() == Paddle.class)
            this.collisionSoundPaddle.play();
        else
            this.collisionSoundBackground.play();
    }

    @Override
    public void update(float arg0) {
        super.update(arg0);
        stateBallNow = this.stateBallNow.launchBall(this);
    }

    public Sound getCollisionSoundBackground() {
        return collisionSoundBackground;
    }

    public Sound getCollisionSoundPaddle() {
        return collisionSoundPaddle;
    }

}
