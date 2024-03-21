package brick_strategies;

import java.util.Random;

import MyObject.Ball;
import MyObject.Paddle;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.WindowController;
import danogl.util.Counter;
import setting.setting;

public class BrickStrategyFactory {

    private final String THROW_BALLS = "assets/img/items/mockBall.png";
    private final String IMG_NARROW = setting.FOLDER_ITEMS + "buffNarrow.png",
            IMG_WIDEN = setting.FOLDER_ITEMS + "buffWiden.png",
            IMG_SLOW = setting.FOLDER_ITEMS + "slow.png",
            IMG_QUICKEN = setting.FOLDER_ITEMS + "quicken.png";

    private GameObjectCollection gameObjects;
    private Counter numberBrick;

    private ImageReader img;

    private Ball ball;
    Effect[] brickStrategys;

    public BrickStrategyFactory(GameObjectCollection gameObjects, Counter numberBrick,
            ImageReader img, Ball ball, Paddle paddle, WindowController windowController) {
        this.gameObjects = gameObjects;
        this.numberBrick = numberBrick;
        Effect[] effect = { new NarrowAndWiden(paddle,
                img.readImage(IMG_NARROW, true), img.readImage(IMG_WIDEN, true)),
                new PaceGame(windowController,
                        img.readImage(IMG_QUICKEN, true), img.readImage(IMG_SLOW, true)) };
                
        brickStrategys = effect;
        this.img = img;
        this.ball = ball;
    }

    public CollisionStrategy getStrategy() {
        Random r = new Random();
        int number = r.nextInt(1000);

        if (number >= 600)
            return new Effects(gameObjects, numberBrick, getEffect());

        if (number >= 550)
            return new throwingBallsBrickStrategy(
                    gameObjects, numberBrick, ball.getCollisionSoundBackground(),
                    ball.getCollisionSoundPaddle(), img.readImage(THROW_BALLS, true));

        return new RemoveBrickStrategy(gameObjects, numberBrick);

    }

    public Effect getEffect() {
        Random r = new Random();
        return this.brickStrategys[r.nextInt(brickStrategys.length)];
        // return this.brickStrategys[1];
    }

}
