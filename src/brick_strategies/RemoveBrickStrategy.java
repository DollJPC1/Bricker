package brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

public class RemoveBrickStrategy implements CollisionStrategy {

    protected GameObjectCollection gameObjects;
    private Counter numberBrick;

    protected boolean bool;


    public RemoveBrickStrategy(GameObjectCollection gameObjects, Counter numberBrick) {
        this.gameObjects = gameObjects;
        this.numberBrick = numberBrick;
        bool = false;
    }

    public void onCollision(GameObject thisObj, Collision collision) {
        if (bool)
            return;
        numberBrick.decrement();
        this.bool = true;
        this.gameObjects.removeGameObject(thisObj, Layer.STATIC_OBJECTS);
    }

}