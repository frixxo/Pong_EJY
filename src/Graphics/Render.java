package Graphics;

import GameManagment.IWorldInfo;
import com.sun.prism.Graphics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.IGameObject;

public class Render{

    static boolean renderDebug = false; //true;
    void game(IWorldInfo worldInfo,GraphicsContext fg,GraphicsContext bg,Assets assets) {
        IGameObject[] gameobjects= worldInfo.GetAllGameObjects();
        fg.clearRect(0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);    // Clear everything
        fg.setFill(Color.WHITE);
        Background(fg,bg,assets);
        fg.setFont(Font.font(18));
        //Todo  points
        for (int i=0; i<gameobjects.length; i++){
            if (renderDebug) {
                fg.strokeRect(gameobjects[i].GetPosition().GetX(), gameobjects[i].GetPosition().GetY(),
                        gameobjects[i].GetSize().GetX(), gameobjects[i].GetSize().GetY());

            } else {
                fg.drawImage(assets.get(gameobjects[i]), gameobjects[i].GetPosition().GetX(),
                        gameobjects[i].GetPosition().GetY(), gameobjects[i].GetSize().GetX(), gameobjects[i].GetSize().GetY());
            }
        }
    }

     void Background(GraphicsContext fg,GraphicsContext bg, Assets assets) {
        if (!renderDebug) {
            fg.clearRect(0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);    // Clear everything
            fg.setFill(Color.WHITE);
            bg.drawImage(assets.GetBackground(), 0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);
        }
    }

    void menu(GraphicsContext fg,GraphicsContext bg,Assets assets) {
        fg.clearRect(0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);
        bg.drawImage(assets.menupic, 0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);
    }
}
