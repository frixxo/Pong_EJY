package GameManagment;
import Controlls.AI;
import Controlls.IControll;
import Controlls.InputSystem;

import java.util.Arrays;
import java.util.HashSet;

import Graphics.GUI;
import model.*;

import javax.swing.*;

public class GM implements IObservable, IWorldInfo{

    private IObserver[] observers;
    private IGameObject[] gameObjects;
    private IControll[] controlls;
    private GUI gui;
    private Vector worldSize;
    private int score[] = {0,0,0};

    public GM(IObserver gui, int worldWidth, int worldHeight) { // This constructor initializes a new world
        worldSize = new Vector(worldWidth, worldHeight);
        this.gui = (GUI)gui;
        Vector StartPositionBall = new Vector(worldWidth/2,worldHeight/2);
        double DisanceFromMidPaddle = 280;

        IControll Player1 = new InputSystem();
        IControll Player2 = new InputSystem();
        controlls = new IControll[]{Player1, Player2};

        gameObjects = new IGameObject[]{ //All GameObjects in the game
                new Ball(StartPositionBall, (IWorldInfo)this),
                new Paddle(new Vector(worldWidth/2-DisanceFromMidPaddle, worldHeight/2), (IWorldInfo) this, Player2),
                new Paddle(new Vector(worldWidth/2+DisanceFromMidPaddle, worldHeight/2), (IWorldInfo)this, Player1)
        };
        Player1.Set((Paddle)gameObjects[2]);
        Player2.Set((Paddle)gameObjects[1]);

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
        try {
            InputSystem tst = (InputSystem) swapPlayer;
        } catch (ClassCastException e)
        {
            return;
        }
        IControllable paddle = swapPlayer.GetPuppet();
        IControll ai = new AI((IWorldInfo) this, paddle);
        swapPlayer.DeleteMe();
        ((Paddle)paddle).SetControll((IControll)ai);
        controlls[playerIndex] = ai;
        HashSet<IObserver> set = new HashSet<IObserver>();
        set.addAll(Arrays.asList(observers));
        set.add((IObserver) ai);
        observers = set.toArray(observers);
        gui.SetInputSystem(GetPlayers());
    }

    public void SetAITOPlayer(int aiIndex)
    {
        IControll swapPlayer = controlls[aiIndex];
        try {
            AI tst = (AI)swapPlayer;
        } catch (ClassCastException e)
        {
            return;
        }
        IControllable paddle = swapPlayer.GetPuppet();
        IControll is = new InputSystem();
        is.Set(paddle);
        swapPlayer.DeleteMe();
        ((Paddle)paddle).SetControll((IControll)is);
        controlls[aiIndex] = is;
        HashSet<IObserver> set = new HashSet<IObserver>();
        set.addAll(Arrays.asList(observers));
        if (set.contains(swapPlayer))
            set.remove(swapPlayer);
        observers = new IObserver[observers.length-1];
        observers = set.toArray(observers);
        gui.SetInputSystem(GetPlayers());
    }

    public void SwapPlayers(){
        InputSystem[] players= GetPlayers();
        InputSystem temp = players[0];
        players[0]=players[1];
        players[1]=temp;
        gui.SetInputSystem(players);
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
            } catch (ClassCastException e) {}
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
