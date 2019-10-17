package Graphics;

import GameManagment.IWorldInfo;
import model.IGameObject;

public class Classic extends Assets {

    public Classic(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle){
        background = getImage("classicbg");

        bind(ball, "classicBall");
        bind(rightpaddle, "classicpaddle");
        bind(leftpaddle, "classicpaddle");
    }

}