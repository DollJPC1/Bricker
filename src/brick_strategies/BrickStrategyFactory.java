package brick_strategies;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;

public class BrickStrategyFactory {

    // private static final Map<String, Renderable> MARKS_DRAWINGS = Map.of(
    //         Mark.BLANK, new String[] {
    //                 "         ",
    //                 "         ",
    //                 "         " },

    //         Mark.X, new String[] {
    //                 "  X   X  ",
    //                 "    X    ",
    //                 "  X   X  " },
    //         Mark.O, new String[] {
    //                 "   OOO  ",
    //                 "  O   O ",
    //                 "   OOO   " }
        private final String[] nameBrickStrategy = {"Remove", ""};

    private GameObjectCollection gameObjects;
    private Counter numberBrick;

    public BrickStrategyFactory(GameObjectCollection gameObjects, Counter numberBrick,
             Renderable[] images) {
                this.gameObjects = gameObjects;
                this.numberBrick = numberBrick;
    }

    public CollisionStrategy getStrategy() {
        Random r = new Random();
        switch (nameBrickStrategy[r.nextInt(nameBrickStrategy.length)]) {
            case "Remove":
                return new RemoveBrickStrategy(gameObjects, numberBrick);        
            default:
                return new VoidBrickStrategy(numberBrick);
        }
    }

}
