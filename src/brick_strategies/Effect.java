package brick_strategies;

import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public interface Effect{


    Vector2 getDimensions();

    Renderable getRenderable();

    String getTag();

    void useStrategy(String tag);

}
