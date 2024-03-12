package brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.util.Counter;

public class VoidBrickStrategy implements CollisionStrategy{

    public VoidBrickStrategy(Counter numberBrick) {
        numberBrick.decrement();
    }

    @Override
    public void onCollision(GameObject thisObj, Collision collision) {
    }
    
}
