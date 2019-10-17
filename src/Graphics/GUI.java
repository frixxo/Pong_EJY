package Graphics;

import Controlls.InputSystem;
import GameManagment.GM;
import GameManagment.IObserver;
import GameManagment.IWorldInfo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.IGameObject;

import static java.lang.System.out;

public class GUI extends Application implements IObserver {

    Render render= new Render();
    static final int GAME_WIDTH = 600;
    static final int GAME_HEIGHT = 400;
    private boolean running = false;    // Is game running?
    private GM gameManager = new GM(this, GAME_WIDTH, GAME_HEIGHT);
    private final IWorldInfo worldInfo = (IWorldInfo) gameManager;
    IGameObject[] gameObjects=worldInfo.GetAllGameObjects();
    Assets assets = new Cool(gameObjects[0],gameObjects[1],gameObjects[2]);

    // ------- Keyboard handling ----------------------------------
    private InputSystem[] players = worldInfo.GetPlayers();

    private void keyPressed(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
                if (players.length > 0) players[0].ChangeDirection(1);
                break;
            case DOWN:
                if (players.length > 0) players[0].ChangeDirection(-1);
                break;
            case Q:
                if (players.length > 1) players[1].ChangeDirection(1);
                break;
            case A:
                if (players.length > 0) players[1].ChangeDirection(-1);
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
                if (players.length > 0) players[0].ChangeDirection(0);
                break;
            case DOWN:
                if (players.length > 0) players[0].ChangeDirection(0);
                break;
            case A:
                if (players.length > 1) players[0].ChangeDirection(0);
                break;
            case Q:
                if (players.length > 1) players[0].ChangeDirection(0);
                break;
            default: // Nothing
        }
    }

    // ---- Menu handling (except themes) -----------------

    private void handleMenu(ActionEvent e) {
        String s = ((MenuItem) e.getSource()).getText();
        switch (s) {
            case "New":
                newGame();
                break;
            case "Stop":
                killGame();
                break;
            case "Exit":
                System.exit(0);
            default:
                throw new IllegalArgumentException("No such menu choice " + s);
        }
    }

    // ---------- Menu actions ---------------------

    private void newGame() {
        // Graphics.GUI handling
        menu.fixMenusNewGame();
        render.Background(fg,bg,assets);

        // Build the model
        gameManager = new GM(this, GAME_WIDTH, GAME_HEIGHT);
        InputSystem[] players = worldInfo.GetPlayers();

        // Start game
        timer.start();
        running = true;
    }

    private void killGame() {
        timer.stop();
        menu.fixMenusKillGame();
        render.menu(fg,bg,assets);
        running = false;
    }

    // -------- Event handling (events sent from model to Graphics.GUI) ------------


   /* @Override
    public void onModelEvent(ModelEvent evt) {
        if (evt.type == ModelEvent.Type.NEW_BALL) {
            // TODO Optional
        } else if (evt.type == ModelEvent.Type.BALL_HIT_PADDLE) {
            assets.hitsound.play();
        } else if (evt.type == ModelEvent.Type.BALL_HIT_WALL_CEILING) {
            // TODO Optional
        }
    }*/

    // ------- Optional ------------
    private void handleOptions(ActionEvent e) {
        CheckMenuItem i = (CheckMenuItem) e.getSource();
        if (i.isSelected()) {
           gameManager.SetPlayerToAI(1);
            out.println("AI on");
        } else {
            out.println("AI off");
        }
    }

    private void handleTheme(ActionEvent e) {
        IGameObject[] X = worldInfo.GetAllGameObjects();
        String k = ((MenuItem) e.getSource()).getText();
        switch (k) {
            case "Duckie":
               Duckie duckie = new Duckie(gameObjects[0],gameObjects[1],gameObjects[2]);
                break;
            case "Classic":
               //TODO Classic classic = new Classic(X[0], X[1], X[2]);
            case "Cool":
                break;
            default:
                throw new IllegalArgumentException("No such menu choice " + k);
        }
    }

    // -------------- Build Scene and start graphics ---------------

    private AnimationTimer timer;
    GraphicsContext fg;
    GraphicsContext bg;
    private PongMenu menu = new PongMenu(this::handleMenu, this::handleTheme, this::handleOptions);

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        root.setTop(menu);


        // Drawing areas
        Canvas background = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        bg = background.getGraphicsContext2D();
        Canvas foreground = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        fg = foreground.getGraphicsContext2D();

        Pane pane = new Pane(background, foreground);
        root.setCenter(pane);

        timer = new AnimationTimer() {
            public void handle(long now) {
                gameManager.Notify();
                render.game(worldInfo,fg,bg,assets);
            }
        };


        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pong");

        // Set assets, splash (order matters) and initial menu state
        menu.fixMenusKillGame();
        bg.drawImage(assets.menupic, 0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Show on screen
        primaryStage.show();
    }

    public void Update() //Updates every frame
    {
        IGameObject[] gameObjects = worldInfo.GetAllGameObjects();
        for (int i = 0; i < gameObjects.length; i++) {
            gameObjects[i].Update();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

