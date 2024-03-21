package brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class PaceGame implements Effect {

    public PaceGame(GameObjectCollection gameObjects, Counter numberBrick, Vector2 dimensions,
            Renderable renderable) {
    }

    @Override
    public void useStrategy(String tag) {
    }

    @Override
    public Vector2 getDimensions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDimensions'");
    }

    @Override
    public Renderable getRenderable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRenderable'");
    }

    @Override
    public String getTag() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTag'");
    }

}
