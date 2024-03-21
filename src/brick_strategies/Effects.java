package brick_strategies;

import MyObject.StatusDefiners;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import danogl.util.Vector2;

public class Effects extends RemoveBrickStrategy {

    private final float SPEED = 200;

    private Effect effect;

    public Effects(GameObjectCollection gameObjects, Counter numberBrick, Effect effect) {
        super(gameObjects, numberBrick);
        this.effect = effect;
    }

    @Override
    public void onCollision(GameObject thisObj, Collision collision) {
        super.onCollision(thisObj, collision);
        effect.createTag();
        StatusDefiners statusDefiners = new StatusDefiners(gameObjects, Vector2.ZERO,
                this.effect.getDimensions(), this.effect.getRenderable(), this.effect);
        statusDefiners.setTag(effect.getTag());
        statusDefiners.setCenter(thisObj.getCenter());
        statusDefiners.setVelocity(Vector2.DOWN.mult(SPEED));
        gameObjects.addGameObject(statusDefiners);
    }

    

}
