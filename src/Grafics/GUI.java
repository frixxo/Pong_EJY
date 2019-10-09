package Grafics;

import GameManagment.IObserver;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Paddle;

import static java.lang.System.out;



public class GUI extends Application implements IObserver {
    private Assets assets;
    static final int GAME_WIDTH = 600;
    static final int GAME_HEIGHT= 400;
    private boolean running = false;    // Is game running?

    // ------- Keyboard handling ----------------------------------

    private void keyPressed(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
                // TODO
                break;
            case DOWN:
                // TODO
                break;
            case Q:
                // TODO
                break;
            case A:
                // TODO
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
                // TODO
                break;
            case DOWN:
                // TODO
                break;
            case A:
                // TODO
                break;
            case Q:
                // TODO
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
        // GUI handling
        menu.fixMenusNewGame();
        Render.Background();

        // Build the model
        Paddle rightPaddle = null;
        Paddle leftPaddle = null;

        // TODO Create objects and connect to a full object model


        // Start game
        timer.start();
        running = true;
    }

    private void killGame() {
        timer.stop();
        menu.fixMenusKillGame();
        Render.menu();
        running = false;
    }

    // -------- Event handling (events sent from model to GUI) ------------


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
    private void handleOptions(ActionEvent e){
        CheckMenuItem i = (CheckMenuItem) e.getSource();
        if( i.isSelected()){
            // TODO Optional if using AI
            out.println("AI on");
        }else {
            out.println("AI off");
        }
    }

    private void handleTheme(ActionEvent e){
        //TODO
    }

    // -------------- Build Scene and start graphics ---------------

    public AnimationTimer timer;
    public GraphicsContext fg;
    public GraphicsContext bg;
    public PongMenu menu = new PongMenu(this::handleMenu, this::handleTheme, this::handleOptions);
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        root.setTop(menu);

        Render Render = new Render();

        // Drawing areas
        Canvas background = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        bg = background.getGraphicsContext2D();
        Canvas foreground = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        fg = foreground.getGraphicsContext2D();

        Pane pane = new Pane(background, foreground);
        root.setCenter(pane);

        /*                                  //TODO Använd inte denna timer. Kör detta i Update metoden istället. mvh Emil
        timer = new AnimationTimer() {
            public void handle(long now) {
                Render.game();
            }
        };*/



        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pong");

        // Set assets, splash (order matters) and initial menu state
        //TODO set theme
        menu.fixMenusKillGame();
        bg.drawImage(assets.menupic, 0, 0, GAME_WIDTH, GAME_HEIGHT);

       // EventBus.INSTANCE.register(this);

        // Show on screen
        primaryStage.show();
    }

    public void Update() //Updates every frame
    {
    }

    public static void main(String[] args) {
        launch(args);
    }
}

