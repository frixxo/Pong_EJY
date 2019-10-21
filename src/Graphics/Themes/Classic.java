package Graphics.Themes;

import GameManagment.IWorldInfo;
import Graphics.Assets;
import model.IGameObject;

public class Classic extends Assets {

    public Classic(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle){
        Assets.background = getImage("classicBg.png");

        bind(ball, "white.png");
        bind(rightpaddle, "white.png");
        bind(leftpaddle, "white.png");
    }
    @Override
    public String getTheme(){return "Classic";}
}