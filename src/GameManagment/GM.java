package GameManagment;
import com.sun.tools.javac.Main;
import model.Ball;
import model.IGameObject;

import java.util.ArrayList;
import java.util.List;

public class GM implements IObservable, IWorldInfo{

    private List<IObserver> observers = new ArrayList<IObserver>();
    private List<IGameObject> gameObjects = new ArrayList<IGameObject>();

    public GM(List<IObserver> observers, List<IGameObject> gameObjects) {
        this.observers = observers;
        this.gameObjects = gameObjects;
    }

    //region GameLoop
    void GameLoop() //TODO start gameloop
    {
        while (true) //TODO Byt ut mot en timad loop ellr nåt.
        {
            Notify();
        }
    }
    //endregion

    //region IObserver
    public void Add(IObserver observer) {
        observers.add(observer);
    }

    public void Remove(IObserver observer) {
        observers.remove(observer);
    }

    public void Notify() {
        for(int i = 0; i < observers.size(); i++)
        {
            observers.get(i).Update();
        }
    }
    //endregion

    //region IWorldInfo
    public List<IGameObject> GetAllGameObjects() {
        return gameObjects;
    }

    public Ball GetBall() {
        try {
            return (Ball)gameObjects.get(0); //The ball needs to always be at index zero
        } catch (IncompatibleClassChangeError e)
        {
            throw new RuntimeException("Ball not at index zero");
        }
    }
    //endregion
}
