package brick_strategies;

import java.util.Random;
import MyObject.Paddle;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class NarrowAndWiden implements Effect {
    private final float WIDEN_OR_NARROW = 10;
    private final Vector2 SIZE_NARROW_AND_WIDEN = new Vector2(50, 16.5f);
    private final String NARROW = "narrow", WIDEN = "widen";

    private String tag;
    private Paddle paddle;
    private Renderable narrowRenderable;
    private Renderable widenRenderable;

    public NarrowAndWiden(Paddle paddle, Renderable narrowRenderable,
            Renderable widenRenderable) {
        this.paddle = paddle;
        this.narrowRenderable = narrowRenderable;
        this.widenRenderable = widenRenderable;
    }

    @Override
    public void useStrategy(String tag) {
        if (tag.equals(NARROW))
            this.paddle.setDimensions(
                    paddle.getDimensions().add(Vector2.RIGHT.mult(WIDEN_OR_NARROW * -1)));
        else
            this.paddle.setDimensions(
                    paddle.getDimensions().add(Vector2.RIGHT.mult(WIDEN_OR_NARROW)));
    }

    @Override
    public Vector2 getDimensions() {
        return SIZE_NARROW_AND_WIDEN;
    }

    @Override
    public Renderable getRenderable() {
        Random r = new Random();
        if (r.nextBoolean()) {
            this.tag = NARROW;
            return this.narrowRenderable;
        }

        this.tag = WIDEN;
        return this.widenRenderable;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

}
