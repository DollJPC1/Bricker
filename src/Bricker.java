import java.util.LinkedList;
import MyObject.*;
import brick_strategies.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import setting.setting;
import state_ball.CatchBall;
import danogl.util.Counter;

public class Bricker extends GameManager {

        private Ball ball;
        private Vector2 windoeDimensions;
        private WindowController windowController;
        private Counter numberBrick;
        private Paddle usePaddle;

        private LinkedList<GameObject> hearts;

        public Bricker(String windowTitle, Vector2 windowDimensions) {
                super(windowTitle, windowDimensions);
        }

        @Override
        public void update(float arg0) {
                super.update(arg0);
                if (this.numberBrick.value() == 0)
                        if (this.windowController.openYesNoDialog("Winner!\n play agen?"))
                                windowController.resetGame();
                        else
                                windowController.closeWindow();
                else if (this.ball.getCenter().y() >= setting.WINDOW_HEIGHT)
                        if (takesHeart())
                                if (this.windowController.openYesNoDialog("lost...\n play agen?"))
                                        windowController.resetGame();
                                else
                                        windowController.closeWindow();
                        else
                                this.ball.catchBall();
                else if (this.ball.getCenter().y() <= 0 || this.ball.getCenter().x() <= 0 || this.ball.getCenter()
                                .x() >= setting.WINDOW_WIDTH)
                        this.ball.catchBall();
        }

        @Override
        public void initializeGame(ImageReader img, SoundReader soundReader,
                        UserInputListener inputListener, WindowController windowController) {
                this.windowController = windowController;
                windowController.setTargetFramerate(60);
                super.initializeGame(img, soundReader, inputListener, windowController);
                windoeDimensions = windowController.getWindowDimensions();

                // create baclgrund
                Renderable imgBackground = img.readImage(setting.IMG_BACKGROUND, false);
                createBackground(imgBackground);
                // create border
                createBorders();

                // create heart
                Renderable heartImg = img.readImage(setting.IMG_HEART, true);
                createHeart(heartImg);
                // creat paddle
                Renderable paddleImg = img.readImage(setting.IMG_PADDLE, false);
                createPaddle(paddleImg, inputListener);

                // create ball
                Renderable ballIng = img.readImage(setting.IMG_BALL, true);
                Sound collisionSoundBackground = soundReader.readSound(setting.SOND_BALL_BACKGROUND);
                Sound collisionSoundPaddle = soundReader.readSound(setting.SOND_BALL_PADDLE);
                createBall(ballIng, collisionSoundBackground, collisionSoundPaddle, inputListener);
                this.numberBrick = new Counter(setting.BRICKS_COL * setting.BRICKS_ROW);

                // create Brick
                Renderable brick = img.readImage(setting.IMG_BRICK, false);

                BrickStrategyFactory brickStrategyFactory = new BrickStrategyFactory(
                                gameObjects(), numberBrick, img, this.ball, this.usePaddle, this.windowController);

                createBrick(brick, brickStrategyFactory);
                System.out.println(img.getClass());
        }

        private void createBackground(Renderable img) {
                GameObject background = new GameObject(Vector2.ZERO,
                                this.windowController.getWindowDimensions(), img);
                background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
                gameObjects().addGameObject(background, Layer.BACKGROUND);

        }

        private void createBorders() {

                GameObject[] borders = {
                                // UP
                                new GameObject(
                                                Vector2.ZERO, new Vector2(
                                                                windoeDimensions.x(),
                                                                setting.THICKNESS),
                                                null),
                                // L
                                new GameObject(
                                                Vector2.ZERO, new Vector2(
                                                                setting.THICKNESS, windoeDimensions.y()),
                                                null),
                                // R
                                new GameObject(
                                                Vector2.ZERO, new Vector2(
                                                                setting.THICKNESS, windoeDimensions.y()),
                                                null),
                                // Done
                                // new GameObject(
                                // Vector2.ZERO, new Vector2(
                                // windoeDimensions.x(), THICKNESS),
                                // img)
                };

                borders[2].setCenter(new Vector2(windoeDimensions.x(), windoeDimensions.y() / 2));
                // borders[3].setCenter(new Vector2(windoeDimensions.x() / 2,
                // windoeDimensions.y()));

                borders[0].setTag(setting.WINNER_TAG);
                // borders[3].setTag(lOST_TAG);

                for (GameObject obj : borders)
                        gameObjects().addGameObject(obj, Layer.STATIC_OBJECTS);

        }

