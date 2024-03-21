package brick_strategies;

import java.util.Random;

import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class PaceGame implements Effect {

    private final Vector2 SIZE_PACE_GAME = new Vector2(50, 16.5f);
    private final String FAST_SPEEDS = "fast", SLOW_SPEEDS = "slow";
    private final double SLOWEST = 0.9, FASTEST = 1.1, AMOUUT_IMPACT = 0.1;

    private WindowController windowController;
    private Renderable fastRenderable;
    private Renderable slowRenderable;
    private String tag;

    public PaceGame(WindowController windowController, Renderable fastRenderable,
            Renderable slowRenderable) {
        this.windowController = windowController;
        this.fastRenderable = fastRenderable;
        this.slowRenderable = slowRenderable;
    }

    @Override
    public void createTag() {
        Random r = new Random();

        if (this.windowController.getTimeScale() <= SLOWEST)
            this.tag = FAST_SPEEDS;
        else if (this.windowController.getTimeScale() >= FASTEST)
            this.tag = SLOW_SPEEDS;
        else
            tag = r.nextBoolean() ? FAST_SPEEDS : SLOW_SPEEDS;

    }

    @Override
    public Vector2 getDimensions() {
        return SIZE_PACE_GAME;
    }

    @Override
    public Renderable getRenderable() {
        if (tag.equals(FAST_SPEEDS))
            return this.fastRenderable;

        return this.slowRenderable;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public void useStrategy(String tag) {
        double n = 0;
        if (tag.equals(SLOW_SPEEDS)) {
            if (this.windowController.getTimeScale() > SLOWEST)
                n = -AMOUUT_IMPACT;
        } else if (this.windowController.getTimeScale() < FASTEST)
            n = AMOUUT_IMPACT;

        this.windowController.setTimeScale((float) (this.windowController.getTimeScale() + n));
    }

}
