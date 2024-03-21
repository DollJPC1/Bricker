package MyObject;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import setting.setting;

import java.awt.event.KeyEvent;

public class Paddle extends GameObject {
    final float PADDLE_SPPED = 700;
    final float MIN_DISTANCE_FROM_SCREEN_EDGE = 15;
    private UserInputListener inputListener;

    public Paddle(Vector2 topLeftCorner, Vector2 dimensions,
            Renderable renderable, UserInputListener inputListener) {
        super(topLeftCorner, dimensions, renderable);

        this.inputListener = inputListener;
    }

    @Override
    public void update(float arg0) {
        super.update(arg0);
        Vector2 move = Vector2.ZERO;
        // go to Left
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT) &&
                getTopLeftCorner().x() >= MIN_DISTANCE_FROM_SCREEN_EDGE)
            move = move.add(Vector2.LEFT);
        // go to Right
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT) &&
                getTopLeftCorner().x() <= setting.WINDOW_WIDTH -
                        getDimensions().x() - MIN_DISTANCE_FROM_SCREEN_EDGE)
            move = move.add(Vector2.RIGHT);

        setVelocity(move.mult(PADDLE_SPPED));
    }

}
