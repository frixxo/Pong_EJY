package Graphics;

import GameManagment.IWorldInfo;
import model.IGameObject;

public class Cool extends Assets {

    public Cool(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle){
        background = getImage("coolBg");

        bind(ball, "coolBall");
        bind(rightpaddle, "coolredpaddle");
        bind(leftpaddle, "coolbluepaddle");
    }

}