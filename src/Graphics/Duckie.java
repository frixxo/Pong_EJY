package Graphics;

import model.IGameObject;

public class Duckie extends Assets{

    public Duckie(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle) {
        background = getImage("duckieBg.png");
        bind(ball, "duckieBall.png");
        bind(rightpaddle, "coolredpaddle.png");
        bind(leftpaddle, "coolbluepaddle.png");
    }
}
