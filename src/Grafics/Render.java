package Grafics;

import GameManagment.GM;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.IGameObject;
import model.Rigbody;

public class Render{
    GUI gui = new GUI();


    // For debugging, see render()
    static boolean renderDebug = false; //true;

    void game(GM gamemanager) {
        IGameObject[] gameobjects=gamemanager.GetAllGameObjects();
        gui.fg.clearRect(0, 0, gui.GAME_WIDTH, gui.GAME_HEIGHT);    // Clear everything
        gui.fg.setFill(Color.WHITE);
        gui.render.Background();
        gui.fg.setFont(Font.font(18));
        //Todo  points
        for (int i=0; i<gameobjects.length; i++){
            if (renderDebug) {
                gui.fg.strokeRect(gameobjects[i].getX(), gameobjects[i].getY(), gameobjects[i].getWidth(), gameobjects[i].getHeight());
            } else {
                gui.fg.drawImage(gui.assets.get(gameobjects[i]), gameobjects[i].getX(), gameobjects[i].getY(), gameobjects[i].getWidth(), gameobjects[i].getHeight());
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
