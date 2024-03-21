package MyObject;

import brick_strategies.Effect;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import setting.setting;

public class StatusDefiners extends GameObject {

    private Effect effect;
    private GameObjectCollection gameObjects;

    public StatusDefiners(GameObjectCollection gameObjects, Vector2 topLeftCorner,
            Vector2 dimensions, Renderable renderable, Effect myEffect) {
        super(topLeftCorner, dimensions, renderable);
        this.gameObjects = gameObjects;
        this.effect = myEffect;
    }

    public void throwItem() {
        setVelocity(Vector2.DOWN.mult(50));
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getClass() == Paddle.class) {
            effect.useStrategy(getTag());
            removeItem();
        }
    }

    @Override
    public void update(float arg0) {
        super.update(arg0);
        if (getCenter().y() >= setting.WINDOW_HEIGHT)
            removeItem();

    }

    private void removeItem() {
        gameObjects.removeGameObject(this);
    }

}
