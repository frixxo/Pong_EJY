package Grafics;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Render extends GUI {


    // For debugging, see render()
    static boolean renderDebug = false; //true;

    void game() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);    // Clear everything
        fg.setFill(assets.colorFgText);
        fg.setFont(Font.font(18));
        fg.fillText("Points: " + pong.getPointsLeft(), 10, 20);
        fg.fillText("Points: " + pong.getPointsRight(), 500, 20);
        for (IPositionable d : pong.getPositionables()) {
            if (renderDebug) {
                fg.strokeRect(d.getX(), d.getY(), d.getWidth(), d.getHeight());
            } else {
                fg.drawImage(assets.get(d), d.getX(), d.getY(), d.getWidth(), d.getHeight());
            }
        }
    }

    public static void Background() {
        if (!renderDebug) {
            bg.drawImage(assets.getBackground(), 0, 0, GAME_WIDTH, GAME_HEIGHT);
        }
    }

    public static void menu() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        bg.drawImage(/*TODO menu pic*/, 0, 0, GAME_WIDTH, GAME_HEIGHT);
    }
}
