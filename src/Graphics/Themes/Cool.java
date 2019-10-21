package Graphics.Themes;

import GameManagment.IWorldInfo;
import Graphics.Assets;
import model.IGameObject;

public class Cool extends Assets {

    public Cool(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle){
        Assets.background = getImage("coolBg.png");

        bind(ball, "coolBall.png");
        bind(rightpaddle, "coolredpaddle.png");
        bind(leftpaddle, "coolbluepaddle.png");
    }
    @Override
    public String getTheme(){return "Cool";}
}