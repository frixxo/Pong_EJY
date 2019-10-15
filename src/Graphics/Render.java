package Graphics;

import GameManagment.IWorldInfo;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.IGameObject;

public class Render{
    GUI gui = new GUI();


    // For debugging, see render()
    static boolean renderDebug = false; //true;

    void game(IWorldInfo gamemanager) {
        IGameObject[] gameobjects= gamemanager.GetAllGameObjects();
        gui.fg.clearRect(0, 0, gui.GAME_WIDTH, gui.GAME_HEIGHT);    // Clear everything
        gui.fg.setFill(Color.WHITE);
        gui.render.Background();
        gui.fg.setFont(Font.font(18));
        //Todo  points
        for (int i=0; i<gameobjects.length; i++){
            if (renderDebug) {
                gui.fg.strokeRect(gameobjects[i].GetPosition().GetX(), gameobjects[i].GetPosition().GetY(),
                        gameobjects[i].GetSize().GetX(), gameobjects[i].GetSize().GetY());

            } else {
                gui.fg.drawImage(gui.assets.get(gameobjects[i]), gameobjects[i].GetPosition().GetX(),
                        gameobjects[i].GetPosition().GetY(), gameobjects[i].GetSize().GetX(), gameobjects[i].GetSize().GetY());
            }
        }
    }

     void Background() {
        if (!renderDebug) {
            gui.bg.drawImage(gui.assets.GetBackground(), 0, 0, gui.GAME_WIDTH, gui.GAME_HEIGHT);
        }
    }

    void menu() {
        gui.fg.clearRect(0, 0, gui.GAME_WIDTH, gui.GAME_HEIGHT);
        gui.bg.drawImage(gui.assets.menupic, 0, 0, gui.GAME_WIDTH, gui.GAME_HEIGHT);
    }
}
