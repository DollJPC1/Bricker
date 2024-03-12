package brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;

public interface CollisionStrategy {

    void onCollision(GameObject thisObj, Collision collision);

}
