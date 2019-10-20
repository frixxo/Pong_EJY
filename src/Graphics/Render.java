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


        //Draws points
        fg.setFill(Color.WHITE);
        fg.setFont(Font.font(24));
        fg.fillText(gameobjects[2].getPoints() +"",230,50);//poäng vänster
        fg.fillText(gameobjects[1].getPoints() +"",350,50);//poäng höger
        fg.fillText(gameobjects[0].getPoints()+"",290,50);//antal studs

        fg.setFont(Font.font(12));
        fg.fillText("bounces:",270,20);

        //Draws objects
        for (int i=0; i<gameobjects.length; i++){
            if (renderDebug) {
                fg.strokeRect(gameobjects[i].GetPosition().GetX(), gameobjects[i].GetPosition().GetY(),
                        gameobjects[i].GetSize().GetX(), gameobjects[i].GetSize().GetY());

            } else {
                fg.drawImage(assets.get(gameobjects[i]), (gameobjects[i].GetPosition().GetX()),
                        (GUI.GAME_HEIGHT-gameobjects[i].GetPosition().GetY()), gameobjects[i].GetSize().GetX(), gameobjects[i].GetSize().GetY());
            }
        }
    }

     void Background(GraphicsContext bg, Assets assets) {
        if (!renderDebug) {
            bg.clearRect(0,0,GUI.GAME_WIDTH,GUI.GAME_WIDTH);
            bg.drawImage(assets.GetBackground(), 0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);
        }
    }

    void menu(GraphicsContext fg,GraphicsContext bg,Assets assets) {
        fg.clearRect(0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);
        bg.drawImage(assets.menupic, 0, 0, GUI.GAME_WIDTH, GUI.GAME_HEIGHT);
    }
}
