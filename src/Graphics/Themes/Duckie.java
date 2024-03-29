package Graphics.Themes;

import Graphics.Assets;
import model.IGameObject;

public class Duckie extends Assets {

    public Duckie(IGameObject ball, IGameObject rightpaddle, IGameObject leftpaddle) {
        Assets.background = getImage("duckieBg.jpg");

        bind(ball, "duckieBall.png");
        bind(rightpaddle, "coolredpaddle.png");
        bind(leftpaddle, "coolbluepaddle.png");
    }
    @Override
    public String getTheme(){return "Duckie";}
}