        private void createBall(Renderable imgRenderable,
                        Sound collisionSoundBackground, Sound collisionSoundPaddle, UserInputListener inputListener) {

                this.ball = new Ball(Vector2.ZERO, Vector2.ONES.mult(
                                setting.SIZE_BALL), imgRenderable,
                                collisionSoundBackground, collisionSoundPaddle,
                                new CatchBall(usePaddle, inputListener, setting.SIZE_BALL));
                ball.catchBall();
                gameObjects().addGameObject(this.ball);
        }

        private void createPaddle(Renderable imgRenderable,
                        UserInputListener inputListener) {

                usePaddle = new Paddle(
                                Vector2.ZERO, new Vector2(
                                                setting.PADDLE_WIDTH,
                                                setting.PADDLE_HEIGHT),
                                imgRenderable, inputListener);//, windoeDimensions.x() - setting.PADDLE_WIDTH);
                usePaddle.setCenter(
                                new Vector2(windoeDimensions.x() / 2, windoeDimensions.y() - setting.DISTANCE_BORDER));
                gameObjects().addGameObject(usePaddle);
        }

        private void createBrick(Renderable brickImg, BrickStrategyFactory brickStrategyFactory) {

                Brick[][] allBricks = new Brick[setting.BRICKS_ROW][setting.BRICKS_COL];

                final Vector2 sizeBrick = new Vector2(
                                ((this.windoeDimensions.x() - (setting.BRICKS_COL - 1) * setting.ROW_DISTANCE)
                                                - setting.THICKNESS * 2)
                                                / setting.BRICKS_COL,
                                (setting.WINDOW_HEIGHT / 2 - setting.DISTANCE_FRAME)
                                                / (setting.BRICKS_ROW * setting.COL_DISTANCE));
                System.out.println(sizeBrick.y());
                for (int column = 0; column < setting.BRICKS_ROW; column++)
                        for (int row = 0; row < setting.BRICKS_COL; row++) {
                                allBricks[column][row] = new Brick(
                                                Vector2.ZERO, sizeBrick, brickImg, brickStrategyFactory.getStrategy());

                                allBricks[column][row].setCenter(new Vector2(
                                                (((row + 1) * setting.ROW_DISTANCE + (sizeBrick.x() * row))
                                                                + sizeBrick.x() / 2)
                                                                + setting.THICKNESS,
                                                setting.DISTANCE_FRAME
                                                                + ((column + 1) * setting.COL_DISTANCE
                                                                                + sizeBrick.y() * column)
                                                                + setting.THICKNESS));
                                gameObjects().addGameObject(allBricks[column][row], Layer.STATIC_OBJECTS);
                        }
        }

        private void createHeart(Renderable img) {
                this.hearts = new LinkedList<GameObject>();
                for (int i = 0; i < setting.HEARTS; i++) {
                        GameObject heart = new GameObject(Vector2.ZERO, Vector2.ONES.mult(setting.SIZE_HEART), img);
                        heart.setCenter(new Vector2(
                                        setting.SIZE_HEART / 2 + i * (setting.SIZE_HEART + setting.DISTANCE_HEART),
                                        setting.WINDOW_HEIGHT - setting.SIZE_HEART / 2));
                        heart.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
                        gameObjects().addGameObject(heart);
                        hearts.add(heart);
                }
        }

        private boolean takesHeart() {
                gameObjects().removeGameObject(this.hearts.removeLast());
                return this.hearts.isEmpty();
        }

        public static void main(String[] args) {
                new Bricker("Ping", new Vector2(setting.WINDOW_WIDTH, setting.WINDOW_HEIGHT)).run();
        }

}
