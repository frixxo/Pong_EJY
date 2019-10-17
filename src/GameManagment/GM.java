package GameManagment;
import Controlls.AI;
import Controlls.IControll;
import Controlls.InputSystem;

import java.util.Arrays;
import java.util.HashSet;
import model.*;

import javax.swing.*;

public class GM implements IObservable, IWorldInfo{

    private IObserver[] observers;
    private IGameObject[] gameObjects;
    private IControll[] controlls;

    private Vector worldSize;
    private int score[] = {0,0};

    public GM(IObserver gui, int worldWidth, int worldHeight) { // This constructor initializes a new world
        worldSize = new Vector(worldWidth, worldHeight);

        Vector StartPositionBall = new Vector(0,0);
        double DisanceFromMidPaddle = 280;

        IControll Player1 = new InputSystem();
        IControll Player2 = new InputSystem();
        controlls = new IControll[]{Player1, Player2};

        gameObjects = new IGameObject[]{ //All GameObjects in the game
                new Ball(StartPositionBall, (IWorldInfo)this),
                new Paddle(new Vector(worldWidth/2-DisanceFromMidPaddle, worldHeight/2), (IWorldInfo) this, Player1), //TODO change startposition
                new Paddle(new Vector(worldWidth/2+DisanceFromMidPaddle, worldHeight/2), (IWorldInfo)this, Player2)
        };
        Player1.Set((Paddle)gameObjects[1]);
        Player2.Set((Paddle)gameObjects[2]);

        ModelManager modelManager = new ModelManager((Ball) gameObjects[0], (Paddle) gameObjects[1], (Paddle) gameObjects[2], (IWorldInfo)this);

        observers = new IObserver[]{ //All nonGameObject observers in the game
                modelManager,
                gui
        };
        HashSet<IObserver> set = new HashSet<IObserver>(); //Adding the gameObjects to the observers
        set.addAll(Arrays.asList(gameObjects));
        set.addAll(Arrays.asList(observers));

        for (int i = 0; i < controlls.length; i++) //Adding all the controls that need updates (ie AI)
        {
            try{
                set.add((IObserver)controlls[i]);
            } catch (ClassCastException e) {};
        }

        observers = set.toArray(observers);
    }

    //region ChangePlayers
    public void SetPlayerToAI (int playerIndex)
    {
        IControll swapPlayer = controlls[playerIndex];
        IControllable paddle = swapPlayer.GetPuppet();
        IControll ai = new AI((IWorldInfo) this, paddle);

        try {
            AI t = (AI)swapPlayer;
        } catch (IncompatibleClassChangeError e)
        {
            controlls[playerIndex] = ai;
            HashSet<IObserver> set = new HashSet<IObserver>();
            set.addAll(Arrays.asList(observers));
            set.add((IObserver) ai);
            observers = set.toArray(observers);
        }
    }

    public void SetAITOPlayer(int aiIndex)
    {
        IControll swapPlayer = controlls[aiIndex];
        IControllable paddle = swapPlayer.GetPuppet();
        IControll is = new InputSystem();
        is.Set(paddle);

        try {
            InputSystem t = (InputSystem) swapPlayer;
        } catch (IncompatibleClassChangeError e)
        {
            controlls[aiIndex] = is;
            HashSet<IObserver> set = new HashSet<IObserver>();
            set.addAll(Arrays.asList(observers));
            set.remove((IObserver) swapPlayer);
            observers = set.toArray(observers);
        }
    }
    //endregion

    //region IObservable
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

    public void SetScore(int newScore[])
    {
        score = newScore;
    }

    public int[] GetScore()
    {
        return score;
    }
    //endregion
}
