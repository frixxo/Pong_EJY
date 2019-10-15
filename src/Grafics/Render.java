package Grafics;

import GameManagment.GM;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Render extends GUI{


    // For debugging, see render()
    static boolean renderDebug = false; //true;

    void game() {
        /* TODO
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);    // Clear everything
        fg.setFill(Assets.colorFgText);
        fg.setFont(Font.font(18));
        fg.fillText("Points: " + pong.getPointsLeft(), 10, 20);
        fg.fillText("Points: " + pong.getPointsRight(), 500, 20);
        for (IPositionable d : pong.getPositionables()) {
            if (renderDebug) {
                fg.strokeRect(d.getX(), d.getY(), d.getWidth(), d.getHeight());
            } else {
                fg.drawImage(assets.get(d), d.getX(), d.getY(), d.getWidth(), d.getHeight());
            }
        }*/
    }

     void Background() {
        if (!renderDebug) {
            bg.drawImage(Assets.getBackground(), 0, 0, GAME_WIDTH, GAME_HEIGHT);
        }
    }

    void menu() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        bg.drawImage(Assets.menupic, 0, 0, GAME_WIDTH, GAME_HEIGHT);
    }
}
