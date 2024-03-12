package state_ball;

import danogl.GameObject;

public class FreeBall implements statusBall{

    public FreeBall() {
    }

    @Override
    public statusBall launchBall(GameObject ball) {
        return this;
    }

    
}
