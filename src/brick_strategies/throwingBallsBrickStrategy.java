package brick_strategies;

import MyObject.Ball;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import setting.setting;
import state_ball.statusBall;

/**
 * throwingBallsBrickStrategy
 */
public class throwingBallsBrickStrategy extends RemoveBrickStrategy {

    private final int AMOUNT_BALLS = 3;
    private final float MINI_BALL = 20, WINDOW_HEIGHT = 800, SPEED_MINI_BALL = 800;
    private Sound collisionSoundPaddle;
    private Sound collisionSoundBackground;
    private Renderable img;

    public throwingBallsBrickStrategy(GameObjectCollection gameObjects, Counter numberBrick,
            Sound collisionSoundBackground, Sound collisionSoundPaddle, Renderable img) {
        super(gameObjects, numberBrick);
        this.collisionSoundBackground = collisionSoundBackground;
        this.collisionSoundPaddle = collisionSoundPaddle;
        this.img = img;
    }

    @Override
    public void onCollision(GameObject thisObj, Collision collision) {
        super.onCollision(thisObj, collision);
            Ball[] ball = new Ball[AMOUNT_BALLS];
            statusBall removeBalls = new statusBall() {
                public statusBall launchBall(GameObject ball) {
                    if (ball.getCenter().y() >= WINDOW_HEIGHT)
                        gameObjects.removeGameObject(thisObj);
                    return this;
                }
            };

            for (int i = 0; i < ball.length; i++) {
                ball[i] = new Ball(
                        Vector2.ZERO, Vector2.ONES.mult(MINI_BALL),
                        img, collisionSoundBackground, collisionSoundPaddle, removeBalls);
                ball[i].setTag(setting.DONT_TOUCH);
                ball[i].setCenter(thisObj.getCenter());
                ball[i].setVelocity(new Vector2(SPEED_MINI_BALL * (1 - i), SPEED_MINI_BALL));
                ball[i].catchBall();
                gameObjects.addGameObject(ball[i]);
            } 
        
    }

}