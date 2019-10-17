package Graphics;

import GameManagment.IWorldInfo;
import model.IGameObject;

public class Cool extends Assets {

    public Cool(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle){
        Assets.background = getImage("coolBg.png");
        System.out.println("cool");
        bind(ball, "coolBall.png");
        bind(rightpaddle, "coolredpaddle.png");
        bind(leftpaddle, "coolbluepaddle.png");
    }

}