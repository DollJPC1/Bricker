package brick_strategies;

import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public interface Effect{

    void createTag();

    Vector2 getDimensions();

    Renderable getRenderable();

    String getTag();

    void useStrategy(String tag);

}
