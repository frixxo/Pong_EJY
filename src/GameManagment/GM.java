package GameManagment;
import Controlls.IControll;
import Controlls.InputSystem;
import Grafics.GUI;

import java.util.Arrays;
import java.util.HashSet;
import model.Ball;
import model.IGameObject;
import model.Paddle;
import model.Vector;

public class GM implements IObservable, IWorldInfo{

    private IObserver[] observers;
    private IGameObject[] gameObjects;
    private IControll[] controlls;

    private Vector worldSize;

    public GM(GUI gui, int worldWidth, int worldHeight) { // This constructor initializes a new world
        worldSize = new Vector(worldWidth, worldHeight);

        Vector StartPositionBall = new Vector(0,0);
        Vector StartPositionPaddle = new Vector(0, 0);

        IControll Player1 = new InputSystem();
        IControll Player2 = new InputSystem();
        controlls = new IControll[]{Player1, Player2};

        gameObjects = new IGameObject[]{ //All GameObjects in the game
                new Ball(StartPositionBall, (IWorldInfo)this),
                new Paddle(StartPositionPaddle, (IWorldInfo)this, Player1), //TODO change startposition
                new Paddle(StartPositionPaddle, (IWorldInfo)this, Player2)
        };
        Player1.Set((Paddle)gameObjects[1]);
        Player2.Set((Paddle)gameObjects[2]);


        observers = new IObserver[]{ //All nonGameObject observers in the game
                gui
        };
        HashSet<IObserver> set = new HashSet<IObserver>();
        set.addAll(Arrays.asList(gameObjects));
        set.addAll(Arrays.asList(observers));

        observers = set.toArray(observers);
    }

    //region IObserver
    public void Notify() {
        for(int i = 0; i < observers.length; i++)
        {
            observers[i].Update();
        }
    }
    //endregion

    //region IWorldInfo
    public IGameObject[] GetAllGameObjects() {
        return gameObjects;
    }

    public Ball GetBall() {
        try {
            return (Ball)gameObjects[0]; //The ball needs to always be at index zero
        } catch (IncompatibleClassChangeError e)
        {
            throw new RuntimeException("Ball not at index zero");
        }
    }

    public InputSystem[] GetPlayers(){
        HashSet<InputSystem> set = new HashSet<InputSystem>();
        for (int i = 0; i < controlls.length; i++)
        {
            try{
                set.add((InputSystem) controlls[i]);
            } catch (IncompatibleClassChangeError e) {}
        }
        InputSystem[] players = new InputSystem[0];
        players = set.toArray(players);
        return players;
    }

    public Vector GetWorldSize() {
        return worldSize;
    }
    //endregion
}
