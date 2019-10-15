package Graphics;

import model.IGameObject;

public class Duckie extends Assets{

    public Duckie(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle) {
        background = getImage("duckieBg");
        bind(ball, "duckieBall");
        bind(rightpaddle, "coolredpaddle");
        bind(leftpaddle, "coolbluepaddle");
    }
}
