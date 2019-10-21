package Graphics;

import Controlls.InputSystem;
import GameManagment.GM;
import GameManagment.IObserver;
import GameManagment.IWorldInfo;
import Graphics.Themes.Classic;
import Graphics.Themes.Cool;
import Graphics.Themes.Duckie;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.IGameObject;

public class GUI extends Application implements IObserver {

    Render render= new Render();
    static final int GAME_WIDTH = 600;
    static final int GAME_HEIGHT = 400;
    private boolean running = false;    // Is game running?
    private GM gameManager = new GM(this, GAME_WIDTH, GAME_HEIGHT);
    private IWorldInfo worldInfo = (IWorldInfo) gameManager;
    private String currentAIstatus = "Set 0x AI";
    IGameObject[] gameObjects=worldInfo.GetAllGameObjects();
    Assets assets = new Cool(gameObjects[0],gameObjects[1],gameObjects[2]);
    // ------- Keyboard handling ----------------------------------
    private InputSystem[] players = worldInfo.GetPlayers();

    public void SetInputSystem (InputSystem[] newPlayers) {players = newPlayers;}

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
                if (players.length > 1) players[1].ChangeDirection(-1);
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
            case DOWN:
                if (players.length > 0) players[0].ChangeDirection(0);
                break;
            case A:
            case Q:
                if (players.length > 1) players[1].ChangeDirection(0);
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
        render.Background(bg,assets);

        SetTheme(assets.getTheme()); //sätter temat igen
        SetAI(currentAIstatus);
        // Start game
        timer.start();
        running = true;

    }

    private void killGame() {
        timer.stop();
        menu.fixMenusKillGame();
        render.menu(fg,bg,assets);
        running = false;

        gameManager = new GM(this, GAME_WIDTH, GAME_HEIGHT);
        worldInfo = (IWorldInfo)gameManager;
        players = worldInfo.GetPlayers();
        gameObjects = worldInfo.GetAllGameObjects();

    }

    // ------- Optional ------------
    private void handleOptions(ActionEvent e) {
        String o = ((MenuItem) e.getSource()).getText();
        SetAI(o);
    }

    private void SetAI(String o){
        {
            switch (o){
                case "Set 0x AI":
                    gameManager.SetAITOPlayer(0);
                    gameManager.SetAITOPlayer(1);
                    currentAIstatus=o;
                    break;

                case "Set 1x AI":
                    gameManager.SetAITOPlayer(0);
                    gameManager.SetPlayerToAI(1);
                    currentAIstatus=o;
                    break;

                case "Set 2x AI":
                    gameManager.SetPlayerToAI(0);
                    gameManager.SetPlayerToAI(1);
                    currentAIstatus=o;
                    break;


            }

        }

    }

    private void handleTheme(ActionEvent e) {
      String k = ((MenuItem) e.getSource()).getText();
        SetTheme(k);
    }
    void SetTheme(String k){
        switch (k) {
            case "Duckie":
                assets = new Duckie(gameObjects[0],gameObjects[1],gameObjects[2]);
                break;
            case "Classic":
                assets = new Classic(gameObjects[0],gameObjects[1],gameObjects[2]);
                break;
            case "Cool":
                assets = new Cool(gameObjects[0],gameObjects[1],gameObjects[2]);
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

